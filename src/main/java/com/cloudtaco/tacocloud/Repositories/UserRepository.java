package com.cloudtaco.tacocloud.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.cloudtaco.tacocloud.Domains.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
