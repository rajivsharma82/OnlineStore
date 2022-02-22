package com.psproj.controller;

import org.springframework.stereotype.Controller;

@Controller
public class LoginControllerold {

    //create a login controller
    //create a login jsp
    //create a logoutSuccess.jsp page
//    private static String SESSION_ERR_MSG = "errorMessageKey";
//
//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public ModelAndView initialLogin(HttpServletRequest request, HttpSession session) throws Exception {
//        ModelAndView response = new ModelAndView();
//        String username = (String)session.getAttribute("username");
//
//        if(StringUtils.equals(username,"admin")){
//            response.setViewName("redirect:/success");
//        }else{
//            response.setViewName("login/login");
//            String errorMessage = (String)session.getAttribute(SESSION_ERR_MSG);
//            response.addObject("errormessage",errorMessage);
//        }
//
//
//        return response;
//    }
//
//
//    @RequestMapping(value = {"/loginSubmit"}, method = RequestMethod.GET)
//    public ModelAndView loginSubmit(LoginFormBean form, HttpServletRequest request, HttpSession session) throws Exception {
//        ModelAndView response = new ModelAndView();
//
//        String username = form.getName();
//        String password = form.getPassword();
//
//        if("admin".equals(username) && "admin".equals(password)){
//            session.setAttribute("username", username);
//            System.out.println("username is " + username);
//            response.setViewName("redirect:/success");
//        }else{
//            session.setAttribute("username",null);
//            response.setViewName("redirect:/login");
//            session.setAttribute(SESSION_ERR_MSG,"please enter correct username and password");
//        }
//        return response;
//    }
//
//    @RequestMapping(value = { "/success" }, method = RequestMethod.GET)
//    public ModelAndView success(HttpServletRequest request, HttpSession session) throws Exception {
//        ModelAndView response = new ModelAndView();
//        String username = (String)session.getAttribute("username");
//
//        if(StringUtils.equals(username,"admin")) {
//            response.setViewName("login/success");
//            response.addObject(username);
//        } else{
//            //response.setViewName("login/login");
//            response.setViewName("redirect:/login");
//        }
//        return response;
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout(HttpSession session) throws Exception{
//        //this is how to destroy the current user session
//        //always implement a logout method this way
//        session.invalidate();
//        ModelAndView response = new ModelAndView();
//        // this is how to do a redirect in spring mvc in the model
//        // response.setViewName("login/login");
//        //this will change the url to be localhost:8080/login which is preferred because the url is the same
//        //as the page you are on
//        response.setViewName("redirect:/login");
//
//        //response.setViewName("login/login");
//        return response;
//    }

}
