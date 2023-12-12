package ru.flamexander.spring.data.jdbc.demo.dtos;

import java.util.List;

public class PageDto {
    private int pageNumber;
    private int pageSize;
    private int totalPageCount;
    private int totalElements;
    private List<DetailedBookDto> bookDetails;

    public List<DetailedBookDto> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(List<DetailedBookDto> bookDetails) {
        this.bookDetails = bookDetails;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public PageDto(int pageNumber, int pageSize, int totalPageCount, int totalElements, List<DetailedBookDto> bookDetails) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPageCount = totalPageCount;
        this.totalElements = totalElements;
        this.bookDetails = bookDetails;
    }
}

