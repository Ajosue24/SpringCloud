package com.vytra.administration.service.insurance;

import com.vytra.administration.entity.insurance.Insurer;
import com.vytra.administration.repository.insurance.InsurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(rollbackFor = Exception.class)
public class InsurerService {

    @Autowired
    InsurerRepository repository;

    public Iterable<Insurer> getAll() {
        return repository.findAll();
    }

    public Optional<Insurer> get(Long id) {
        Optional<Insurer> insurer = this.repository.findById(id);
        return insurer;
    }

    public Insurer saveUpdate(Insurer insurer) {
        try {
            return repository.save(insurer);
        } catch (Exception e) {
            return null;
        }
    }

    public void saveAll(Iterable<Insurer> iterable) {
        repository.saveAll(iterable);
    }
}
