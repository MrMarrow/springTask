package com.sgu.springTask.service;

import com.sgu.springTask.repositiry.ValutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValutaService {

    @Autowired
    ValutaRepository valutaRepository;

    public boolean isValuta(String accCode) {
        return valutaRepository.existsByAccCode(accCode);
    }
}
