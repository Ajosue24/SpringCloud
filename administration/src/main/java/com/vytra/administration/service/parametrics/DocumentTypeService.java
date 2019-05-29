package com.vytra.administration.service.parametrics;

import com.vytra.administration.entity.parametrics.DocumentType;
import com.vytra.administration.repository.parametrics.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DocumentTypeService {
    @Autowired
    DocumentTypeRepository repository;

    public List<DocumentType> getAll(String filter){
        return (List<DocumentType>) repository.findAll();
    }
}
