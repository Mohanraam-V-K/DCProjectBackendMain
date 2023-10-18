package com.proj.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.Report;

public interface ReportRepo extends JpaRepository<Report,UUID> {
	List<Report> findBycustomeremail(String email);

}
