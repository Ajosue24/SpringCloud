package com.vytra.administration.controller.vehicles;

import com.vytra.administration.controller.BaseController;
import com.vytra.administration.entity.vehicles.Fleet;
import com.vytra.administration.entity.vehicles.Vehicle;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.vehicles.FleetService;
import com.vytra.administration.service.vehicles.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/fleets", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class FleetController extends BaseController<Fleet, FleetService> {
    @Autowired
    private FleetService fleetService;

    public FleetController(FleetService service){
        super(service);
    }

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required=false) String filter){
        return super.getAll(filter);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<JsendResponse> get(@PathVariable("id") long id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<?> saveRequest(@Valid @RequestBody Fleet fleet, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
         return this.save(fleet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id, @RequestBody Fleet fleet) {
            return this.updateOnly(id, fleet);
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Fleet fleet){
        return super.delete(fleet);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(Fleet fleet){
        return super.updateStatus(fleet);
    }


}
