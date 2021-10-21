package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.response.MessageResponse;

import java.util.List;

public interface DbBasicsService<T> {

    MessageResponse add(T t);
    List<T> findAll();
    T findByID(Long id) throws Exception;
    MessageResponse deleteById(Long id) throws Exception;
    MessageResponse update(T t, Long id) throws Exception;
}
