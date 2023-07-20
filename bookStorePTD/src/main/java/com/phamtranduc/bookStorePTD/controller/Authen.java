package com.phamtranduc.bookStorePTD.controller;

import com.phamtranduc.bookStorePTD.entity.Book;
import com.phamtranduc.bookStorePTD.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Authen {
    private BookService bookService;

    public Authen(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/")
//    public String home(Model model){
////        Page<Book> pageBook=bookService.getBookForClient(1,"id","asc");
////        model.addAttribute("listBook",pageBook.getContent());
////        model.addAttribute("currentPage",)
//        return getPageForClient(model,null,1,"id","asc");
//    }
    @GetMapping("/")
    public String getPageForClient(Model model,@RequestParam(value = "keyWord",required = false) String keyWord,
                                   @RequestParam(name = "currentPage",required = false) Integer currentPage,
                                   @RequestParam(name = "sortBy",required = false)String sortBy,
                                   @RequestParam(name = "sortType",required = false) String sortType
                                   ){
        Page<Book> pageBook=null;
        if(keyWord==null&&currentPage==null) {
            pageBook = bookService.getBookForClient(null,1, "id", "asc");
            model.addAttribute("currentPage", 1);
            model.addAttribute("sortBy", "id");
            model.addAttribute("sortType", "asc");
            model.addAttribute("keyWord",null);
        }
        else if(keyWord==null&& currentPage!=null){
            pageBook = bookService.getBookForClient(null,currentPage, "id", "asc");
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("sortBy", "id");
            model.addAttribute("sortType", "asc");
            model.addAttribute("keyWord",null);
        }

        else{
            pageBook = bookService.getBookForClient(keyWord,currentPage,sortBy,sortType);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("sortType", sortType);
            model.addAttribute("keyWord",keyWord);

        }

        model.addAttribute("totalItem", pageBook.getTotalElements());
        model.addAttribute("totalPage", pageBook.getTotalPages());
        model.addAttribute("listBook", pageBook);

        return "index";
    }
    @GetMapping("/authen")
    public String authen(){
        return "authen";
    }
    @GetMapping("/login")
    public String formLogin(){
        return "formLogin";
    }

    @GetMapping("/admin/meeting")
    public String getMettingAdmin(){
        return "mettingAdmin";
    }

    @GetMapping("/deniedPage")
    public String getPageDenied(){
        return "deniedPage";
    }



}
