package com.openu.forum.topics;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.openu.forum.comments.Comment;

public interface TopicJpaRepository extends CrudRepository<Topic, Long>{

	@Query(value="SELECT * FROM comments WHERE id=:id", nativeQuery=true)
	public List<Comment> findComments(@Param("id") Long id);
}
