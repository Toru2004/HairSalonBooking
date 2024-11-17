package com.admin.controller;

import com.admin.model.Stylist;
import com.admin.model.Care;
import com.admin.service.StylistService;
import com.admin.service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/booknow")
public class BookNowController {

    @Autowired
    private StylistService stylistService;

    @Autowired
    private CareService careService;

    @GetMapping("/stylists")
    public List<Stylist> getStylists() {
        return stylistService.listAll();
    }

    // Lấy danh sách care services
    @GetMapping("/cares")
    public ResponseEntity<List<Care>> getCares() {
        List<Care> cares = careService.getAllCares();
        return ResponseEntity.ok(cares);
    }
}
