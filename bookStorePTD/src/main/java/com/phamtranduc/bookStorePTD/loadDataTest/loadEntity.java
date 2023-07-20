package com.phamtranduc.bookStorePTD.loadDataTest;

import com.phamtranduc.bookStorePTD.entity.*;
import com.phamtranduc.bookStorePTD.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loadEntity {

    private BookRepository bookRepository;
    private CountryRepository countryRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public loadEntity(BookRepository bookRepository, CountryRepository countryRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository,UserRepository userRepository,RoleRepository roleRepository) {
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    @GetMapping("/load1")
    public void loadBookVsCountry(){
        Country country1=new Country("VietNam","VN");
        Country country2=new Country("Japan","JP");
        Country country3=new Country("America","US");

        Book book1=new Book("Code java with PTD",10.5,true,100);
        Book book2= new Book("Cook with VHA",99.9,true,200);
        Book book3= new Book("Cách học tất cả trong 90 phút ",11.9,true,50);

        country3.addBook(book1);
        country3.addBook(book2);
        country1.addBook(book3);
        countryRepository.save(country1);
        countryRepository.save(country3);
    }
    @GetMapping("/load2")
    public void loadBookvsAuthor(){
//        Author author1=new Author("Phạm Trần Đức");
        Author author2=new Author("Vũ Hạ An");
        Book book1=bookRepository.findById(1L).get();
        book1.addAuthor(author2);
        bookRepository.save(book1);
    }

    @GetMapping("load3")
    public void loadBookvsCategory(){
        Book book1=bookRepository.findById(1L).get();
        Book book2=bookRepository.findById(2L).get();
        Book book3=bookRepository.findById(3L).get();

        Category category1=new Category("Lập trình");
        Category category2=new Category("Nấu ăn");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        book1.addCategory(category1);
        book2.addCategory(category1);
        book3.addCategory(category2);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
    @GetMapping("/load4")
    public void testLoad(){
        Category category=categoryRepository.getById(4L);
        Book book1=bookRepository.findById(1L).get();
        book1.addCategory(category);
        bookRepository.save(book1);
    }

    @GetMapping("/load5")
    public void testLoadUR(){
        User user1=new User("phamduc","12345","Pham Tran Duc","0862293627","duc@gmail.com","PhuTho");
        User user2=new User("haan","12345","Vu Ha An","0911111111","an@gmail.com","LangSon");
        Role role1=new Role("ADMIN");
        Role role2=new Role("USER");
        roleRepository.save(role1);
        roleRepository.save(role2);
        userRepository.save(user1);
        userRepository.save(user2);
        user1.addRole(role1);
        user1.addRole(role2);
        user2.addRole(role2);

        userRepository.save(user1);
        userRepository.save(user2);


    }

    @GetMapping("/testDelete")
    public String testDelete(){
        bookRepository.deleteById(6L);
        return "redirect:/admin/books";
    }
}
