package com.bioodas.seckill.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 基类接口
 */
public interface BaseService<T> {
	int saveOrUpdate(T t);
    T delete(String id);
    T findById(String id);
    List<T> findAll();
    Page<T> findByPage(Pageable page);
}
