package com.nnamanibenjamin.E_commerce.rest.api.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nnamanibenjamin.E_commerce.rest.api.dto.OrderDto;
import com.nnamanibenjamin.E_commerce.rest.api.dto.OrderItemDto;
import com.nnamanibenjamin.E_commerce.rest.api.model.Order;
import com.nnamanibenjamin.E_commerce.rest.api.model.OrderItem;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderItems", source = "items") 
     OrderDto toDto(Order order);

     @Mapping(target = "user.id", source = "userId")
     @Mapping(target = "items", source = "orderItems")
     Order toEntity (OrderDto orderDto); 

     List<OrderDto> toDtos(List<Order> orders); 
     List<Order> toEntities(List<OrderDto> orderDtos); 

    @Mapping(target = "productId", source = "product.id")
    OrderItemDto toOrderItemDto(OrderItem orderItems); 
    @Mapping(target = "product.id", source = "productId") 
    OrderItem  toOrderItemEntities(OrderItemDto orderItemDto);

    List<OrderItemDto> toOrderItemDto(List<OrderItem> orderItems);
    List<OrderItem> toOrderItemEntities(List<OrderItemDto> orderItemDtos); 
} 
