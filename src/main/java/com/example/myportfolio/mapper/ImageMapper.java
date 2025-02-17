package com.example.myportfolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.myportfolio.dto.ImageDTO;
import com.example.myportfolio.entity.ImageEntity;

@Mapper
public interface ImageMapper {
	
	
	ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
	
	ImageDTO toDTO(ImageEntity imageEntity);
	
	ImageEntity toEntity(ImageDTO imageDTO);
	

}
