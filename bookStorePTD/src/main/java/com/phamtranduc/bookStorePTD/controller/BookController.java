package com.phamtranduc.bookStorePTD.controller;

import com.phamtranduc.bookStorePTD.entity.Book;
import com.phamtranduc.bookStorePTD.entity.Country;
import com.phamtranduc.bookStorePTD.service.BookServiceImpl;
import com.phamtranduc.bookStorePTD.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    private static final String PATH_FOLDER_IMGBOOK= new File("upload").getAbsolutePath();

    private BookServiceImpl bookService;
    private CountryService countryService;

    public BookController(BookServiceImpl bookService, CountryService countryService) {
        this.countryService=countryService;
        this.bookService = bookService;
    }

//    @GetMapping("/books")
//    public String getListBook(Model model){
//
//        return getPage(model,1,"id","desc");
//    }
    @GetMapping("/admin/books")
    public String getListBookByKeyWord(Model model,@RequestParam(required = false) String keyWord,@RequestParam(required = false) Integer currentPage,
                                       @RequestParam(required = false) String sortBy,@RequestParam(required = false) String sortType){
        if(keyWord==null){
            return  getPage(model,1,"id","desc");

        }
        else {

            Page<Book> pageBook = bookService.getBookByKeyWord(keyWord, currentPage, sortBy, sortType);
            model.addAttribute("listBook", pageBook.getContent());
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalItem", pageBook.getTotalElements());
            model.addAttribute("totalPage", pageBook.getTotalPages());
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("sortType", sortType);
            String reverser = sortType.equals("asc") ? "desc" : "asc";
            model.addAttribute("reverser", reverser);
            return "listBook";
        }

    }

    @GetMapping("/admin/books/{currentPage}")
    public String getPage(Model model, @PathVariable Integer currentPage,
                          @Param("sortBy") String sortBy,@Param("sortType") String sortType){
        Page<Book> temp=bookService.getByPage(currentPage,sortBy,sortType);
        List<Book> bookList=temp.getContent();
        model.addAttribute("listBook",bookList);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItem",temp.getTotalElements());
        model.addAttribute("totalPage",temp.getTotalPages());
        model.addAttribute("sortBy",sortBy);
        model.addAttribute("sortType",sortType);

        String reverser=sortType.equals("asc")?"desc":"asc";
        model.addAttribute("reverser",reverser);

        return "listBook";
    }

    @GetMapping("/admin/formBook")
    public String getFromAddBook(Model model, Book book){
        book=new Book();
        book.setAvailability(true);
        model.addAttribute("book",book);
        model.addAttribute("listCountry",countryService.getAll());
        return "formBook";
    }


    @PostMapping("/admin/addBook")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam(name = "img")MultipartFile file) throws IOException {
        Long id = book.getCountry().getId();
        Country country = countryService.findById(id);
        book.setCountry(country);
        String filePath=file.getOriginalFilename();
        
        
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath=Paths.get(PATH_FOLDER_IMGBOOK,file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        

        if (book.getId() != null) {
            Book existingBook = bookService.findById(book.getId());
            if (existingBook!=null) {
                // Update existing book
                Book managedBook = existingBook;
                managedBook.setName(book.getName());
                managedBook.setDescription(book.getDescription());
                managedBook.setPrice(book.getPrice());
                managedBook.setAvailability(book.getAvailability());
                managedBook.setQuantity(book.getQuantity());
                managedBook.setCountry(book.getCountry());
                managedBook.setPathImg(filePath);
                bookService.addBook(managedBook);
            } else {
                // Save new book
                bookService.addBook(book);
            }
        } else {
            // Save new book
//            book.setPathImg(filePath);
            bookService.addBook(book);
        }

        return "redirect:/admin/books";
    }


    @GetMapping("/admin/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/formEditBook/{id}")
    public String getFormEdit(@PathVariable("id") Long id,Model model){
        model.addAttribute("book",bookService.findById(id));
        model.addAttribute("listCountry",countryService.getAll());
        return "formBook";
    }




}
