package com.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.Plans;

public interface PlanRepo extends JpaRepository<Plans,Long> {
    Plans findByPlanId(String planId);
}