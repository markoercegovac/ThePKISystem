package com.pki.app.mapper;

import com.pki.app.dto.DtoEntity;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Data
@Component
public class DtoUtils {
    public DtoEntity convertToDto(Object obj,DtoEntity mapper){
        return new ModelMapper().map(obj,mapper.getClass());
    }
    public Object convertToEntity(Object obj,DtoEntity mapper){
        return new ModelMapper().map(mapper,obj.getClass());
    }
}
