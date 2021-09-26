package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.user.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{

}
