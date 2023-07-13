package kz.bitlab.finalproject.sneakershop.repository;

import kz.bitlab.finalproject.sneakershop.model.Sneakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakersRepository  extends JpaRepository<Sneakers, Long> {
}
