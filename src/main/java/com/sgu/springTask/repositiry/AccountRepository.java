package com.sgu.springTask.repositiry;

import com.sgu.springTask.mvc.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);

    Account findByClientIdAndAccCode(Long clientId, String accCode);
}
