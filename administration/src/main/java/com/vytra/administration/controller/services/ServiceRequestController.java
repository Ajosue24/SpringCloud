package com.vytra.administration.controller.services;

import com.vytra.administration.controller.BaseController;
import com.vytra.administration.entity.services.ServiceRequest;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.services.ServiceRequestService;
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
@RequestMapping(value = "/service-request", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class ServiceRequestController extends BaseController<ServiceRequest, ServiceRequestService> {

    @Autowired
    ServiceRequestService service;

    ServiceRequestController(ServiceRequestService service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {
        return super.getAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<?> saveRequest(@Valid @RequestBody ServiceRequest serviceRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        return this.saveOrUpdate(serviceRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id,@RequestBody ServiceRequest serviceRequest) {
        return super.updateOnly(id,serviceRequest);
    }

    public ResponseEntity<?> saveOrUpdate(ServiceRequest serviceRequest){
        return super.save(serviceRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (ServiceRequest serviceRequest){
        return super.delete(serviceRequest);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(ServiceRequest serviceRequest){
        return super.updateStatus(serviceRequest);
    }
}
