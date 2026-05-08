package com.autoloc.mapper;

import com.autoloc.dto.VehiculeResponse;
import com.autoloc.model.Vehicule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(source = "typeBoiteVitesse", target = "typeBoite")
    @Mapping(target = "nbPortes",  ignore = true)
    @Mapping(target = "nbPlaces",  ignore = true)
    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "volume",    ignore = true)
    @Mapping(target = "longueur",  ignore = true)
    @Mapping(target = "elevator",  ignore = true)
    VehiculeResponse toResponse(Vehicule vehicule);
}