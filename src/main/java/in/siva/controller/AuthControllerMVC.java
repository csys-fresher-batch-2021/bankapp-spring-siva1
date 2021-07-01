package in.siva.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthControllerMVC {

	@GetMapping("Logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession(); // creating a session
		session.invalidate();// remove the username from session
		return "redirect:index.html";

	}
}
