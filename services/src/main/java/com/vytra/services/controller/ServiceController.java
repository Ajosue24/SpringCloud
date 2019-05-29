package com.vytra.services.controller;

import com.vytra.services.response.JsendResponse;
import com.vytra.services.services.ServiceService;
import com.vytra.services.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service/service", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam Long idServiceCategory){
        Util.userAuth();
        return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.serviceService.getAllByServiceCategory(idServiceCategory)), HttpStatus.OK);
    }






}
