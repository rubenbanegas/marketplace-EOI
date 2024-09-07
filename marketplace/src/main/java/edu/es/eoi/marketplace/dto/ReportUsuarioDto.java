package edu.es.eoi.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportUsuarioDto {

	private Integer id;
	
	private String nombre;
	
	private double total_importe;
}
