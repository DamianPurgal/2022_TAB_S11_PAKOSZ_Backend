package com.example.skiSlope.api;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.UserEditInformationRequest;
import com.example.skiSlope.model.response.UserDetailedInformationResponse;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.ReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private ReportService service;


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

    @GetMapping("/report/html")
    public String generateReport() throws JRException {


        List<User> users= userService.getAllUsers();
        File file= null;
        try {
            file = ResourceUtils.getFile("C:\\Users\\julia\\OneDrive\\Desktop\\ProjektPP\\PP_Backend\\2022_TAB_S11_PAKOSZ_Backend\\skiSlope\\skiSlope\\src\\main\\resources\\customers.jrxml");
        } catch (FileNotFoundException e) {
            return "ERROR!";
        }
        JasperReport jasperReport= null;
        try {
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("createdBy", "SkiSlope");
        JasperPrint jasperPrint;
        try {
            jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

         JasperExportManager.exportReportToPdfFile(jasperPrint, "raport.pdf");
        return "report generated";


    }

}

