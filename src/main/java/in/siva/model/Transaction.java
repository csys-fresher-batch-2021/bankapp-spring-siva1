package in.siva.model;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor

public class Transaction {
	private float amount;
	private String transactionType;
	private String comments = "TRANSACTION THROUGH UPI";
	private LocalDateTime transactionDateTime;
	@ManyToOne
	private User user;

}