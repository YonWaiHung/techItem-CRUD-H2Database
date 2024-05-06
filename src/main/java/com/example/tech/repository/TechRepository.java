package com.example.tech.repository;

import com.example.tech.model.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechRepository extends JpaRepository<Tech, Long> {
    List<Tech> findByTechTypeContaining(String techType);
}
