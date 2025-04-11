package com.drzave.repository;

import com.drzave.model.Drzava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava,Long> {
    Drzava findByNaziv(String naziv);
}
