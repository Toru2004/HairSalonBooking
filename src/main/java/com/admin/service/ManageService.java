package com.admin.service;

import com.admin.model.Manage;
import com.admin.repository.ManageRepository;
import com.admin.exception.ManageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageService {

    @Autowired
    private ManageRepository manageRepository;

    public List<Manage> listAll() {
        return (List<Manage>) manageRepository.findAll();
    }

    public void save(Manage manage) {
        manageRepository.save(manage);
    }

    public Manage get(Integer id) throws ManageNotFoundException {
        Optional<Manage> result = manageRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ManageNotFoundException("Could not find any manage with ID " + id);
    }

    public void delete(Integer id) throws ManageNotFoundException {
        Long count = manageRepository.countById(id);
        if (count == null || count == 0) {
            throw new ManageNotFoundException("Could not find any manage with ID " + id);
        }
        manageRepository.deleteById(id);
    }
}
