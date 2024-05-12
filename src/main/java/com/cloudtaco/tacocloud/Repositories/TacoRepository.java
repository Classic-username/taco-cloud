package com.cloudtaco.tacocloud.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.cloudtaco.tacocloud.Domains.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{
    
}
