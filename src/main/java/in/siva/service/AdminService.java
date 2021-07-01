package in.siva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.dao.AdminRepository;
import in.siva.dao.UserRepository;
import in.siva.exception.ValidException;
import in.siva.model.Admin;
import in.siva.model.User;
import in.siva.validator.UserValidation;

@Service
public class AdminService {
	private AdminService() {
		// Default Constructor
	}

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Login for Admin
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin adminLogin(String name, String password) {

		Admin admin = null;
		if (UserValidation.nameValidation(name) && UserValidation.passwordValidation(password)) {
			admin = adminRepository.findByNameAndPassword(name, password);
		}
		return admin;

	}

	/**
	 * This method shows all User details for Admin
	 * 
	 * @return
	 */
	public Iterable<User> getAllUser() {
		Iterable<User> userList = null;
		try {
			userList = userRepository.findAllUser();
		} catch (Exception e) {
			throw new ValidException("Details Not Available");
		}
		return userList;
	}

}
