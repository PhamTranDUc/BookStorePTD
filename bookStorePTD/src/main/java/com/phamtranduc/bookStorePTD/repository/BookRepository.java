package com.phamtranduc.bookStorePTD.repository;

import com.phamtranduc.bookStorePTD.entity.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long> {

    @Query("SELECT p FROM Book p WHERE p.name like %?1%")
   Page<Book> getBookByKeyWord(String keyword,Pageable pageable);
}
