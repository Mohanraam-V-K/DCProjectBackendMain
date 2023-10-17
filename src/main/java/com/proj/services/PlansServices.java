package com.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proj.model.Plans;
import com.proj.repository.PlanRepo;

@Service
public class PlansServices {

    @Autowired
    private PlanRepo planRepo;

    public ResponseEntity<Plans> create(Plans data) {

        if (data != null) {
            Plans res = planRepo.save(data);
            return ResponseEntity.ok().body(res);
        } else {
            return ResponseEntity.status(204).body(null);
        }

    }

    public ResponseEntity<List<Plans>> getAll(){
    	//System.out.println(planRepo.findByPlanId("I1001").getPlanType());
        return ResponseEntity.ok().body(planRepo.findAll());
    }

    public ResponseEntity<Plans> get(String data){
        if(data != null){
            Plans res = planRepo.findByPlanId(data);
            return ResponseEntity.ok().body(res);
        }else{
            return ResponseEntity.status(204).body(null);
        }
    }
    
}