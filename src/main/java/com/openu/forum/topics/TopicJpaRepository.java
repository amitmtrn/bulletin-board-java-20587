package com.openu.forum.topics;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * A repository that provides CRUD operations for Topic objects
 *  
 * @author amit and nir
 *
 */
public interface TopicJpaRepository extends CrudRepository<Topic, Long>{

	/**
	 * Gets the list of topics related to a given subject
	 * @param subjectId - the id of a given subject
	 * @return the list of topics related to the given subject
	 */
	@Query(value="SELECT * FROM topics WHERE subject_id=:subjectId", nativeQuery=true)
	public List<Topic> findTopicsBySubject(@Param("subjectId") Long subjectId);

	/**
	 * Deletes all topics that have created before a given date
	 * @param beforeDate - the given date
	 */
	@Query(value="DELETE FROM topics WHERE created < :date", nativeQuery=true)
	public void deleteByDate(@Param("date") String beforeDate);
}
