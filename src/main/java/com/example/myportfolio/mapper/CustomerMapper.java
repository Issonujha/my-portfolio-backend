package com.example.myportfolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.myportfolio.dto.CustomerDTO;
import com.example.myportfolio.entity.Customer;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDTO toDTO(Customer customer);

	Customer toEntity(CustomerDTO customerDto);

}
