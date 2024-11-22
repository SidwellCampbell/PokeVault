package com.tevthedev.pokedex.repository;

import com.tevthedev.pokedex.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameIgnoreCase(String username);
}
