package com.autoloc.mapper;

import com.autoloc.dto.MaintenanceResponse;
import com.autoloc.model.OrdreMaintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    @Mapping(source = "vehicule.id",              target = "vehiculeId")
    @Mapping(source = "vehicule.marque",          target = "vehiculeMarque")
    @Mapping(source = "vehicule.modele",          target = "vehiculeModele")
    @Mapping(source = "vehicule.immatriculation", target = "vehiculeImmatriculation")
    @Mapping(source = "technicien.id",            target = "technicienId")
    @Mapping(source = "technicien.firstname",     target = "technicienPrenom")
    @Mapping(source = "technicien.lastname",      target = "technicienNom")
    MaintenanceResponse toResponse(OrdreMaintenance ordre);
}
