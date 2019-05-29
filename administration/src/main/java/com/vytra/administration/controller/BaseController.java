package com.vytra.administration.controller;

import com.vytra.administration.Util.Util;
import com.vytra.administration.entity.BaseEntity;
import com.vytra.administration.entity.Company;
import com.vytra.administration.entity.vehicles.Vehicle;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.BaseService;
import com.vytra.administration.service.vehicles.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.*;

public class BaseController<Entity extends BaseEntity, Service extends BaseService<Entity>> {
    private Service service;
    @Autowired
    VehicleService vehicleService;
    protected BaseController(Service service){
        this.service = service;
    }

    public ResponseEntity<JsendResponse> getAll(String filter) {
        //TODO: consultar la compa;ia con el token de usuario logueado en caso de que sea administrador que traiga todo
        Company company = new Company();
        company.setId(1L);
        List<Entity> entityList;
        try {
            entityList = (List<Entity>) this.service.getAll(company);
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(entityList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsendResponse<>().sendFailureBySystem(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<JsendResponse> save(Entity entity) {
        try {
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.service.saveUpdate(entity)), HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<JsendResponse> get(long id) {
        try {
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.service.get(id).orElseThrow(()->new NoSuchElementException("not found"))),HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new JsendResponse<>().jsendNotFount(),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new JsendResponse<>().sendFailureBySystem(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> delete(Entity entity) {

        try {
            Optional<Entity> entityOptional = service.get(entity.getId());
            if (!entityOptional.isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Date date= new Date();
            long time = date.getTime();
            entityOptional.get().setDeletedAt(date);
            entityOptional.get().setEnabled(false);
            this.save(entityOptional.get());
            if (entityOptional.get().getClass().getSimpleName().equalsIgnoreCase("Fleet")){
                List<Vehicle> vehiclesList =  vehicleService.listFleet(entityOptional.get().getId());
                vehiclesList.forEach(x->x.getFleetSet().removeIf(y->y.getId().equals(entityOptional.get().getId())));
                vehicleService.saveAll(vehiclesList);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateStatus(Entity entity) {
        try {
            Optional<Entity> entityOptional = service.get(entity.getId());
            if (!entityOptional.isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            long time = entityOptional.get().getDeletedAt().getTime();
            entityOptional.get().setEnabled(true);
            entityOptional.get().setDeletedAt(null);
            return new ResponseEntity<>(this.save(entityOptional.get()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    protected ResponseEntity<JsendResponse> updateOnly(long id,Entity entity) {
        try {
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.service.updateOnly(id,entity)), HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> logicalDelete(long id) {
        try{
            Entity obj = (Entity) Util.logicalDelete(this.service.get(id).orElseThrow(()->new NoSuchElementException("not found")));
            obj.setDeletedAt(Calendar.getInstance().getTime());
            return new ResponseEntity<>(new JsendResponse<>().sendSuccess(this.service.saveUpdate(obj)), HttpStatus.OK);
        }catch (NoSuchMethodException e){
            return new ResponseEntity<>(new JsendResponse<>().jsendNotFount(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new JsendResponse<>().jsendBadRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        }


    }


}
