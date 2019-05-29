package com.vytra.administration.repository.insurance;

import com.vytra.administration.entity.insurance.Insurer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurerRepository extends CrudRepository<Insurer, Long> {
}
