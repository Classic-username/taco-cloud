package com.cloudtaco.tacocloud.Controllers;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
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

    private TacoRepository tacoRepo;

    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo){
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        
        List<Ingredient> ingredients = new ArrayList();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public Users user(Principal principal) {
        String username = principal.getName();
        Users user = userRepo.findByUsername(username);
        return user;
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        log.info("Processing taco: {}", taco);
        
        if(errors.hasErrors()) {
            return "design";
        }
        
        Taco saved = tacoRepo.save(taco);
        tacoOrder.addTaco(saved);
        
        return "redirect:/orders/current";
    }
    
        private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
            return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
        }
    
}