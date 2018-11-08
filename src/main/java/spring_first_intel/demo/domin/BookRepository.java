package spring_first_intel.demo.domin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//SQL orders are stored here
public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable page);

    Book findById(long id);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    //List<Book> findByDescriptionEndsWith
    List<Book> findByDescriptionEndingWith(String description);
    //List<Book> findByDescriptionEndW

    @Query("select b from Book b where length(b.name) > ?1")   //数字 1 代表第一个参数
    List<Book> findByJPQl(int Len);

//    @Query("select b from Book b where length(b.name) > :len")
//    List<Book> findByJPQl(@Param int Len);

    @Query(value = "select * from book where  length(author) > ?1 ", nativeQuery = true)
    List<Book> findByQuery(int len);

    @Modifying
    @Query("update Book b set b.status = ?1 where id = ?2")
    int Update(int status,long id);

    @Modifying
    @Query("delete from Book b where b.id = ?1")
    int Delete(long id);

}
