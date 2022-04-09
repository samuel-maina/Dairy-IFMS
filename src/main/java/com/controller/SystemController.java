/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.service.SystemService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author samuel
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @GetMapping("/logs")
    public ResponseEntity<?> systemLogs() {
        return new ResponseEntity<>(systemService.logFiles(), HttpStatus.OK);
    }
       @GetMapping("/logs/download/{file_}") 
   public ResponseEntity<Object> downloadFile(@PathVariable String file_) {
      String filename = "/home/samuel/logger/"+file_;
      File file = new File(filename);
      InputStreamResource resource=null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
      HttpHeaders headers = new HttpHeaders();
      
      headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
      headers.add("Pragma", "no-cache");
      headers.add("Expires", "0");
      
      ResponseEntity<Object> 
      responseEntity = ResponseEntity.ok().headers(headers).contentLength(
         file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
      
      return responseEntity;

}
}
