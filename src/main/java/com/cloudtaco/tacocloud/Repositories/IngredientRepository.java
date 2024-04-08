package com.cloudtaco.tacocloud.Repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.Repository;
import com.cloudtaco.tacocloud.Domains.Ingredient;

//This Repository will use the first implementation shown, the OrderRepository will use the second.
//Repository doesn't have the deleteAll() method being called in the commandlinerunner in app.java, so I had to change to crudrepository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

}