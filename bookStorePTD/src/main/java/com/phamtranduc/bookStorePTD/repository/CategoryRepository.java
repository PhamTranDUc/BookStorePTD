package com.phamtranduc.bookStorePTD.repository;

import com.phamtranduc.bookStorePTD.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
