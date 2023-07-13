package kz.bitlab.finalproject.sneakershop.api;


import kz.bitlab.finalproject.sneakershop.dto.SneakersDTO;
import kz.bitlab.finalproject.sneakershop.services.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/sneakers")
public class SneakersController {

    private final SneakersService sneakersService;

    @GetMapping(value = "/get-sneakers-list")
    public List<SneakersDTO> getSneakers(){
        return sneakersService.getSneakers();
    }

    @PostMapping(value = "/add-sneakers")
    public SneakersDTO addSneakers(@RequestBody SneakersDTO sneakers) {
        return sneakersService.addSneakers(sneakers);
    }

}
