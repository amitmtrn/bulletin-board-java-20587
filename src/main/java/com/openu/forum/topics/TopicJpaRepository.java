package com.openu.forum.topics;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TopicJpaRepository extends CrudRepository<Topic, Long>{

	@Query(value="SELECT * FROM topics WHERE subject_id=:subjectId", nativeQuery=true)
	public List<Topic> findTopicsBySubject(@Param("subjectId") Long subjectId);

	@Query(value="DELETE FROM topics WHERE created > :date", nativeQuery=true)
	public void deleteByDate(@Param("date") String beforeDate);
}
