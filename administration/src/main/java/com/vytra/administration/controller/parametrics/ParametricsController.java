package com.vytra.administration.controller.parametrics;

import com.vytra.administration.entity.parametrics.DocumentType;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.parametrics.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/parametrics", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class ParametricsController {

    @Autowired
    DocumentTypeService documentTypeService;



    @GetMapping(value = "/document-types")
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {
        List<DocumentType> entityList;
        try {
            entityList = documentTypeService.getAll(filter);
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(entityList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsendResponse<>().sendFailureBySystem(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

