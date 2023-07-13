package kz.bitlab.finalproject.sneakershop.repository;

import kz.bitlab.finalproject.sneakershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
        User findByEmail(String email);
}
