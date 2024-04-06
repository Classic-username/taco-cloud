package com.cloudtaco.tacocloud.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; //Mans forgot this import in the book D:<
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import com.cloudtaco.tacocloud.Domains.TacoOrder;
import com.cloudtaco.tacocloud.Repositories.OrderRepository;

@Slf4j 
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping //book loves to leave out the new imports for things that "oops now this comes later"
    public String processOrder (@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()){
            return "orderForm";
        }

        orderRepo.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
    
}
