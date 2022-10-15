package ec.com.imusic.config;


import ec.com.imusic.dto.ClientDTO;
import ec.com.imusic.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "CDI",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {
    @Mapping(target = "id", ignore = true)
    Client toClient(ClientDTO c);

    @Mapping(target="id", expression = "java(c.getId())")
    ClientDTO toDto(Client c);

}
