package com.cloudtaco.tacocloud.Controllers;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

import com.cloudtaco.tacocloud.Domains.Ingredient;
import com.cloudtaco.tacocloud.Domains.Ingredient.Type;
import com.cloudtaco.tacocloud.Repositories.IngredientRepository;
import com.cloudtaco.tacocloud.Repositories.OrderRepository;
import com.cloudtaco.tacocloud.Repositories.TacoRepository;
import com.cloudtaco.tacocloud.Repositories.UserRepository;
import com.cloudtaco.tacocloud.Domains.Taco;
import com.cloudtaco.tacocloud.Domains.TacoOrder;
import com.cloudtaco.tacocloud.Domains.Users;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private final OrderRepository orderRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, OrderRepository orderRepo){
        this.ingredientRepo = ingredientRepo;
        this.orderRepo = orderRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }

        // The below has been turned into a DB query
        // List<Ingredient> ingredients = Arrays.asList(
        //     new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        //     new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        //     new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        //     new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        //     new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        //     new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        //     new Ingredient("CHED", "Cheddar", Type.CHEESE),
        //     new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        //     new Ingredient("SLSA", "Salsa", Type.SAUCE),
        //     new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        // );

        // Type[] types = Ingredient.Type.values();

        // for (Type type : types) {
        //     model.addAttribute(type.toString().toLowerCase(),
        //     filterByType(ingredients, type));
        // }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)// The book did NOT tell us this part (at least by page 69 when we refactor this class)
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder, @AuthenticationPrincipal Users user) {

        if(errors.hasErrors()) {
            return "design";
        }
        
        tacoOrder.addTaco(taco);
        tacoOrder.setUser(user);
        log.info("Processing taco: {}", taco);  
    
        return "redirect:/orders/current";
    }
}