package com.cloudtaco.tacocloud.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import com.cloudtaco.tacocloud.Domains.TacoOrder; // the use of this import comes later, book said we'll be refactoring the code to store information in a database but for the time being we make it like this.

@Slf4j // this supposedly happens at compile time
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
    
}
