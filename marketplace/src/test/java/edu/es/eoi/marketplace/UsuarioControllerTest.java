package edu.es.eoi.marketplace;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.es.eoi.marketplace.controller.UsuarioController;
import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.service.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

	@Mock
	UsuarioServiceImpl usuarioServiceImpl;

	@InjectMocks
	UsuarioController usuarioController;

	@Test
	public void findAllTest() {

		List<UsuarioDto> usuariosSimulados = new ArrayList<UsuarioDto>();
		usuariosSimulados.add(new UsuarioDto(1, "Ruben", "root"));
		usuariosSimulados.add(new UsuarioDto(2, "Maria", "kali"));
		
		Mockito.when(usuarioServiceImpl.findAll()).thenReturn(usuariosSimulados);

		ResponseEntity<List<UsuarioDto>> esperado = new ResponseEntity<List<UsuarioDto>>(usuariosSimulados, HttpStatus.OK);

		ResponseEntity<List<UsuarioDto>> resultado = usuarioController.findAll();

		Assertions.assertEquals(esperado, resultado);
		
		Mockito.verify(usuarioServiceImpl).findAll();
	}
	
	@Test
	public void findByIdTest() {
		UsuarioDto usuarioSimulado = new UsuarioDto(1, "Ruben", "root");
		
		Mockito.when(usuarioServiceImpl.findById(1)).thenReturn(usuarioSimulado);
		
		ResponseEntity<UsuarioDto> esperado = new ResponseEntity<UsuarioDto>(usuarioSimulado, HttpStatus.OK);
		
		ResponseEntity<UsuarioDto> resultado = usuarioController.findById(1);
		
		Assertions.assertEquals(esperado, resultado);
		
		Mockito.verify(usuarioServiceImpl).findById(1);
	}
}
