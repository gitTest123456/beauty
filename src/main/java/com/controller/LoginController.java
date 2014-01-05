package com.controller;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 05.01.14
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "/login.html";

	}

	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "/login.html";

	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "/login.html";

	}

}
