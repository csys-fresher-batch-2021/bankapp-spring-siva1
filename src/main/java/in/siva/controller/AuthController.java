package in.siva.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.siva.model.Admin;
import in.siva.model.Message;
import in.siva.model.User;
import in.siva.service.AdminService;
import in.siva.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController

public class AuthController {

	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;

	@ApiOperation(value = "Authenticate User", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Login Success", response = User.class),
			@ApiResponse(code = 401, message = "Invalid Login Credentials", response = Message.class) })

	@PostMapping("AdminLogin")
	public ResponseEntity<?> adminLogin(@RequestBody Admin admin, HttpSession session) {

		Admin adminResult = adminService.adminLogin(admin.getName(), admin.getPassword());

		Message message = new Message();
		if (adminResult != null) {
			message.setInfoMessage("LOGIN SUCCESS");
			HttpStatus httpStatus = HttpStatus.OK;
			return new ResponseEntity<>(adminResult, httpStatus);
		} else {
			message.setErrorMessage("INVALID LOGIN CREDENTIALS");
			HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(message, httpStatus);
		}

	}

	@PostMapping("UserLogin")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User userResult = null;
		System.out.println("Auth" + user);
		userResult = userService.userLogin(user.getEmail(), user.getPassword());
		System.out.println(userResult);
		session.setAttribute("LOGGED_IN_USER", user.getEmail());
		int accno = userService.getAccNo(user.getEmail());
		session.setAttribute("ACCOUNTNUMBER", accno);
		Message message = new Message();
		if (userResult != null) {
			HttpStatus httpStatus = HttpStatus.OK;
			return new ResponseEntity<>(userResult, httpStatus);
		} else {
			message.setErrorMessage("INVALID LOGIN CREDENTIALS");
			HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(message, httpStatus);
		}
	}

}
