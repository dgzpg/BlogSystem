package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Entity.Visitor;

public interface VisitorRep extends JpaRepository<Visitor, Long>{

}
