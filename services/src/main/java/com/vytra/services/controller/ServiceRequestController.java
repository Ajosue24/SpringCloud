package com.vytra.services.controller;

import com.vytra.services.entity.services.ServiceRequest;
import com.vytra.services.response.JsendResponse;
import com.vytra.services.services.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/service/service-request", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class ServiceRequestController {

@Autowired
ServiceRequestService serviceRequestService;

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required=false) String filter){
        return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.serviceRequestService.getAllServiceRequestByUser(filter)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JsendResponse> saveRequest(@Valid @RequestBody ServiceRequest serviceRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.serviceRequestService.save(serviceRequest)),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new JsendResponse<>().sendFailureBySystem(),HttpStatus.BAD_REQUEST);
        }

    }

}
