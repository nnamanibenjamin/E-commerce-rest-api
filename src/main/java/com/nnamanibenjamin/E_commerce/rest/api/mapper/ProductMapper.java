package com.nnamanibenjamin.E_commerce.rest.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CommentDto;
import com.nnamanibenjamin.E_commerce.rest.api.dto.ProductDto;
import com.nnamanibenjamin.E_commerce.rest.api.model.Comment;
import com.nnamanibenjamin.E_commerce.rest.api.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "image", source = "image")
    ProductDto toDto(Product product);

    @Mapping(target = "image", source = "image")
    Product toEntity(ProductDto productDto);

    @Mapping(target = "userId",source = "user.id")
    CommentDto toDTO(Comment comment);
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "product", ignore = true)
    Comment toEntity(CommentDto commentDTO);
}
