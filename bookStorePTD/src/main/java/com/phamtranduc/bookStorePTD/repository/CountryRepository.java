package com.phamtranduc.bookStorePTD.repository;

import com.phamtranduc.bookStorePTD.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
