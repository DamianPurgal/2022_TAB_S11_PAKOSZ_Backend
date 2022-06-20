package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.User;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.CardService;
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
        if(file.exists()){
            System.out.println("exists");
        }
        if(file.canRead())
        {
            System.out.println("canRead");
        }
        System.out.println(file.getAbsolutePath());

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        System.out.println("report compiled");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cards);
        System.out.println("T1");
        Map<String, Object> parameters= new HashMap<>();
        System.out.println("T2");
        parameters.put("Company", "Srebrne Stoki");
        System.out.println("T3");
        System.out.println(parameters.toString());
        System.out.println(jasperReport.getCompileData().toString());
        System.out.println(jasperReport.getName().toString());
        System.out.println(jasperReport.getColumnCount());
        System.out.println(dataSource.getData().toString());
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        System.out.println("T4");
        JRPdfExporter exporter = new JRPdfExporter();
        System.out.println("T5");
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        System.out.println("T6");
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        System.out.println("T7");
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
        System.out.println("T8");
        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        System.out.println("T9");
        reportConfig.setSizePageToContent(true);
        System.out.println("T10");
        reportConfig.setForceLineBreakPolicy(false);
        System.out.println("T11");

        exporter.exportReport();
        System.out.println("T12");
        byte [] res= pdfOutputStream.toByteArray();
        System.out.println("T13");
        var headers = new HttpHeaders();
        System.out.println("T14");
        headers.add("Content-Disposition", "inline; filename= customerReport.pdf");
        System.out.println("T15");
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
        File file = ResourceUtils.getFile("./skiSlope/skiSlope/src/main/resources/customers.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("Company", "Srebrne Stoki");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters, dataSource);
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
