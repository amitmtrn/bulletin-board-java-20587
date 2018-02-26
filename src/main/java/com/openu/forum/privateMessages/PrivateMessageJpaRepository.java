package com.openu.forum.privateMessages;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PrivateMessageJpaRepository extends CrudRepository<PrivateMessage, Long> {

	@Query(value="SELECT * FROM private_messages WHERE to_id=:id", nativeQuery=true)
	public List<PrivateMessage> getUserReceivedMessages(@Param("id") Long id);

	@Query(value="SELECT * FROM private_messages WHERE from_id=:id", nativeQuery=true)
	public List<PrivateMessage> getUserSentMessages(@Param("id") Long id);

}
