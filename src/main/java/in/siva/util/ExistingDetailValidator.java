package in.siva.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.siva.exception.ValidException;
import in.siva.model.User;
import in.siva.service.AdminService;

@Component
public class ExistingDetailValidator {

	@Autowired
	AdminService userDetails;

	public void isExistingDetails(String email, long mobileNo) throws ValidException {

		Iterable<User> list = userDetails.getAllUser();

		for (User user : list) {

			if (user.getEmail().equalsIgnoreCase(email) && user.getMobileNo() == mobileNo) {
				throw new ValidException("Email Id and Mobile Number Already Exists");
			}
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new ValidException("Email Id Already Exists");

			}
			if (user.getMobileNo() == mobileNo) {
				throw new ValidException("Mobile Number Already Exists");
			}

		}
	}
}
