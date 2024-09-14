package com.practice3.practice3.repository;

import com.practice3.practice3.entity.Personentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//never foget to add few properties in application properties id and password and path to store data create or update
@Repository
public interface Personrepository extends JpaRepository<Personentity,Long> {
}
