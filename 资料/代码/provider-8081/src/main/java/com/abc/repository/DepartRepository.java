package com.abc.repository;

import com.abc.bean.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartRepository extends JpaRepository<Depart, Integer> {
}
