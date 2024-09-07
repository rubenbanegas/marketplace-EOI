package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.entity.Usuario;
import edu.es.eoi.marketplace.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioDto> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDto> dtos = new ArrayList<UsuarioDto>();
		
		for (Usuario u : usuarios) {
			dtos.add(EntityToDto(u));
		}
			
		return dtos;
	}
	
	@Override
	public UsuarioDto findById(Integer id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		UsuarioDto dto = EntityToDto(usuario);
		return dto;
	}

	@Override
	public void create(UsuarioDto usuarioDto) {
		Usuario usuario = DtoToEntity(usuarioDto);
		usuario.setId(null);
		
		usuarioRepository.save(usuario);
	}
	
	@Override
	public UsuarioDto login(UsuarioDto usuarioDto) {
		Usuario usuario = DtoToEntity(usuarioDto);
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		for (Usuario u : usuarios) {
			if (usuario.getNombre().equals(u.getNombre()) && usuario.getPassword().equals(u.getPassword())){
				return usuarioDto;
			}
		}
		return null;
	}

	@Override
	public void update(UsuarioDto usuarioDto) {
		usuarioRepository.save(DtoToEntity(usuarioDto));
	}
	
	public UsuarioDto EntityToDto(Usuario usuario) {
		UsuarioDto dto = new UsuarioDto();
		dto.setId(usuario.getId());
		dto.setNombre(usuario.getNombre());
		dto.setPassword(usuario.getPassword());
		return dto;
	}
	
	public Usuario DtoToEntity(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setPassword(usuarioDto.getPassword());
		return usuario;
	}	
}
