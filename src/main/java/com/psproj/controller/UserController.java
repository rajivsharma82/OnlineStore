package com.psproj.controller;

import com.psproj.form.EditUserFormBean;
import com.psproj.form.RegisterFormBean;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.dao.UserRoleDAO;
import com.psproj.repository.entity.Product;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
import com.psproj.utilities.OnlineStoreUtilities;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    OnlineStoreUtilities onlineStoreUtilities;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/manageUser", method = RequestMethod.GET)
    public ModelAndView manageUserDisplay( HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/userSearch");
        List<User> userList = userDao.findAll();
        response.addObject("userSearchList",userList);
//        System.out.println(productForm);
        return response;
    }

    @RequestMapping(value = "/userSearch", method = RequestMethod.GET)
    public ModelAndView userSearch(@RequestParam (required = false) String search ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/userSearch");

        if(!StringUtils.isEmpty(search)){
//            List<User> userSearchList = userDao.findByFirstNameLike(search);
            List<User> userSearchList = userDao.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(search);


            response.addObject("userSearchList",userSearchList);
            response.addObject("searchKey", search);

            for(User user: userSearchList){
                System.out.println(user);
            }

        }

        return response;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id,
                               @RequestParam (required = false) String searchKey ) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("redirect:/registration-url-path/userSearch?search=" + searchKey);
        //response.setViewName("redirect:/productSearch?search=" + searchKey);

        User user = userDao.findById(id);
        Integer deleteUserId = user.getId();
        if ( deleteUserId != null ) {
            //userRoleDAO.deleteUserRolesByUser(deleteUserId);
            userDao.delete(user);
        }

        return response;
    }


    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public ModelAndView editUser(@RequestParam(required = false) Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/userEdit");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
            User user = userDao.findById(id);

            // populate the form bean with the data loaded from the database
            EditUserFormBean form = new EditUserFormBean();

            form.setEmail(user.getEmail());
            form.setFirstName(user.getFirstName());
            form.setLastName(user.getLastName());
            form.setUserName(user.getUsername());
//            form.setPassword(user.getPassword());
//            form.setConfirmPassword(user.getPassword());
            form.setPhone(user.getPhone());

            response.addObject("formBeanKey", form);
            response.addObject("userEditId", user.getId());
        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
            //Find the user Id in session
            User userInSession = onlineStoreUtilities.getUserInSession();
            User user = userDao.findById(userInSession.getId());

            // populate the form bean with the data loaded from the database
            EditUserFormBean form = new EditUserFormBean();

            form.setEmail(user.getEmail());
            form.setFirstName(user.getFirstName());
            form.setLastName(user.getLastName());
            form.setUserName(user.getUsername());

//            form.setPassword(user.getPassword());
//            form.setConfirmPassword(user.getPassword());
            form.setPhone(user.getPhone());

            response.addObject("formBeanKey", form);
            response.addObject("userEditId", user.getId());

//            RegisterFormBean form = new RegisterFormBean();
//            response.addObject("formBeanKey", form);
        }

        return response;
    }

    @RequestMapping(value = "/editUserSubmit", method = RequestMethod.POST)
    public ModelAndView editUserSubmit(@Valid EditUserFormBean form,
                                       @RequestParam Integer userEditId,
                                       BindingResult errors,
                                       HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        //User userEmail = userDao.findByEmail(form.getEmail());

        User userFound = userDao.findById(userEditId);

        if(errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                form.getErrorMessages().add(error.getDefaultMessage());
                System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }
            System.out.println(form);
            response.addObject("formBeanKey",form);
            response.setViewName("user/userEdit");
        }

        else{
            // there are no errors on the form submission so this is either a create or an update.
            // a new user object is needed to perform add/update
//            User user = new User();
            User user = userDao.findById(userEditId);
//
            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);

//            session.setAttribute("username", form.getUserName());

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

//            response.setViewName("redirect:/login/login");
            response.setViewName("user/userEditSuccess");

        }


        return response;
    }


}
