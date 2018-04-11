package com.openu.forum.comments;

import org.springframework.data.repository.CrudRepository;

/**
 * A repository that provides CRUD operations for Comment objects
 *  
 * @author amit and nir
 *
 */
public interface CommentJpaRepository extends CrudRepository<Comment, Long> {
}
