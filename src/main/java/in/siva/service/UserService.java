package in.siva.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.siva.dao.UserRepository;
import in.siva.exception.ValidException;
import in.siva.model.User;
import in.siva.util.ExistingDetailValidator;
import in.siva.validator.UserValidation;

@Service
public class UserService {

	private UserService() {
		// Default Constructor
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	ExistingDetailValidator existingDetail;

	/**
	 * Validation for Login
	 * 
	 * @param userName     // name of the user
	 * @param userPassword //password given by the user
	 * @return
	 */

	public User userLogin(String email, String userPassword) {

		User user = null;
		if (UserValidation.emailValidation(email) && UserValidation.passwordValidation(userPassword)) {

			user = userRepository.findByEmailAndPassword(email, userPassword);
			System.out.println("Service" + user);
		}

		return user;
	}

	/**
	 * Register with user details Add the user Details in ArrayList
	 * 
	 * @param list //Details of user
	 */
	public boolean registerDetails(User user) throws ValidException {
		boolean register = false;

		existingDetail.isExistingDetails(user.getEmail(), user.getMobileNo());
		// Condition for valid details
		if (UserValidation.isValidUser(user)) {
			userRepository.save(user);
		} else {
			throw new ValidException("Invalid Details");
		}
		return register;

	}

	/**
	 * Method to get all user details in arrayList
	 * 
	 * @param name //name of user
	 * @return
	 */
	public Optional<User> getUser(int accNo) {

		Optional<User> display = null;
		try {
			if (UserValidation.isValidAccount(accNo)) {
				System.out.println(accNo);
				display = userRepository.findById(accNo);
			}
		} catch (ValidException e) {
			throw new ValidException("Invalid Account Number");

		}
		return display;

	}

	/**
	 * This method shows whether the account is active or not
	 * 
	 * @param accno
	 * @param status
	 * @return
	 */
	public boolean accountStatus(int accno, boolean status) {
		boolean isValid = false;

		try {
			if (accno > 0) {

				isValid = true;
				userRepository.updateStatus(accno, status);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new ValidException("Unable to fetch status");
		}
		return isValid;
	}

	/**
	 * This method gets the Account Number of the user
	 * 
	 * @param email
	 * @return
	 */
	public int getAccNo(String email) {
		int accNo = 0;

		try {
			if (UserValidation.emailValidation(email)) {
				accNo = userRepository.getAccountNo(email);
			}
		} catch (ValidException e) {
			throw new ValidException("Invalid Email ID");

		}
		return accNo;
	}

}
