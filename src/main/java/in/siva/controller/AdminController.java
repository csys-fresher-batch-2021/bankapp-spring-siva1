package in.siva.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.siva.model.User;
import in.siva.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("AdminDisplay")
	public Iterable<User> getAllUser(HttpServletResponse response) throws IOException {

		Iterable<User> list = adminService.getAllUser();

		return list;
	}

}
