package com.phamtranduc.bookStorePTD.service;

import com.phamtranduc.bookStorePTD.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBook(Long id);
    public Page<Book> getByPage(int page,String sortBy,String sortType);
    public Book findById(Long id);
    public Page<Book> getBookByKeyWord(String keyWord,int page,String sortBy,String sortType);

    public Page<Book> getBookForClient(String keyWord,int page,String sortBy,String sortType);
}
