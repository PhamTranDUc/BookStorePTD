package com.phamtranduc.bookStorePTD.repository;

import com.phamtranduc.bookStorePTD.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
