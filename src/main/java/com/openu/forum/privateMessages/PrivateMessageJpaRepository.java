package com.openu.forum.privateMessages;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * A repository that provides CRUD operations for PrivateMessage objects
 *  
 * @author amit and nir
 *
 */
public interface PrivateMessageJpaRepository extends CrudRepository<PrivateMessage, Long> {

	/**
	 * Gets the received private messages of a given user
	 * @param id - the id of a given user
	 * @return the list of the received private messages of the given user
	 */
	@Query(value="SELECT * FROM private_messages WHERE to_id=:id AND to_visable=true", nativeQuery=true)
	public List<PrivateMessage> getUserReceivedMessages(@Param("id") Long id);

	/**
	 * Gets the private messages which sent by the given user
	 * @param id - the id of a given user
	 * @return the list of the private messages which sent by the given user
	 */
	@Query(value="SELECT * FROM private_messages WHERE from_id=:id AND from_visable=true", nativeQuery=true)
	public List<PrivateMessage> getUserSentMessages(@Param("id") Long id);

}
