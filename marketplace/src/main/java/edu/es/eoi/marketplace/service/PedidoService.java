package edu.es.eoi.marketplace.service;

import java.util.List;

import edu.es.eoi.marketplace.dto.PedidoDto;

public interface PedidoService {
	
	public PedidoDto findById(Integer id);
	
	public List<PedidoDto> findByName(String nombre);
	
	public void delete(Integer id);
	
	public void create(PedidoDto pedidoDto);
	
	public void update(PedidoDto pedidoDto);
}
