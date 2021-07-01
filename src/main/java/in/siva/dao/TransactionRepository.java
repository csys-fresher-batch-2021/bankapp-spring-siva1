package in.siva.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.siva.exception.DBException;
import in.siva.model.Transaction;
import in.siva.model.User;

@Repository
public class TransactionRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public double getBalance(int accno) {
		// double accountBalance = 0;
		Double accountBalance = null;
		try {
			String sql = "select balance from bank_userdetails where accno = ?";
//			accountBalance = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//				double balance = rs.getDouble("balance");
//				return balance;
//			});
			accountBalance = jdbcTemplate.queryForObject(sql, Double.class, accno);

		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Deposit");

		}

		return accountBalance;
	}

	public boolean updateBalance(int accno, double amount, String type) {

		boolean isUpdated = false;

		String sql = null;

		if (type.equals("DEPOSIT")) {
			sql = "update bank_userdetails set balance = balance + ? where accno = ?";
		} else if (type.equals("WITHDRAW")) {
			sql = "update bank_userdetails set balance =balance - ? where accno = ?";
		}
//		else {
//			sql = "update bank_userdetails set balance =balance - ? where accno = ?";
//		}

		Object[] params = { amount, accno };
		int row = jdbcTemplate.update(sql, params);
		if (row == 1) {
			isUpdated = true;
		}

		return isUpdated;
	}

	public boolean exists(int accNo) {

		String sql = "select accno from bank_userdetails where accNo = ?";

		try {
			accNo = jdbcTemplate.queryForObject(sql, Integer.class, accNo);
		} catch (DataAccessException e) {
			accNo = -1;
		}
		return accNo > 0 ? true : false;

	}

	public List<Transaction> summary(int accno) {

		String sql = "select bank.accno,bank.name,tr.amount,tr.comments,"
				+ "tr.transaction_type,tr.transaction_time from bank_userdetails bank,"
				+ "transaction_details tr where bank.accno=tr.accno and bank.accno=?"
				+ " order by tr.transaction_time desc";

		return jdbcTemplate.query(sql, (rs, rowNo) -> {

			return statement(rs);
		}, accno);
	}

	public List<Transaction> miniStatement(int accno) {

		String sql = "select bank.accno,bank.name,tr.amount,tr.comments,"
				+ "tr.transaction_type,tr.transaction_time from bank_userdetails bank,"
				+ "transaction_details tr where bank.accno=tr.accno and bank.accno=?"
				+ " order by tr.transaction_time desc limit 5";

		return jdbcTemplate.query(sql, (rs, rowNo) -> {

			return statement(rs);
		}, accno);
	}

	private Transaction statement(ResultSet rs) throws SQLException {
		User user = new User();
		Transaction trans = new Transaction();
		user.setAccNo(rs.getInt(1));
		user.setName(rs.getString(2));
		trans.setAmount(rs.getFloat(3));
		trans.setComments(rs.getString(4));
		trans.setTransactionType(rs.getString(5));
		Timestamp transTime = rs.getTimestamp(6);
		LocalDateTime transferTime = transTime.toLocalDateTime();
		trans.setTransactionDateTime(transferTime);
		trans.setUser(user);
		return trans;
	}

	public void transactionDetails(int accno, Transaction trans) {

		String sql = "insert into transaction_details"
				+ "(accno,amount,transaction_type,comments,transaction_time)values(?,?,?,?,?)";
		Object[] params = { accno, trans.getAmount(), trans.getTransactionType(), trans.getComments(),
				trans.getTransactionDateTime() };
		jdbcTemplate.update(sql, params);
	}
}
