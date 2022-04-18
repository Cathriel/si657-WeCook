package com.example.demo.domain.repository;

import com.example.demo.domain.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MultimediaRepository extends JpaRepository<Multimedia,Long> {

}
