package in.siva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.siva.dao.TransactionRepository;
import in.siva.exception.ValidException;
import in.siva.model.Transaction;
import in.siva.util.AmountValidator;
import in.siva.validator.UserValidation;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AmountValidator amountValidator;

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param name   // name of user
	 * @param amount // amount to deposit
	 */

	public double depositAmount(int accNo, Transaction transaction) {
		double balance = 0;
		// condition to validate accno and amount

		if (!UserValidation.isValidAmount(transaction.getAmount())) {
			throw new ValidException("Invalid Amount");
		}
		if (!transactionRepository.exists(accNo)) {
			throw new ValidException("Invalid  Account Number!");
		}

		transactionRepository.updateBalance(accNo, transaction.getAmount(), "DEPOSIT");
		transactionRepository.transactionDetails(accNo, transaction);
		balance = transactionRepository.getBalance(accNo);

		return balance;
	}

	/**
	 * Withdraw money from account
	 * 
	 * @param name   // name of user
	 * @param amount // amount withdraw by user
	 * @return
	 */

	public double withdrawAmount(int accNo, Transaction transaction) {

		double balance = 0;
		// condition to validate email and amount

		if (!UserValidation.isValidAmount(transaction.getAmount())) {
			throw new ValidException("Amount Invalid");
		}
		if (amountValidator.isSufficientAmount(accNo, transaction.getAmount())) {
			throw new ValidException("InSufficient Balance in Your Account!");
		}
		if (!transactionRepository.exists(accNo)) {
			throw new ValidException("Invalid Account Number!");
		}

		transactionRepository.updateBalance(accNo, transaction.getAmount(), "WITHDRAW");
		transactionRepository.transactionDetails(accNo, transaction);
		balance = transactionRepository.getBalance(accNo);

		return balance;
	}

	/**
	 * This method transfers amount to another user account
	 * 
	 * @param senderAccNo
	 * @param receiverAccNo
	 * @param transaction
	 * @return
	 */
	@Transactional
	public double transferAmount(int senderAccNo, int receiverAccNo, Transaction transaction) {
		double balance = 0;

		if (!UserValidation.isValidAmount(transaction.getAmount())) {
			throw new ValidException("Invalid Amount");
		}
		if (amountValidator.isSufficientAmount(senderAccNo, transaction.getAmount())) {
			throw new ValidException("InSufficient Balance in Your Account!");
		}
		if (!transactionRepository.exists(receiverAccNo)) {
			throw new ValidException("Invalid Receiver Account Number!");
		}
		if (senderAccNo == receiverAccNo) {
			throw new ValidException("Enter Receiver Account Number!");
		}
		if (!transactionRepository.exists(senderAccNo)) {
			throw new ValidException("Invalid Account Number!");
		}
		transactionRepository.updateBalance(receiverAccNo, transaction.getAmount(), "DEPOSIT");
		transactionRepository.updateBalance(senderAccNo, transaction.getAmount(), "WITHDRAW");
		transactionRepository.transactionDetails(senderAccNo, transaction);
		balance = transactionRepository.getBalance(senderAccNo);

		return balance;
	}

	/**
	 * This method gives the bank statement of the user
	 * 
	 * @param accno
	 * @return
	 */
	public List<Transaction> overallStatement(int accno) {
		List<Transaction> display = null;

		if (UserValidation.isValidAccount(accno)) {
			display = transactionRepository.summary(accno);
		}

		return display;
	}

	/**
	 * This method shows the last few transactions of the User
	 * 
	 * @param accno
	 * @return
	 */
	public List<Transaction> miniStatement(int accno) {
		List<Transaction> display = null;

		if (UserValidation.isValidAccount(accno)) {
			display = transactionRepository.miniStatement(accno);
		}

		return display;
	}

}
