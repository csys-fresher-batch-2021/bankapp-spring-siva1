package in.siva.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Table(value = "bank_userdetails")
public class User {

	@Pattern(regexp = "^[a-zA-Z\\s]+\\w{4,29}$")
	private String name;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
	private String email;

	// @JsonProperty(access = Access.WRITE_ONLY)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$", message = "Invalid Password")
	private String password;

	@NotNull
	@NotEmpty
	private String address;

	@Min(value = 6000000000L, message = "Invalid Mobile Number")
	@Max(value = 9999999999L, message = "Invalid Mobile Number")
	@Column("mobileno")
	private long mobileNo;

	@Column("accno")
	@Id
	private int accNo;

	@Column("created_date")
	@PastOrPresent
	private LocalDateTime createdDate;

	@NotNull
	private float balance;

	private boolean active = true;

}
