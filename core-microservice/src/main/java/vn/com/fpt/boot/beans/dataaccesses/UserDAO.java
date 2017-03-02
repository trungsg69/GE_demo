package vn.com.fpt.boot.beans.dataaccesses;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.com.fpt.boot.beans.Entities.Users;


public interface UserDAO extends JpaRepository<Users, Long> {

	@Query(value = "select * from Vegetable.user", nativeQuery = true)
	List<Users> getAll();
	
	@Query(value = "SELECT * FROM Vegetable.user ORDER BY userid DESC limit 1", nativeQuery = true)
	List<Users> getLastUser();
	@Query(value = "SELECT * FROM users u where u.username = ?1", nativeQuery = true)
	List<Users> getUserByName(String userName);
	
	@Query(value = "SELECT * FROM Vegetable.user where userid=?1 limit 1", nativeQuery = true)
	List<Users> getUserById(long userid);
	
	@Query(value = "Insert into Vegetable.user(username,password) values(?1,?2)", nativeQuery = true)
	void addUser(String userName,String password);
	
	@Query(value = "Update Vegetable.user set userid=?1,username=?2,password=?3,fullname=?4" , nativeQuery = true)
	void updateUser(long userid, String username,String password,String fullname);
	
	@Query(value = "Delete from Vegetable.user where userid=?", nativeQuery = true)
	void deleteUser(long userid);

}
