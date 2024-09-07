package edu.es.eoi.marketplace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticuloDto {
	
	private Integer id;
	
	private String nombre;
	
	private Double precio;
	
	private Integer stock;
}
