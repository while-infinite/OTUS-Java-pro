package ru.flamexander.spring.data.jdbc.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {
    @Query(
            "select b.id, b.title, b.genre, a.full_name as author_name, bd.description, r.rating," +
                    " AVG(r.rating) as average_rating from BOOKS b " +
                    " left join AUTHORS a on b.author_id = a.id " +
                    " left join BOOKS_DETAILS bd on bd.book_id = b.id" +
                    " left join REVIEWS r on r.book_id = b.id" +
                    " GROUP BY b.id, b.title, b.genre, a.full_name, bd.description, r.rating"
    )
    List<DetailedBookDto> findAllDetailedBooks();

    @Query(
            "select b.id, b.title, b.genre, a.full_name as author_name, bd.description, r.rating," +
                    " AVG(r.rating) as average_rating from BOOKS b " +
                    " left join AUTHORS a on b.author_id = a.id " +
                    " left join BOOKS_DETAILS bd on bd.book_id = b.id" +
                    " left join REVIEWS r on r.book_id = b.id" +
                    " where b.id in(:ids)" +
                    " GROUP BY b.id, b.title, b.genre, a.full_name, bd.description, r.rating"
    )
    List<DetailedBookDto> findAllDetailedBooksByIdIn(List<Long> ids);

    @Query(
            "select b.id, b.title, b.genre, a.full_name as author_name, bd.description, r.rating," +
                    " AVG(r.rating) as average_rating from BOOKS b " +
                    " left join AUTHORS a on b.author_id = a.id " +
                    " left join BOOKS_DETAILS bd on bd.book_id = b.id" +
                    " left join REVIEWS r on r.book_id = b.id" +
                    " GROUP BY b.id, b.title, b.genre, a.full_name, bd.description, r.rating" +
                    " limit :limit offset :offset"
    )
    List<DetailedBookDto> findAllDetailedBooksWithQueryPagination(int offset, int limit);


    @Query(
            value = "select b.id, b.title, b.genre, a.full_name as author_name, bd.description, r.rating," +
                    " (select AVG(rating) from REVIEWS where book_id = b.id) as average_rating from BOOKS b " +
                    " left join AUTHORS a on b.author_id = a.id " +
                    " left join BOOKS_DETAILS bd on bd.book_id = b.id" +
                    " left join REVIEWS r on r.book_id = b.id" +
                    " where r.added >= :start and r.added <= :end" +
                    " GROUP BY b.id, b.title, b.genre, a.full_name, bd.description, r.rating" +
                    " order by r.rating DESC" +
                    " limit 10"
    )
    List<DetailedBookDto> findAllTop10DetailedBooks(LocalDateTime start, LocalDateTime end);

    @Query("update books set title = :title where id = :id")
    @Modifying
    void changeTitleById(Long id, String title);

    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
}