package com.parkinglot.repository;

import java.util.Collection;

public interface AbstractRepository<E> {
    Collection<E> findAll();

    E findById(Integer id);

    Collection<E> createAll(Collection<E> entities);
}
