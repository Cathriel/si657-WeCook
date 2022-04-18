package com.example.demo.domain.repository;

import com.example.demo.domain.model.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {

}