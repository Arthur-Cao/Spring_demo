package spring_first_intel.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring_first_intel.demo.Service.BookService;
import spring_first_intel.demo.domin.Book;


@Controller
public class BookController {

   @Autowired
    public BookService bookService;

    /**
     *
     * Get list menu
     * @param model
     * @return
     */
    @GetMapping("/books")
//    public String list(Model model,
//                       @RequestParam(defaultValue = "0") int page,
//                       @RequestParam(defaultValue = "5") int size)
//    {
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Page<Book> page1 =bookService.findAllByPage(new PageRequest(page,size,sort));
//        model.addAttribute("page",page1);
//        return "books";
//    }
    public String list(Model model,
                       @PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable)
    {
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Page<Book> page1 =bookService.findAllByPage(pageable);
        model.addAttribute("page",page1);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model)
    {
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "book";
    }

    @GetMapping("books/input")
    public String input(Model model){
        model.addAttribute("book",new Book());
        return "input";
    }

    @PostMapping("/books")
    public String post(Book book,final RedirectAttributes attributes){
        Book bok=bookService.save(book);
        if ( bok!= null){
            attributes.addFlashAttribute("message","<<"+ bok.getName()+">>" +"updated successfully!");
        }
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "input";
    }
    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id,final RedirectAttributes attributes){
        Book book = bookService.findOne(id);
        bookService.delete(id);
        attributes.addFlashAttribute("message","<<"+ book.getName()+">>" +"was deleted successfully!");
        return "redirect:/books";
    }
}
