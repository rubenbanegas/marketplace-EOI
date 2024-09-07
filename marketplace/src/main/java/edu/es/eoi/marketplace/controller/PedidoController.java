package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.service.PedidoService;

@RestController
@RequestMapping("/marketplace")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<PedidoDto> findById(@PathVariable Integer id){
		return new ResponseEntity<PedidoDto>(pedidoService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/pedidos/{nombreparcial}/nombre")
	public ResponseEntity <List<PedidoDto>> findById(@PathVariable String nombreparcial){
		List<PedidoDto> pedidosDto = pedidoService.findByName(nombreparcial);
		
		if (pedidosDto != null) {
			return new ResponseEntity<List<PedidoDto>>(pedidosDto, HttpStatus.OK);			
		}
		return new ResponseEntity<List<PedidoDto>>(HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping("/pedidos/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		pedidoService.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping("/pedidos")
	public ResponseEntity<String> create(@RequestBody PedidoDto pedidoDto){
		pedidoService.create(pedidoDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping("/pedidos/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody PedidoDto pedidoDto){
		
		if (id.equals(pedidoDto.getId())) {
			pedidoService.update(pedidoDto);
			return new ResponseEntity<String>(HttpStatus.OK);			
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
