package com.cloudtaco.tacocloud.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.cloudtaco.tacocloud.Domains.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {

    TacoOrder save(TacoOrder order);
    
}
