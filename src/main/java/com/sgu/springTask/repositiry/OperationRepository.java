package com.sgu.springTask.repositiry;

import com.sgu.springTask.mvc.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
    List<Operation> findAllByIdAccountFrom(Long accountId);
    List<Operation> findAllByIdAccountTo(Long accountId);
}
