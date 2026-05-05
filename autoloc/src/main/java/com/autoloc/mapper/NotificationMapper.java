package com.autoloc.mapper;

import com.autoloc.dto.NotificationRequest;
import com.autoloc.dto.NotificationResponse;
import com.autoloc.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "utilisateur.id",        target = "userId")
    @Mapping(source = "utilisateur.firstname", target = "userFirstname")
    @Mapping(source = "utilisateur.lastname",  target = "userLastname")
    NotificationResponse toResponse(Notification notification);

    @Mapping(target = "id",          ignore = true)
    @Mapping(target = "dateEnvoi",   ignore = true)
    @Mapping(target = "utilisateur", ignore = true)
    Notification toEntity(NotificationRequest request);
}
