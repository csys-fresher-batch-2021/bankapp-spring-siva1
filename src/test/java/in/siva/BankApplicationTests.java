package in.siva;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.siva.dao.UserRepository;

@SpringBootTest
class BankApplicationTests {

	
	@Autowired
	UserRepository user;
	@Test
	void contextLoads() {
	
	System.out.println(user.findById(2));
	}

}
