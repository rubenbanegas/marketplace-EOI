package edu.es.eoi.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportArticuloDto {
	
	private Integer id;
	
	private String nombre;
	
	private Integer stock;
	
	private Double cantidadVendida;
}
