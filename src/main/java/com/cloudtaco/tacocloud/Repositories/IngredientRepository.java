package com.cloudtaco.tacocloud.Repositories;

import java.util.Optional;

import com.cloudtaco.tacocloud.Domains.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

}