package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.entity.Articulo;
import edu.es.eoi.marketplace.repository.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	ArticuloRepository articuloRepository;

	@Override
	public List<ArticuloDto> findByName(String nombre) {
		List<Articulo> articulos = articuloRepository.findByNombreContaining(nombre);
		List<ArticuloDto> dtos = new ArrayList<ArticuloDto>();
		
		for (Articulo a : articulos) {
			dtos.add(EntityToDto(a));
		}
		
		return dtos;
	}

	@Override
	public ArticuloDto findById(Integer id) {
		ArticuloDto dto = EntityToDto(articuloRepository.findById(id).get());
		return dto;
	}

	@Override
	public void create(ArticuloDto articuloDto) {
		Articulo articulo = DtoToEntity(articuloDto);
		articulo.setId(null);

		articuloRepository.save(articulo);
	}

	@Override
	public void update(ArticuloDto articuloDto) {
		articuloRepository.save(DtoToEntity(articuloDto));
	}

	public ArticuloDto EntityToDto(Articulo articulo) {
		ArticuloDto dto = new ArticuloDto();
		dto.setId(articulo.getId());
		dto.setNombre(articulo.getNombre());
		dto.setPrecio(articulo.getPrecio());
		dto.setStock(articulo.getStock());
		return dto;
	}

	public Articulo DtoToEntity(ArticuloDto articuloDto) {
		Articulo usuario = new Articulo();
		usuario.setId(articuloDto.getId());
		usuario.setNombre(articuloDto.getNombre());
		usuario.setPrecio(articuloDto.getPrecio());
		usuario.setStock(articuloDto.getStock());
		return usuario;
	}
}
