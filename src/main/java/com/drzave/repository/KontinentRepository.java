package com.drzave.repository;

import com.drzave.model.Kontinent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontinentRepository extends JpaRepository<Kontinent,Long> {
    Kontinent findByNaziv(String naziv);
}
