package com.openu.forum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TopicJpaRepository extends CrudRepository<Topic, Long>{

	@Query(value="SELECT * FROM comments WHERE id=:id", nativeQuery=true)
	public List<Comment> findComments(@Param("id") Long id);
}
