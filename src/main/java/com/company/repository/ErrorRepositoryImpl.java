package com.company.repository;

import com.company.model.ErrorReport;
import com.company.model.web.ErrorResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ErrorRepositoryImpl implements ErrorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ErrorReport save(ErrorReport errorReport) {
        entityManager.persist(errorReport);
        return errorReport;
    }
}