package com.autoloc.mapper;

import com.autoloc.dto.TechnicienRequest;
import com.autoloc.dto.TechnicienResponse;
import com.autoloc.model.Technicien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicienMapper {

    // role (enum userRole) → String via enum.name() automatique
    @Mapping(source = "role", target = "role")
    TechnicienResponse toResponse(Technicien technicien);

    @Mapping(target = "id",                ignore = true)
    @Mapping(target = "role",              ignore = true)
    @Mapping(target = "ordreMaintenances", ignore = true)
    @Mapping(target = "phone",             ignore = true)
    @Mapping(target = "address",           ignore = true)
    Technicien toEntity(TechnicienRequest request);
}
