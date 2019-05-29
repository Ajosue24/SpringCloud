package com.vytra.services.controller;

import com.vytra.services.response.JsendResponse;
import com.vytra.services.services.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/service/service-category", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class ServiceCategoryController {

    @Autowired
    ServiceCategoryService serviceCategoryService;

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required=false) String filter){
        return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.serviceCategoryService.getAllParents(filter)), HttpStatus.OK);
    }









}
