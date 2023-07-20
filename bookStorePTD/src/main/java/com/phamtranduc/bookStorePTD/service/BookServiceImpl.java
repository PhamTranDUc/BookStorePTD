package com.phamtranduc.bookStorePTD.service;

import com.phamtranduc.bookStorePTD.entity.Book;
import com.phamtranduc.bookStorePTD.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
//        Book managedBook = entityManager.merge(book);

    }

    @Override
    @Transactional
    public void deleteBook(Long id) {

        bookRepository.deleteById(id);
       // bookRepository.deleteBookById(id);

    }



    @Override
    public Page<Book> getByPage(int page,String sortBy,String sortType) {
        Sort sort=Sort.by(sortBy);
        sort=sortType.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable= PageRequest.of(page-1,6,sort);
        return bookRepository.findAll(pageable);
    }


    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Page<Book> getBookByKeyWord(String keyWord,int page, String sortBy, String sortType) {
        Sort sort=Sort.by(sortBy);
        sort=sortType.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable=PageRequest.of(page-1,6,sort);
        Page<Book> pageBook=bookRepository.getBookByKeyWord(keyWord,pageable);

        return pageBook;
    }

    @Override
    public Page<Book> getBookForClient(String keyWord,int page, String sortBy, String sortType) {
        Sort sort=Sort.by(sortBy);
        sort=sortType.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable=PageRequest.of(page-1,8,sort);
        if (keyWord==null){
            return bookRepository.findAll(pageable);
        }
        return bookRepository.getBookByKeyWord(keyWord,pageable);
    }
}
