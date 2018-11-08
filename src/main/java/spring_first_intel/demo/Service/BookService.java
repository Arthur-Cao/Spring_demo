package spring_first_intel.demo.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring_first_intel.demo.domin.Book;
import spring_first_intel.demo.domin.BookRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
   return bookRepository.findAll();
    }
    public Book save(Book book){
        return bookRepository.save(book);
    }

    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public  List<Book> findByAuthorAndStatus(String author, int status){

        return  bookRepository.findByAuthorAndStatus(author,status);
    }

    public  List<Book> findByDescriptionEndWith(String description){
        return bookRepository.findByDescriptionEndingWith(description);
    }

    public List<Book> findJPQL(int len){
        return bookRepository.findByJPQl(len);
    }

    public List<Book> findByQuery(int len){
        return bookRepository.findByQuery(len);
    }

    @Transactional
    public int updateStatus(int status,long id){
        return bookRepository.Update(status,id);
    }

//    @Transactional
//    public int updateAndDelete(int status,long id1,long id2){
//
//        int upcount= bookRepository.Update(status,id1);
//        int decount=bookRepository.Delete(id2);
//
//        return upcount+decount;
//    }

    public Book findOne(long id){
        return bookRepository.findById(id);
    }

    public Page<Book> findAllByPage(Pageable pageable){
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable =new PageRequest(1,5,sort);
        return bookRepository.findAll(pageable);
    }

    @Transactional
    public int delete(long id){

        return bookRepository.Delete(id);
    }
}
