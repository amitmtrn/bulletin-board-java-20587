package com.openu.forum;

import org.springframework.data.repository.CrudRepository;

public interface CommentJpaRepository extends CrudRepository<Comment, Long> {
}
