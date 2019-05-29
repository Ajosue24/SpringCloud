package com.vytra.administration.controller.vehicles;


import com.vytra.administration.controller.BaseController;
import com.vytra.administration.entity.vehicles.VehicleType;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.vehicles.VehicleTypeService;
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
@RequestMapping(value = "/vehicle-types", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class VehicleTypeController extends BaseController<VehicleType, VehicleTypeService> {

    protected VehicleTypeController(VehicleTypeService service) {
        super(service);
    }



    @Autowired
    VehicleTypeService service;


    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {
        return super.getAll(filter); }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody VehicleType roles, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String,String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()){
                campoError.put(e.getField(),e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        return super.save(roles);}

    @GetMapping("/{id}")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id) {
        return super.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id,@RequestBody VehicleType vehicleType) {
        return super.updateOnly(id, vehicleType);
    }

}