package com.example.skiSlope.api;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.UserEditInformationRequest;
import com.example.skiSlope.model.response.TicketResponse;
import com.example.skiSlope.model.response.UserDetailedInformationResponse;
import com.example.skiSlope.service.definitions.UserService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    @GetMapping("/info")
    public UserDetailedInformationResponse getUserDetailedInformation(){
        User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return UserDetailedInformationResponse.builder()
                .username(loggedUser.getUsername())
                .firstName(loggedUser.getFirstName())
                .lastName(loggedUser.getLastName())
                .email(loggedUser.getEmail())
                .build();
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    @PutMapping("/edit")
    public void editUserInformation(@Valid @NonNull @RequestBody UserEditInformationRequest userEditInformationRequest){
        User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateUserData(loggedUser.getId(), userEditInformationRequest);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    @DeleteMapping("/delete")
    public void deleteUser(){
        User loggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteUser(loggedUser.getId());
    }
}

