package com.autoloc.mapper;

import com.autoloc.dto.ClientResponse;
import com.autoloc.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    // permisConduire.categorie → String via enum.name() automatique MapStruct
    @Mapping(source = "permisConduire.categorie", target = "permisCategorie")
    ClientResponse toResponse(Client client);
}
