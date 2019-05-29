package com.vytra.administration.controller;

import com.vytra.administration.entity.Cost;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/cost", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class CostController extends BaseController<Cost, CostService> {

    @Autowired
    private CostService service;

    public CostController(CostService service) {
        super(service);
    }

    @Override
    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {
        return super.getAll(filter);
    }

    @GetMapping(value = "/id")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id) {
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<JsendResponse> save(@Valid @RequestBody Cost cost, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        return super.save(cost);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id,@RequestBody Cost cost) {
        return super.updateOnly(id,cost);
    }



    @DeleteMapping
    public ResponseEntity<?> delete(Cost cost) {
        cost.setDeletedAt(new Date());
        return super.save(cost);
    }
}