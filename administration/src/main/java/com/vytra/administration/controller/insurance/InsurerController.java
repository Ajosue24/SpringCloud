package com.vytra.administration.controller.insurance;

import com.vytra.administration.entity.insurance.Insurer;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.insurance.InsurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value ="/insurers")
public class InsurerController {

    @Autowired
    InsurerService service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") long id){
        try{
            return new ResponseEntity<>(service.get(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Insurer insurer, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        try{
            return new ResponseEntity<>(service.saveUpdate(insurer), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Insurer insurer,BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        try{
            return new ResponseEntity<>(service.saveUpdate(insurer), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(Insurer Insurers) {
        Optional<Insurer> insurer = service.get(Insurers.getId());
        if (!insurer.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date date= new Date();
        long time = date.getTime();
        insurer.get().setDeletedAt(date);
        insurer.get().setEnabled(false);
        insurer.get().setNit(insurer.get().getNit()+"_"+time);
        return new ResponseEntity<>( this.service.saveUpdate(insurer.get()),HttpStatus.OK);

    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<?> updateStatus(Insurer Insurers){
        Optional<Insurer> insurer = service.get(Insurers.getId());
        if (!insurer.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        long time = insurer.get().getDeletedAt().getTime();
        insurer.get().setNit(insurer.get().getNit().replaceAll("_" + time + "$", ""));
        insurer.get().setEnabled(true);
        insurer.get().setDeletedAt(null);
        return new ResponseEntity<>( this.service.saveUpdate(insurer.get()),HttpStatus.OK);
    }
}
