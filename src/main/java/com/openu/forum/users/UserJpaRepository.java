package com.openu.forum.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * A repository that provides CRUD operations for User objects
 *  
 * @author amit and nir
 *
 */
public interface UserJpaRepository extends CrudRepository<User, Long>{

	/**
	 * Finds the user by his user name 
	 * @param username - a given user name
	 * @return the user (at most 1) with the given user name
	 */
	@Query(value="SELECT * FROM users WHERE username=:username", nativeQuery=true)
	public User findByUsername(@Param("username") String username);

}
