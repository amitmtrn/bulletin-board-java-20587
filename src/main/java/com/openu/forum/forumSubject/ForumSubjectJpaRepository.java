package com.openu.forum.forumSubject;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository that provides CRUD operations for ForumSubject objects
 * 
 * @author amit and nir
 *
 */
public interface ForumSubjectJpaRepository extends CrudRepository<ForumSubject, Long>{
}
