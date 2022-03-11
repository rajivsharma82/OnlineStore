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

        return response;
    }
}
