package com.es.projectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.es.projectbackend.model.Plane;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, String>{

}
