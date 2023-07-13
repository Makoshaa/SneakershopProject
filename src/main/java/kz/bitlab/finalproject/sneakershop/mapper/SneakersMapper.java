package kz.bitlab.finalproject.sneakershop.mapper;

import kz.bitlab.finalproject.sneakershop.dto.SneakersDTO;
import kz.bitlab.finalproject.sneakershop.model.Sneakers;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SneakersMapper {

    SneakersDTO toDTO(Sneakers sneakers);
    Sneakers toModel(SneakersDTO sneakersDTO);
    List<SneakersDTO>toDTOList(List<Sneakers> sneakersList);


}
