package com.sgu.springTask.repositiry;

import com.sgu.springTask.mvc.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
    User findByPhone(String phone);

    boolean existsUserByLogin(String login);
}
