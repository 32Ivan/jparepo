package com.drzave.service;

import com.drzave.dto.CreateDrzavaDto;
import com.drzave.dto.DrzavaDto;
import com.drzave.maper.DrzavaMapper;
import com.drzave.model.Drzava;
import com.drzave.model.Kontinent;
import com.drzave.repository.DrzavaRepository;
import com.drzave.repository.KontinentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrzavaService {

    private final DrzavaRepository drzavaRepository;
    private final KontinentRepository kontinentRepository;
    private final DrzavaMapper drzavaMapper;


    public List<DrzavaDto> findAll(){
        List<Drzava> drzavasList = drzavaRepository.findAll();

        if (drzavasList.isEmpty()) {
            System.out.println("Nema dostupnih država.");
        }

        return drzavasList.stream().map(drzavaMapper::toDTO).collect(Collectors.toList());
    }

    public DrzavaDto findById(Long id){
        Drzava drzava = drzavaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Država s ID " + id + " nije pronađena"));
        return drzavaMapper.toDTO(drzava);
    }

    public DrzavaDto create(CreateDrzavaDto dto) {
        Kontinent kontinent = kontinentRepository.findByNaziv(dto.getKontinent());

        if (kontinent == null) {
            kontinent = kontinentRepository.findByNaziv("Nepoznat");

            if (kontinent == null) {
                // Ako ni "Nepoznat" ne postoji, možeš ga kreirati
                kontinent = new Kontinent();
                kontinent.setNaziv("Nepoznat");
                kontinent = kontinentRepository.save(kontinent);
            }
        }

        Drzava d = new Drzava();
        d.setNaziv(dto.getNaziv());
        d.setBrojStanovnika(dto.getBrojStanovnika());
        d.setKontinent(kontinent);

        return drzavaMapper.toDTO(drzavaRepository.save(d));
    }


    public DrzavaDto update(Long id, CreateDrzavaDto dto) {
        Drzava drzava = drzavaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Država nije pronađena"));

        // Ažuriranje podataka
        drzava.setNaziv(dto.getNaziv());
        drzava.setBrojStanovnika(dto.getBrojStanovnika());

        // Ako je kontinent promijenjen
        Kontinent kontinent = kontinentRepository.findByNaziv(dto.getKontinent());
        if (kontinent != null) {
            drzava.setKontinent(kontinent);
        }

        return drzavaMapper.toDTO(drzavaRepository.save(drzava));
    }

    public void delete(Long id){
        if (!drzavaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Država nije pronađena");
        }
        drzavaRepository.deleteById(id);
    }

}
