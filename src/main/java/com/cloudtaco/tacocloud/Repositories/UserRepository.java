package com.cloudtaco.tacocloud.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.cloudtaco.tacocloud.Domains.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
