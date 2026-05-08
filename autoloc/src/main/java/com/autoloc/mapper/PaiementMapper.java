package com.autoloc.mapper;

import com.autoloc.dto.PaiementRequest;
import com.autoloc.dto.PaiementResponse;
import com.autoloc.model.Paiement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(source = "reservation.id",          target = "reservationId")
    @Mapping(source = "reservation.client.id",   target = "clientId")
    @Mapping(source = "reservation.vehicule.id", target = "vehiculeId")
    @Mapping(source = "modePaiement",            target = "modePaiement")
    @Mapping(source = "statutPaiement",          target = "statutPaiement")
    PaiementResponse toResponse(Paiement paiement);

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "reservation", ignore = true)
    Paiement toEntity(PaiementRequest request);
}