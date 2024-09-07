package edu.es.eoi.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportPedidoDto {

	private Integer idPedido;
	
	private Integer idCliente;
	
	private double totalImporte;
}
