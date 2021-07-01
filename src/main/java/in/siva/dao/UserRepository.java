package in.siva.dao;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query("select accno from bank_userdetails where email = :email")
	int getAccountNo(@Param("email") String email);

	@Modifying
	@Query("update bank_userdetails set active= :status where accno = :accno")
	int updateStatus(@Param("accno") int accno, @Param("status") boolean status);

	@Query("select * from bank_userdetails order by accno asc")
	Iterable<User> findAllUser();
}
