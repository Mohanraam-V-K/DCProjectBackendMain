package com.proj.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.Report;

public interface ReportRepo extends JpaRepository<Report,UUID> {
	

}
