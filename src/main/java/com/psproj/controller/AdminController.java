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
@RequestMapping("/admin")
// this restricts the controller to admin only, this can be done at the class level or at the method level
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {


    //@PreAuthorize("hasAuthority('ADMIN', 'USER')")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/home");

        log.debug("debug message");
        return response;
    }
}
