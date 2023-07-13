package kz.bitlab.finalproject.sneakershop.api;


import kz.bitlab.finalproject.sneakershop.dto.SneakersDTO;
import kz.bitlab.finalproject.sneakershop.services.SneakersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/sneakers")
@RequiredArgsConstructor
public class SneakersRestController {

    private final SneakersService sneakersService;

    @GetMapping
    public List<SneakersDTO> sneakersList(){
        return sneakersService.getSneakers();
    }
    @GetMapping(value = "{id}")
    public SneakersDTO getSneakers(@PathVariable(name="id") Long id){
        return sneakersService.getSneakers(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public SneakersDTO addSneakers(@RequestBody SneakersDTO sneakers)
    {
        return sneakersService.addSneakers(sneakers);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping
    public SneakersDTO updateSneakers(@RequestBody SneakersDTO sneakers){
        return sneakersService.updateSneakers(sneakers);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}")
    public void deleteSneakers(@PathVariable(name = "id")Long id){
        sneakersService.deleteSneakers(id);
    }


}
