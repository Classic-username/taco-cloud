package com.cloudtaco.tacocloud.Repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.cloudtaco.tacocloud.Domains.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

}