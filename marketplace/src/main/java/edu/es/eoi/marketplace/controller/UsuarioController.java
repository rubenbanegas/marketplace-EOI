package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.service.UsuarioService;
import edu.es.eoi.marketplace.service.UsuarioServiceImpl;


@RestController
@RequestMapping("/marketplace")
public class UsuarioController {
	
	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDto>> findAll(){
		return new ResponseEntity<List<UsuarioDto>>(usuarioServiceImpl.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id){
		return new ResponseEntity<UsuarioDto>(usuarioServiceImpl.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<String> create(@RequestBody UsuarioDto usuarioDto) { 
		usuarioServiceImpl.create(usuarioDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping("/usuarios/login")
	public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto usuarioDto) { 
		UsuarioDto dto = usuarioServiceImpl.login(usuarioDto);
		if (dto != null) {
			return new ResponseEntity<UsuarioDto>(dto, HttpStatus.OK);			
		}
		return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);	
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto){
		if(id.equals(usuarioDto.getId())){
			usuarioServiceImpl.update(usuarioDto);
			return new ResponseEntity<String>(HttpStatus.OK);						
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);	
	}
}
