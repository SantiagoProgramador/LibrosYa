package com.riwi.LibrosYa.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.riwi.LibrosYa.api.dto.request.BookRequest;
import com.riwi.LibrosYa.api.dto.response.BookResponse;
import com.riwi.LibrosYa.domain.model.Book;
import com.riwi.LibrosYa.infrastructure.persistence.BookEntity;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loans",ignore = true)
    @Mapping(target = "reservations",ignore = true)
    public BookResponse bookToBookResponse(Book book);

    public BookEntity bookToBookEntity(Book book);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loans",ignore = true)
    @Mapping(target = "reservations",ignore = true)
    public Book bookRequestToBook(BookRequest bookRequest);

    public Book bookEntityToBook(BookEntity bookEntity);
}
