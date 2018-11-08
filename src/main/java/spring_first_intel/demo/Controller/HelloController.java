package spring_first_intel.demo.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/say")
    public String hello(){

        return "Hello,World!";
    }

//    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)

    //@PathVariable get variable input from url
//    public Object getOne(@PathVariable String id){
////        Map<String,Object> book=new HashMap<>();
////        book.put("Name","Computer");
////        book.put("isbn",1234);
////        book.put("id","000");
////        if(book.get("id")==id)
////        return book.get("id");
////        else{return "do not have!";}
//        return null;
//    }

//    @PostMapping("/books")
//    //@RequestParam get variable input from front end
//    public Object createOne(@RequestParam String name,
//                            @RequestParam String isbn,
//                            @RequestParam String author){
//        Map<String,Object> book=new HashMap<>();
//        book.put("name",name);
//        book.put("isbn",isbn);
//        book.put("author",author);
//        return  book;

    //}
}
