package in.siva.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.siva.model.Message;
import in.siva.model.User;
import in.siva.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

//	@PostMapping("Register")
//	@ResponseBody
//	public User register(@Valid @RequestBody User user) {
//		user.setCreatedDate(LocalDateTime.now());
//		System.out.println(user);
//		userService.registerDetails(user);
//		return user;
//
//	}

	@PostMapping("Register")
	@ResponseBody
	public ResponseEntity<Message> register(@Valid @RequestBody User user) {
		user.setCreatedDate(LocalDateTime.now());
		System.out.println(user);
		Message message = new Message();

		if (user != null) {
			message.setInfoMessage("Registered Successfully");
			HttpStatus httpStatus = HttpStatus.OK;
			userService.registerDetails(user);
			return new ResponseEntity<>(message, httpStatus);
		} else {
			message.setErrorMessage("Invalid Details");
			HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(message, httpStatus);
		}
	}

	@GetMapping("Display")
	public Optional<User> getUser(@RequestParam("accno") int accno, HttpServletResponse response) throws IOException {

		Optional<User> user = userService.getUser(accno);

		return user;

	}

	@GetMapping("AccountStatus")
	public boolean isActiveAccount(@RequestParam("accno") int accno, @RequestParam("status") boolean status,
			HttpServletResponse response) throws IOException {
		boolean isValid = false;
		isValid = userService.accountStatus(accno, status);
		return isValid;
	}

}
