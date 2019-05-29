package com.vytra.administration.controller.security;

import com.vytra.administration.Util.Util;
import com.vytra.administration.controller.BaseController;
import com.vytra.administration.entity.security.User;
import com.vytra.administration.entity.security.UserFeature;
import com.vytra.administration.response.JsendResponse;
import com.vytra.administration.service.security.UserFeatureService;
import com.vytra.administration.service.security.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
public class UserController extends BaseController<User, UserService> {
    @Autowired
    private UserService service;
    @Autowired
    private UserFeatureService userFeatureService;


    public UserController(UserService service) {
        super(service);

    }

    @Override
    @GetMapping
    public ResponseEntity<JsendResponse> getAll(@RequestParam(required = false) String filter) {

        Util.logicalDelete((Object)super.get(2).getBody().getData());
        return super.getAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsendResponse> get(@PathVariable("id") long id) {
        return super.get(id);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> campoError = new HashMap<>();
            for (FieldError e : bindingResult.getFieldErrors()) {
                campoError.put(e.getField(), e.getDefaultMessage());
            }
            return new ResponseEntity<>(new JsendResponse<>().sendError(campoError), HttpStatus.BAD_REQUEST);
        }
        //Encoding password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        {
            try {

                AtomicReference<Boolean> rolIsFeature = new AtomicReference<>(false);
                List<UserFeature> userFeatureListJson = new ArrayList<>();
                user.getRolesSet().forEach(x -> {
                    if (x.getFeatures() != null && !x.getFeatures().isEmpty()) {
                        userFeatureListJson.addAll(convertStringJson(x.getFeatures()));
                        rolIsFeature.set(true);
                    }
                });

                if (!userFeatureListJson.
                        stream().map(UserFeature::getKey).collect(Collectors.toList()).containsAll(user.getUserFeatureList()
                        .stream().map(UserFeature::getKey).collect(Collectors.toList()))) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (super.save(user).getStatusCode().equals(HttpStatus.OK)) {
                    if (rolIsFeature.get()) {
                        user.getUserFeatureList().forEach(x -> x.setUserId(user));
                        userFeatureService.saveAll(user.getUserFeatureList());
                    }
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable("id") long id, @RequestBody User user) {
        //Encoding password
        if(user.getPassword()!=null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        return this.updateOnly(id, user);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> logicalDelete(@PathVariable("id") long id){
        return super.logicalDelete(id);
    }

    private List<UserFeature> convertStringJson(String userFeature) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(userFeature, objectMapper.getTypeFactory().constructCollectionType(List.class, UserFeature.class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}