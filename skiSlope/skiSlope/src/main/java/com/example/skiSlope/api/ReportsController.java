package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.User;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.CardService;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.aspectj.util.FileUtil;
import org.jfree.io.FileUtilities;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@RequestMapping("/api/report")
@RestController
public class ReportsController {


    private UserService userService;

    private CardService cardService;


    @GetMapping("/customer")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public ResponseEntity<byte[]> generateCustomerReport(HttpServletResponse response) throws RuntimeException, JRException, IOException, FileNotFoundException {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List <Card> cards= cardService.getAllCardsByUserId(loggedUser.getId());

        File file = ResourceUtils.getFile("src/main/resources/tickets.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cards);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("Company", "Srebrne Stoki");
        JasperPrint jasperPrint = null;
        try{
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
        exporter.exportReport();
        byte [] res= pdfOutputStream.toByteArray();
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename= customerReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(res);
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<byte[]> generateManagerReport(HttpServletResponse response) throws RuntimeException, JRException, IOException, FileNotFoundException {


        List<User> users= userService.getAllUsers();
        File file = ResourceUtils.getFile("src/main/resources/customers.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("Company", "Srebrne Stoki");
        JasperPrint jasperPrint = null;
        try{
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
        exporter.exportReport();
        byte [] res= pdfOutputStream.toByteArray();
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename= managerReport.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(res);
    }
}
