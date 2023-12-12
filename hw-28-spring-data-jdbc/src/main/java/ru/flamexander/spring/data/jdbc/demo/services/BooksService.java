package ru.flamexander.spring.data.jdbc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.PageDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;
import ru.flamexander.spring.data.jdbc.demo.repositories.BooksRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Transactional
    public PageDto findAllDetailedBooks(int page, int size) {
        //sorting by sql native query
        var offset = page == 0 ? 0 : page * size;
        List<Book> allBooks = booksRepository.findAll();
        var detailBookList = booksRepository.findAllDetailedBooksWithQueryPagination(offset, size);
        int totalPageCount = allBooks.size() / size;
        return new PageDto(
                page,
                size,
                totalPageCount,
                allBooks.size(),
                detailBookList
        );
          //sorting by using ListPagingAndSortingRepository
//        Page<Book> pageBooks = booksRepository.findAll(PageRequest.of(page, size));
//        List<Long> booksId = pageBooks.getContent().stream().map(Book::getId).toList();
//        var detailBookList = booksRepository.findAllDetailedBooksByIdIn(booksId);
//        List<Book> allBooks = booksRepository.findAll();
//        int totalPageCount = allBooks.size() / size;
//        return new PageDto(
//                page,
//                size,
//                totalPageCount,
//                allBooks.size(),
//                detailBookList
//        );
    }

    public List<DetailedBookDto> findAllDetailedBooks() {
        return booksRepository.findAllDetailedBooks();
    }

    public void updateTitleById(Long id, String newTitle) {
        booksRepository.changeTitleById(id, newTitle);
    }

    public List<DetailedBookDto> findAllTop10DetailedBooks() {
        var startDate = LocalDateTime.now().minusDays(30);
        var endDate = LocalDateTime.now();
        return booksRepository.findAllTop10DetailedBooks(startDate, endDate);
    }
}
