package com.openu.forum.users;

import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<User, Long>{

}
