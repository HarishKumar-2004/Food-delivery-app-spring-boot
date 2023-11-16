package com.example.Food_app.repository;

import com.example.Food_app.domain.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuredUserRepository extends JpaRepository<SecuredUser,Integer> {

    SecuredUser findByUsername(String name);
}
