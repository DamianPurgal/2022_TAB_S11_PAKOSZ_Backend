package com.example.skiSlope.service.implementations;
import com.example.skiSlope.model.User;
import com.example.skiSlope.repository.UserRepository;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository repository;


    public String exportReport() throws JRException {

        List<User> users= (List<User>) repository.findAll();
        File file= null;
        try {
            file = ResourceUtils.getFile("classpath:customers.jrxlm");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\julia\\OneDrive\\Desktop\\ProjektPP");


        return "report generated";
    }
}
