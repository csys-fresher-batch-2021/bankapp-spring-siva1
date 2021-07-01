package in.siva.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.siva.model.User;
import in.siva.service.UserService;

@Component
public class AmountValidator {

	@Autowired
	UserService userService;
	

	/**
	 * This method validates the amount
	 * 
	 * @param accNo
	 * @param amount
	 * @return
	 */
	public boolean isSufficientAmount(int accNo, float amount) {

		boolean isSufficient = false;
		Optional<User> user = userService.getUser(accNo);
		if (user.get().getBalance() < amount) {
			isSufficient = true;

		}
		return isSufficient;
	}
}