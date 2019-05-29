package com.vytra.administration.repository;

import com.vytra.administration.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long> {
    Iterable<T> findAllByCompany(Company company);
}
