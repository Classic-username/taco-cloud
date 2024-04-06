package com.cloudtaco.tacocloud.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cloudtaco.tacocloud.Domains.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    TacoOrder save(TacoOrder order);
    
}
