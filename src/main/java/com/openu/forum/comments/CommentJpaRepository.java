package com.openu.forum.comments;

import org.springframework.data.repository.CrudRepository;

public interface CommentJpaRepository extends CrudRepository<Comment, Long> {
}
