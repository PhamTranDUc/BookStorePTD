package com.phamtranduc.bookStorePTD.repository;

import com.phamtranduc.bookStorePTD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
