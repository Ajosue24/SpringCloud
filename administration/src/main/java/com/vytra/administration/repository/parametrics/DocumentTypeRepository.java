package com.vytra.administration.repository.parametrics;

import com.vytra.administration.entity.parametrics.DocumentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentTypeRepository  extends CrudRepository<DocumentType,Long> {
}
