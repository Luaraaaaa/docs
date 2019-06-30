package com.documents.Repositories;

import com.documents.Models.Documents;
import com.documents.Models.Documents_type;
import com.documents.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.awt.*;
import java.util.List;

public interface DocumentsRepo extends JpaRepository<Documents, Long> {

    List<Documents> findByNumber(long number);

    Documents findByDocumentsTypeAndUser(Documents_type dt, Users users);

    Documents findById(long id);
}
