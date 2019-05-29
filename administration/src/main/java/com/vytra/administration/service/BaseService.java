package com.vytra.administration.service;


import com.vytra.administration.AdministrationApplication;
import com.vytra.administration.Util.Util;
import com.vytra.administration.entity.BaseEntity;
import com.vytra.administration.entity.Company;
import com.vytra.administration.entity.parametrics.Country;
import com.vytra.administration.entity.parametrics.DocumentType;
import com.vytra.administration.entity.security.User;
import com.vytra.administration.repository.BaseRepository;
import com.vytra.administration.service.security.UserService;

import javax.swing.text.html.parser.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class BaseService<T extends BaseEntity> {

    private BaseRepository<T> repository;

    protected BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public T saveUpdate(T entity) throws Exception {
        return this.repository.save(entity);
    }

    public Optional<T> get(Long id) throws Exception {
        return this.repository.findById(id);
    }

    public Iterable<T> getAll(Company company) throws Exception {
        return this.repository.findAllByCompany(company);
    }

    public void deleteById(Long id) throws Exception {
        this.repository.deleteById(id);
    }

    /**
     * Get only data is been changed
     */
    public T updateOnly(Long id,T entity)throws Exception{
        Object obj = Util.merge(this.repository.findById(id).get(),entity);
        return this.repository.save((T)obj);
    }


}



