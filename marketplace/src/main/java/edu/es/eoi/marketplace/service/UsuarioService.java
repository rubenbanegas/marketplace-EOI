package edu.es.eoi.marketplace.service;

import java.util.List;

import edu.es.eoi.marketplace.dto.UsuarioDto;

public interface UsuarioService {

	public List<UsuarioDto> findAll();
	
	public UsuarioDto findById(Integer id);
	
	public void create(UsuarioDto usuarioDto);
	
	public UsuarioDto login(UsuarioDto usuarioDto);
	
	public void update(UsuarioDto usuarioDto);
}
