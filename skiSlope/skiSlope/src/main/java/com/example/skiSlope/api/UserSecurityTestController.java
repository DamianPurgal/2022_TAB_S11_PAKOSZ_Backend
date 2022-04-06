package com.example.skiSlope.api;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/auth/test")
@RestController
public class UserSecurityTestController {

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/customer")
    public String onlyForCustomers(){
        return "customer";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/manager")
    public String onlyForManagers(){
        return "manager";
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/everyone")
    public String forEveryone(){
        return "everyone";
    }
}
