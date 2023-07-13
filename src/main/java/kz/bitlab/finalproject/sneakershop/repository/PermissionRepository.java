package kz.bitlab.finalproject.sneakershop.repository;

import kz.bitlab.finalproject.sneakershop.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByRole(String role);

}
