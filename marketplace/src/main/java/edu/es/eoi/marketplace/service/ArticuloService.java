package edu.es.eoi.marketplace.service;

import java.util.List;

import edu.es.eoi.marketplace.dto.ArticuloDto;

public interface ArticuloService {
	
	public List<ArticuloDto> findByName(String nombre);
	
	public ArticuloDto findById(Integer id);
	
	public void create(ArticuloDto articuloDto);
	
	public void update(ArticuloDto articuloDto);
}
