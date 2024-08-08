package com.example.CDLibrary.controllers;

import com.example.CDLibrary.model.CD;
import com.example.CDLibrary.services.CdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CDController {

    CdService cdService;

@GetMapping("/compactdisc")
public Iterable<CD>  getAllCDs()  {

    return cdService.findAll();
}

@GetMapping("/compactdisc/{name}")
public Iterable<CD> findAllByArtistName(@PathVariable String name) {
    return cdService.findByArtistNameOrderByTitle(name);
}

    public CdService getCdService() {
        return cdService;
    }

    @Autowired
    public void setCdService(CdService cdService) {
        this.cdService = cdService;
    }
}
