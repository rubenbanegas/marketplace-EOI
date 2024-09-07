package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.marketplace.dto.ReportArticuloDto;
import edu.es.eoi.marketplace.dto.ReportPedidoDto;
import edu.es.eoi.marketplace.dto.ReportUsuarioDto;
import edu.es.eoi.marketplace.service.ReportsService;

@RestController
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	ReportsService reportsService;

	@GetMapping("/articulosMasVendidos")
	public ResponseEntity<List<ReportArticuloDto>> findByArticulosMasVendidos() {
		return new ResponseEntity<List<ReportArticuloDto>>(reportsService.findByArticulosMasVendidos(), HttpStatus.OK);
	}

	@GetMapping("/mejoresPedidos")
	public ResponseEntity<List<ReportPedidoDto>> findByMejoresPedidos() {
		return new ResponseEntity<List<ReportPedidoDto>>(reportsService.findByMejoresPedidos(), HttpStatus.OK);
	}

	@GetMapping("/mejoresUsuarios")
	public ResponseEntity<List<ReportUsuarioDto>> findByMejoresUsuarios() {
		return new ResponseEntity<List<ReportUsuarioDto>>(reportsService.findByMejoresUsuarios(), HttpStatus.OK);
	}
}
