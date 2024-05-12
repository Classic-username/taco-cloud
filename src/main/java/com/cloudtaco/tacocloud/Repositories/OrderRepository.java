package com.cloudtaco.tacocloud.Repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.cloudtaco.tacocloud.Domains.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

    TacoOrder save(TacoOrder order);
    
}
