package in.siva.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.siva.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

	@Query("select name,password from admin where name=:name and password=:password")
	Admin findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
