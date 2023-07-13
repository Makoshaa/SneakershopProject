package kz.bitlab.finalproject.sneakershop;

import kz.bitlab.finalproject.sneakershop.dto.SneakersDTO;
import kz.bitlab.finalproject.sneakershop.mapper.SneakersMapper;
import kz.bitlab.finalproject.sneakershop.model.Sneakers;
import kz.bitlab.finalproject.sneakershop.repository.SneakersRepository;
import kz.bitlab.finalproject.sneakershop.services.SneakersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestApp {

	@Autowired
	private SneakersMapper sneakersMapper;

	@Autowired
	private SneakersService sneakersService;

	@Autowired
	private SneakersRepository sneakersRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void checkSneakersDTO(){
		Sneakers sneakers=new Sneakers();
		sneakers.setId(15l);
		sneakers.setName("Kyrie 5");
		sneakers.setColor("White");
		sneakers.setPrice(150);
		SneakersDTO sneakersDTO=sneakersMapper.toDTO(sneakers);
		assertEquals(sneakers.getId(), sneakersDTO.getId());
		assertEquals(sneakers.getName(), sneakersDTO.getName());
		assertEquals(sneakers.getColor(), sneakersDTO.getColor());
		assertEquals(sneakers.getPrice(), sneakersDTO.getPrice());
	}


	@Test
	public void testToDTO() {
		// Создание тестовых данных
		Sneakers sneakers = new Sneakers();
		sneakers.setId(1L);
		sneakers.setName("Sneakers 1");
		sneakers.setColor("Red");
		sneakers.setPrice(99);

		// Метод toDTO должен выполнять маппинг объекта Sneakers в SneakersDTO
		SneakersDTO result = sneakersMapper.toDTO(sneakers);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Sneakers 1", result.getName());
		assertEquals("Red", result.getColor());
		assertEquals(99, result.getPrice());
	}

	@Test
	public void testToModel() {
		// Создание тестовых данных
		SneakersDTO sneakersDTO = new SneakersDTO();
		sneakersDTO.setId(1L);
		sneakersDTO.setName("Sneakers 1");
		sneakersDTO.setColor("Red");
		sneakersDTO.setPrice(99);

		// Метод toModel должен выполнять маппинг объекта SneakersDTO в Sneakers
		Sneakers result = sneakersMapper.toModel(sneakersDTO);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Sneakers 1", result.getName());
		assertEquals("Red", result.getColor());
		assertEquals(99, result.getPrice());
	}

	@Test
	public void testToDTOList() {
		// Создание тестовых данных
		List<Sneakers> sneakersList = new ArrayList<>();
		Sneakers sneakers1 = new Sneakers();
		sneakers1.setId(1L);
		sneakers1.setName("Sneakers 1");
		sneakers1.setColor("Red");
		sneakers1.setPrice(99);
		sneakersList.add(sneakers1);
		Sneakers sneakers2 = new Sneakers();
		sneakers2.setId(2L);
		sneakers2.setName("Sneakers 2");
		sneakers2.setColor("Blue");
		sneakers2.setPrice(149);
		sneakersList.add(sneakers2);

		// Метод toDTOList должен выполнять маппинг списка объектов Sneakers в список объектов SneakersDTO
		List<SneakersDTO> result = sneakersMapper.toDTOList(sneakersList);

		assertNotNull(result);
		assertEquals(2, result.size());

		SneakersDTO resultSneakers1 = result.get(0);
		assertEquals(1L, resultSneakers1.getId());
		assertEquals("Sneakers 1", resultSneakers1.getName());
		assertEquals("Red", resultSneakers1.getColor());
		assertEquals(99, resultSneakers1.getPrice());

		SneakersDTO resultSneakers2 = result.get(1);
		assertEquals(2L, resultSneakers2.getId());
		assertEquals("Sneakers 2", resultSneakers2.getName());
		assertEquals("Blue", resultSneakers2.getColor());
		assertEquals(149, resultSneakers2.getPrice());
	}


	@Test
	public void testGetSneakers_NonexistentSneakers() {
		// Метод getSneakers должен возвращать null, если кроссовки не существуют
		SneakersDTO result = sneakersService.getSneakers(1L);
		assertNull(result);
	}

	@Test
	public void testAddSneakers_ValidSneakers() {
		// Создание тестовых данных
		SneakersDTO sneakersDTO = new SneakersDTO();
		sneakersDTO.setName("Sneakers 1");
		sneakersDTO.setColor("Red");
		sneakersDTO.setPrice(99);

		// Вызов метода addSneakers
		SneakersDTO result = sneakersService.addSneakers(sneakersDTO);

		// Проверка результата
		assertNotNull(result);
		assertNotNull(result.getId()); // Проверяем, что у кроссовок присвоен ID
		assertEquals("Sneakers 1", result.getName());
		assertEquals("Red", result.getColor());
		assertEquals(99, result.getPrice());
	}

	@Test
	public void testDeleteSneakers_ExistingSneakers() {
		// Создание тестовых данных
		Sneakers sneakers = new Sneakers();
		sneakers.setId(1L);
		sneakers.setName("Sneakers 1");
		sneakers.setColor("Red");
		sneakers.setPrice(99);
		sneakersRepository.save(sneakers);

		// Вызов метода deleteSneakers
		sneakersService.deleteSneakers(1L);

		// Проверка, что кроссовки удалены
		Sneakers deletedSneakers = sneakersRepository.findById(1L).orElse(null);
		assertNull(deletedSneakers);
	}



}
