package spring_first_intel.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;
import spring_first_intel.demo.Service.BookService;
import spring_first_intel.demo.domin.Book;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApp {
    @Autowired
    private BookService bookService;

    @GetMapping("books")
    public Page<Book> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
//       return bookService.findAll();
        return bookService.findAllByPage(new PageRequest(page,size,sort));
    }

    @PostMapping("/create")
    public Book post(@RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam Integer status
    ){
        Book book= new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);

        return bookService.save(book);
    }
    @PostMapping("findBy")
    public List<Book> findByAuthor(@RequestParam String author){
        return  bookService.findByAuthor(author);
    }

    @PostMapping("find")
    public List<Book> find(@RequestParam String author,@RequestParam int status){
        return bookService.findByAuthorAndStatus(author,status);
    }

    @PostMapping("zhao")
    public List<Book> findByEnds(@RequestParam String description){
        return bookService.findByDescriptionEndWith(description);
    }

    @PostMapping("JPQL")
    public  List<Book> findByJPQL(@RequestParam int len){
        return bookService.findJPQL(len);
    }

    @PostMapping("Query")
    public  List<Book> findByQuery(@RequestParam int Len){
        return  bookService.findByQuery(Len);
    }


    @PostMapping("Update")
    public int  update(@RequestParam int status, @RequestParam long id){
        return bookService.updateStatus(status,id);
    }

//    @PostMapping("Update&Delete")
//    public int  updateAndDelete(@RequestParam int status, @RequestParam long id,@RequestParam long id2){
//        return bookService.updateAndDelete(status,id,id2);
//    }
}
