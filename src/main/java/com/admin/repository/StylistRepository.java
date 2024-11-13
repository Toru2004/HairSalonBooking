package com.admin.repository;

import com.admin.model.Stylist;
import org.springframework.data.repository.CrudRepository;

public interface StylistRepository extends CrudRepository<Stylist, Integer> {
    public Long countById(Integer id);
}
