package com.documents.Repositories;

import com.documents.Models.Documents_type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Documents_typeRepo extends CrudRepository<Documents_type, Long> {
    List<Documents_type> findById(long id);

    Documents_type findByType(String type);
}
