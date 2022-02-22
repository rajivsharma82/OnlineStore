package com.psproj.controller;

import com.psproj.repository.dao.UserDao;
import com.psproj.repository.dao.UserRoleDAO;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import com.psproj.form.RegisterFormBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/registration-url-path")
public class RegistrationController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register( ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register1");

        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        User userEmail = userDao.findByEmail(form.getEmail());

        if(errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                form.getErrorMessages().add(error.getDefaultMessage());
                System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }
            System.out.println(form);
            response.addObject("formBeanKey",form);
            response.setViewName("registration/register1");
        }
        else if(userEmail != null){
            form.getErrorMessages().add("Email already registered");
            response.addObject("formBeanKey",form);
            response.setViewName("registration/register1");
        }
        else{
            // there are no errors on the form submission so this is either a create or an update.
            // a new user object is needed to perform add/update
            User user = new User();

            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);

            session.setAttribute("username", form.getUserName());

            user.setUsername(form.getUserName());
            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(encryptedPassword);
            user.setPhone(form.getPhone());
            userDao.save(user);

            UserRole ur = new UserRole();
            ur.setUser(user);
            ur.setUserRole("USER");
            userRoleDAO.save(ur);

            response.setViewName("redirect:/login/login");

        }

        // First i will get object of RegisterFOrmBeanClass
        // Form.setName(get the value from the JSP form)
        // do above for all the form fields

        // Create an object of UserDAO
        // DAO.Save(Form newUser ) -- i will be having
        // Spring Boot , JPA - @Entity , Front end JSP

        // FIx the condition-when there is no errors in form only then save to DB

        return response;
    }

    @RequestMapping(value = "/userSearch", method = RequestMethod.GET)
    public ModelAndView userSearch(@RequestParam (required = false) String search ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/userSearch");

        if(!StringUtils.isEmpty(search)){
            List<User> userSearchList = userDao.findByFirstName(search);

            response.addObject("userSearchList",userSearchList);
            response.addObject("searchKey", search);

            for(User user: userSearchList){
                System.out.println(user);
            }

        }

        return response;
    }


}
