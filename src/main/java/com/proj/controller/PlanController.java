package com.proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.model.Plans;
import com.proj.services.PlansServices;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/plans")
public class PlanController {
    
    @Autowired
    private PlansServices plansSer;

    @PostMapping()
    public ResponseEntity<Plans> addPlan( @RequestBody Plans data ){
        return plansSer.create(data);
    }

    @GetMapping()
    public ResponseEntity<List<Plans>> getAllPlans(){
        return plansSer.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plans> getPlan(@PathVariable String id){
        return plansSer.get(id);
    }
    
}