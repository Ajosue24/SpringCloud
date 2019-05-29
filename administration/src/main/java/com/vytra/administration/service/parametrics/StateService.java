package com.vytra.administration.service.parametrics;

import com.vytra.administration.entity.parametrics.State;
import com.vytra.administration.repository.parametrics.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StateService {
    @Autowired
    StateRepository repository;

    public List<State> getAll(){
        return (List<State>) repository.findAll();
    }

}
