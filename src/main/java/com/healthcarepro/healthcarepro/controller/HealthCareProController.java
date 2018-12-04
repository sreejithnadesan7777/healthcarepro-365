package com.healthcarepro.healthcarepro.controller;

import com.healthcarepro.healthcarepro.service.HealthCareProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.SchemaOutputResolver;

@RestController
public class HealthCareProController {
    @Autowired
    private HealthCareProService healthCareProService;

    @GetMapping("/healthcarepro/dataupload")
    public String dataUpload() throws Exception{
        return healthCareProService.execute();
    }
}
