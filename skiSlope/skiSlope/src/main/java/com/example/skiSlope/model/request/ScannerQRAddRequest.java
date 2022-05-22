package com.example.skiSlope.model.request;

import com.example.skiSlope.model.ScannerQR;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScannerQRAddRequest {

   @NotBlank
   private String login;

   @NotBlank
   private String password;

   private String title;


   public ScannerQR ScannerAddRequestToScanner(){
       return ScannerQR.builder()
               .login(login)
               .password(password)
               .title(title)
               .build();
   }
}
