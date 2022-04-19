package com.example.skiSlope.api;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@AllArgsConstructor
@RestController
public class GoogleAuthenticationController {

    @PreAuthorize("permitAll()")
    @RequestMapping("/api/login/google")
    public String authorizeByGoogle() {

        return "xD";
    }

}
