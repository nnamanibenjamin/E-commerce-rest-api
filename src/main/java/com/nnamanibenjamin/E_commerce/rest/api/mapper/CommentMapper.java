package com.nnamanibenjamin.E_commerce.rest.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CommentDto;
import com.nnamanibenjamin.E_commerce.rest.api.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "userId", source = "user.id")
    CommentDto toDto(Comment comment);
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "product", ignore = true)
    Comment toEntity(CommentDto commentDto);
}
