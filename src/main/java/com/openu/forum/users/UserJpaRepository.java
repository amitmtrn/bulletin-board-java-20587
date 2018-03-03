package com.openu.forum.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends CrudRepository<User, Long>{

  @Query(value="SELECT * FROM users WHERE username=:username", nativeQuery=true)
	public User findByUsername(@Param("username") String username);
 
}
