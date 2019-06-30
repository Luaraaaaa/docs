package com.documents.Repositories;

import com.documents.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepo extends JpaRepository<Users, Long> {

    List<Users> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

    Users findById(long id);

    List<Users> findBySurname(String surname);

    List<Users> findByBirthdate(String bt);

}
