package com.proj.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.model.BillHistory;

public interface BillHistoryRepo extends JpaRepository<BillHistory,UUID>{
	List<BillHistory> findByemail(String email);

}
