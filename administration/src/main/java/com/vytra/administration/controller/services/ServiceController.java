package com.vytra.administration.controller.services;

import com.vytra.administration.controller.BaseController;
import com.vytra.administration.entity.services.Service;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/service")
public class ServiceController extends BaseController<Service, ServiceService> {

    @Autowired
    private ServiceService serviceService;

    ServiceController(ServiceService service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {
        return super.getAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id){
        return  super.get(id);
    }


    @PostMapping
    public ResponseEntity<?> saveRequest(@Valid @RequestBody Service service,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        return this.saveOrUpdate(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id,@RequestBody Service service) {
        return super.updateOnly(id,service);
    }

    private ResponseEntity<?> saveOrUpdate(Service service){
        return super.save(service);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (Service service){
        return super.delete(service);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(Service service){
        return super.updateStatus(service);
    }
}
