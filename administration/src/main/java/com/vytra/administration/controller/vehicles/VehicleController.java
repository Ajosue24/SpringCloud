package com.vytra.administration.controller.vehicles;

import com.vytra.administration.controller.BaseController;
import com.vytra.administration.entity.vehicles.Fleet;
import com.vytra.administration.entity.vehicles.Vehicle;
import com.vytra.administration.entity.vehicles.VehicleFeature;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.vehicles.FleetService;
import com.vytra.administration.service.vehicles.VehicleFeatureService;
import com.vytra.administration.service.vehicles.VehicleService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/vehicles", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class VehicleController extends BaseController<Vehicle, VehicleService> {

    @Autowired
    VehicleService service;
    @Autowired
    VehicleFeatureService vehicleFeatureService;
    @Autowired
    FleetService fleetService;

    public VehicleController(VehicleService service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter){
        return super.getAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id){
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<?> saveRequest(@Valid @RequestBody Vehicle vehicle,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        return this.saveOrUpdate(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRequest(@PathVariable("id") long id,@RequestBody Vehicle vehicle) {
        return this.updateOnly(id,vehicle);
    }

    public ResponseEntity<?> saveOrUpdate(Vehicle vehicle){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<VehicleFeature> vehicleFeatureListJson = objectMapper.readValue(vehicle.getVehicleType().getFeatures(),objectMapper.getTypeFactory().constructCollectionType(List.class, VehicleFeature.class));
            boolean flag=false;
            for(VehicleFeature vehicleFeacture: vehicle.getVehicleFeatureList() ){
                for(VehicleFeature vehicleFeactureJson: vehicleFeatureListJson){
                    if (vehicleFeacture.getKey().equalsIgnoreCase(vehicleFeactureJson.getKey())){
                        flag=true;
                    }
                }
                if (!flag){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                flag=false;
            }
            super.save(vehicle);
            vehicle.getVehicleFeatureList().forEach(x -> x.setVehicleId(vehicle));
            vehicleFeatureService.saveAll(vehicle.getVehicleFeatureList());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Vehicle vehicles) {
        try {
            Optional<Vehicle> vehicle = service.get(vehicles.getId());
            if (!vehicle.isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Date date= new Date();
            long time = date.getTime();
            vehicle.get().setDeletedAt(date);
            vehicle.get().setEnabled(false);
            vehicle.get().setIdentifier(vehicle.get().getIdentifier()+"_"+time);
            return super.save(vehicle.get());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(Vehicle vehicles){
        try {
            Optional<Vehicle> vehicle = service.get(vehicles.getId());
            if (!vehicle.isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            long time = vehicle.get().getDeletedAt().getTime();
            vehicle.get().setIdentifier(vehicle.get().getIdentifier().replaceAll("_" + time + "$", ""));
            vehicle.get().setEnabled(true);
            vehicle.get().setDeletedAt(null);
            return super.save(vehicle.get());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
