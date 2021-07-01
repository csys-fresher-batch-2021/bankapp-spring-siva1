package in.siva.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.siva.model.Message;
import in.siva.model.Transaction;
import in.siva.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PatchMapping("Deposit")
	public ResponseEntity<Message> deposit(@RequestParam("accno") int accno, @RequestParam("amount") float amount) {
		double balance = 0;
		Transaction transaction = new Transaction();
		Message message = new Message();
		transaction.setAmount(amount);
		transaction.setTransactionType("CREDITED");
		transaction.setTransactionDateTime(LocalDateTime.now());
		balance = transactionService.depositAmount(accno, transaction);
		System.out.println(balance);
		if (balance > 0) {
			message.setInfoMessage("DEPOSIT SUCCESS");
			HttpStatus httpStatus = HttpStatus.OK;
			return new ResponseEntity<>(message, httpStatus);
		} else {
			message.setErrorMessage("DEPOSIT FAILED");
			HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(message, httpStatus);

		}

	}

	@PatchMapping("Withdraw")
	public ResponseEntity<Message> withdraw(@RequestParam("accno") int accno, @RequestParam("amount") float amount) {
		double balance = 0;
		Transaction transaction = new Transaction();
		Message message = new Message();
		transaction.setAmount(amount);
		transaction.setComments("TRANSACTION THROUGH ATM");
		transaction.setTransactionType("DEBITED");
		transaction.setTransactionDateTime(LocalDateTime.now());
		balance = transactionService.withdrawAmount(accno, transaction);
		System.out.println(balance);

		if (balance > amount) {
			message.setInfoMessage("WITHDRAW SUCCESS");
			HttpStatus httpStatus = HttpStatus.OK;
			return new ResponseEntity<>(message, httpStatus);
		} else {
			message.setErrorMessage("WITHDRAW FAILED");
			HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(message, httpStatus);

		}
	}

	@GetMapping("Transfer")
	public ResponseEntity<Message> fundTransfer(@RequestParam("fromAccNo") int fromAccNo,
			@RequestParam("toAccNo") int toAccNo, @RequestParam("amount") float amount) {
		double balance = 0;
		Transaction transaction = new Transaction();
		Message message = new Message();
		transaction.setAmount(amount);
		transaction.setComments("TRANSACTION TRHOUGH UPI");
		transaction.setTransactionType("TRANSFERED TO ACCNO" + toAccNo);
		transaction.setTransactionDateTime(LocalDateTime.now());
		balance = transactionService.transferAmount(fromAccNo, toAccNo, transaction);
		if (balance > amount) {
			message.setInfoMessage("TRANSFER SUCCESS");
			HttpStatus httpStatus = HttpStatus.OK;
			return new ResponseEntity<>(message, httpStatus);
		} else {
			message.setErrorMessage("TRANSFER FAILED");
			HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(message, httpStatus);

		}
	}

	@GetMapping("Statement")
	public Iterable<Transaction> transactionStatement(@RequestParam("accno") int accno) {
		Iterable<Transaction> statementList = null;
		statementList = transactionService.overallStatement(accno);
		return statementList;
	}

	@GetMapping("MiniStatement")
	public Iterable<Transaction> miniTransactionStatement(@RequestParam("accno") int accno) {
		Iterable<Transaction> statementList = null;
		statementList = transactionService.miniStatement(accno);
		return statementList;
	}

}
