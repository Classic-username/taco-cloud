package com.cloudtaco.tacocloud.Repositories;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import com.cloudtaco.tacocloud.Domains.Ingredient;

//This Repository will use the first implementation shown, the OrderRepository will use the second.
public interface IngredientRepository extends Repository<Ingredient, String> {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

}