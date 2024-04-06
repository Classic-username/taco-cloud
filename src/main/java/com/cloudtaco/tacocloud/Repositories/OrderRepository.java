package com.cloudtaco.tacocloud.Repositories;

import java.util.Optional;

import com.cloudtaco.tacocloud.Domains.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
    
}
