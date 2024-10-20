package com.nnamanibenjamin.E_commerce.rest.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nnamanibenjamin.E_commerce.rest.api.dto.CartDto;
import com.nnamanibenjamin.E_commerce.rest.api.dto.CartItemDto;
import com.nnamanibenjamin.E_commerce.rest.api.model.Cart;
import com.nnamanibenjamin.E_commerce.rest.api.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "userId", source = "user.id") 
    CartDto toDto(Cart Cart);

    @Mapping(target = "user.id", source = "userId")
    Cart toEntity(CartDto cartDto);

    @Mapping(target = "productId", source = "product.id")
    CartItemDto toCartItemDto(CartItem cartItem);

    @Mapping(target = "product.id", source = "productId") 
    CartItem toEntity(CartItemDto cartItemDto);
}
