package com.admin.service;

import com.admin.model.Stylist;
import com.admin.repository.StylistRepository;
import com.admin.exception.StylistNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StylistService {

    @Autowired
    private StylistRepository stylistRepository;


    public List<Stylist> listAll() {
        return (List<Stylist>) stylistRepository.findAll();
    }

    public void save(Stylist stylist) {
        stylistRepository.save(stylist);
    }

    public Stylist get(Integer id) throws StylistNotFoundException {
        Optional<Stylist> result = stylistRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new StylistNotFoundException("Could not find any stylist with ID " + id);
    }

    public void delete(Integer id) throws StylistNotFoundException {
        Long count = stylistRepository.countById(id);
        if (count == null || count == 0) {
            throw new StylistNotFoundException("Could not find any stylist with ID " + id);
        }
        stylistRepository.deleteById(id);
    }
}
