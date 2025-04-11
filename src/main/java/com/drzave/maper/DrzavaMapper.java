package com.drzave.maper;

import com.drzave.dto.CreateDrzavaDto;
import com.drzave.dto.DrzavaDto;
import com.drzave.model.Drzava;
import org.springframework.stereotype.Component;

@Component
public class DrzavaMapper {

    public DrzavaDto toDTO(Drzava d) {
        DrzavaDto dto = new DrzavaDto();
        dto.setId(d.getId());
        dto.setNaziv(d.getNaziv());
        dto.setBrojStanovnika(d.getBrojStanovnika());
        dto.setKontinent(d.getKontinent().getNaziv());
        return dto;
    }


}
