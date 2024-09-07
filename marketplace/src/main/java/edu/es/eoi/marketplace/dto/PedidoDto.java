package edu.es.eoi.marketplace.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {
	
	private Integer id;
	
	private String nombre;
	
	private Integer idCliente;
	
	private LocalDate fecha;
	
	private List<CantidadArticulosDto> articulos;
}
