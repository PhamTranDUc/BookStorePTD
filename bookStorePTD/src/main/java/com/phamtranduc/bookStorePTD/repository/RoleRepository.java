package com.phamtranduc.bookStorePTD.repository;

import com.phamtranduc.bookStorePTD.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
