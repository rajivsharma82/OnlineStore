package com.psproj.controller;

import com.psproj.form.RegisterFormBean;
import com.psproj.repository.dao.UserDao;
import com.psproj.repository.dao.UserRoleDAO;
import com.psproj.repository.entity.User;
import com.psproj.repository.entity.UserRole;
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
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDAO userRoleDAO;

    @RequestMapping(value = "/userSearch", method = RequestMethod.GET)
    public ModelAndView userSearch(@RequestParam (required = false) String search ) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/userSearch");

        if(!StringUtils.isEmpty(search)){
            List<User> userSearchList = userDao.findByFirstNameLike(search);

            response.addObject("userSearchList",userSearchList);
            response.addObject("searchKey", search);

            for(User user: userSearchList){
                System.out.println(user);
            }

        }

        return response;
    }


}
