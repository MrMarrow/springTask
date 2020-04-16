package com.sgu.springTask.repositiry;

import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.mvc.model.Valuta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValutaRepository extends CrudRepository<Valuta, Long> {

    boolean existsByAccCode(String accCode);
}
