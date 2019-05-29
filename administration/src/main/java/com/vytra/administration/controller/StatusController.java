package com.vytra.administration.controller;

import com.vytra.administration.entity.Status;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/status", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class StatusController extends BaseController<Status, StatusService> {

    @Autowired
    StatusService service;

    StatusController(StatusService service) {
        super(service);
    }

    @Override
    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {
        return super.getAll(filter);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id) {
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<JsendResponse> save(@Valid @RequestBody Status status) {
        return super.save(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id,@RequestBody Status status) {
        return super.updateOnly(id,status);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Status status) {
        status.setDeletedAt(new Date());
        return super.save(status);
    }
}
