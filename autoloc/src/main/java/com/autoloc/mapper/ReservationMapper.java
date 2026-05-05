package com.autoloc.mapper;

import com.autoloc.dto.ReservationResponse;
import com.autoloc.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "client.id",            target = "clientId")
    @Mapping(source = "vehicule.id",          target = "vehiculeId")
    @Mapping(source = "statutReservation",    target = "statut")
    ReservationResponse toResponse(Reservation reservation);
}
