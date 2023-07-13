package kz.bitlab.finalproject.sneakershop.services;

import kz.bitlab.finalproject.sneakershop.dto.SneakersDTO;
import kz.bitlab.finalproject.sneakershop.mapper.SneakersMapper;
import kz.bitlab.finalproject.sneakershop.repository.SneakersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SneakersService {

    private final SneakersRepository sneakersRepository;
    private final SneakersMapper sneakersMapper;

    public List<SneakersDTO> getSneakers(){
      return sneakersMapper.toDTOList(sneakersRepository.findAll());
    }

    public SneakersDTO addSneakers(SneakersDTO sneakers) {
        return sneakersMapper.toDTO(sneakersRepository.save(sneakersMapper.toModel(sneakers)));
    }

    public SneakersDTO getSneakers(Long id){
        return sneakersMapper.toDTO(sneakersRepository.findById(id).orElse(null));
    }
    public SneakersDTO updateSneakers(SneakersDTO sneakers){
        return sneakersMapper.toDTO(sneakersRepository.save(sneakersMapper.toModel(sneakers)));
    }
    public void deleteSneakers(Long id){
        sneakersRepository.deleteById(id);
    }
}
