package com.psproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

	@RequestMapping(value =  "/" , method = RequestMethod.GET)
	public ModelAndView about(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView response = new ModelAndView();
		//response.setViewName("redirect:/showProducts");
		//session.invalidate();
		response.setViewName("redirect:/showProducts");

		return response;
	}

	@RequestMapping(value =  "/about" , method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView response = new ModelAndView();
		response.setViewName("about/about");

		return response;
	}

	@RequestMapping(value =  "/contactus" , method = RequestMethod.GET)
	public ModelAndView contactus(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView response = new ModelAndView();
		response.setViewName("about/contactus");

		return response;
	}

}
