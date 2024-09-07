package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.service.ArticuloService;

@RestController
@RequestMapping("/marketplace")
public class ArticuloController {

	@Autowired
	ArticuloService articuloService;

	@GetMapping("/articulos/{nombreparcial}/nombre")
	public ResponseEntity<List<ArticuloDto>> findByNombre(@PathVariable String nombreparcial) {
		return new ResponseEntity<List<ArticuloDto>>(articuloService.findByName(nombreparcial), HttpStatus.OK);
	}

	@GetMapping("/articulos/{id}")
	public ResponseEntity<ArticuloDto> findById(@PathVariable Integer id) {
		return new ResponseEntity<ArticuloDto>(articuloService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/articulos")
	public ResponseEntity<String> create(@RequestBody ArticuloDto articuloDto) {
		articuloService.create(articuloDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping("/articulos/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody ArticuloDto articuloDto) {
		if (id.equals(articuloDto.getId())) {
			articuloService.update(articuloDto);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
