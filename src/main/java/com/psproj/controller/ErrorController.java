package com.psproj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/error")
// this restricts the controller to admin only, this can be done at the class level or at the method level
//@PreAuthorize("hasAuthority('ADMIN')")
public class ErrorController {


    //@PreAuthorize("hasAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("error/error");

        log.debug("debug message");
        return response;
    }



}
