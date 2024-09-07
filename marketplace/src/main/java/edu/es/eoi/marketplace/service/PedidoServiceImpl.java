package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.CantidadArticulosDto;
import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.entity.Articulo;
import edu.es.eoi.marketplace.entity.CantidadArticulos;
import edu.es.eoi.marketplace.entity.Pedido;
import edu.es.eoi.marketplace.repository.ArticuloRepository;
import edu.es.eoi.marketplace.repository.PedidoRepository;
import edu.es.eoi.marketplace.repository.UsuarioRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ArticuloRepository articuloRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public PedidoDto findById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		return EntityToDto(pedido);
	}

	@Override
	public List<PedidoDto> findByName(String nombre) {
		List<Pedido> pedidos = pedidoRepository.findByNombreContaining(nombre);
		List<PedidoDto> pedidosdto = new ArrayList<PedidoDto>();

		for (Pedido p : pedidos) {
			PedidoDto pedidodto = EntityToDto(p);
			pedidosdto.add(pedidodto);
		}

		if (pedidosdto.size() == 0) {
			return null;
		}

		return pedidosdto;
	}

	@Override
	public void delete(Integer id) {
		pedidoRepository.deleteById(id);
	}

	@Override
	public void create(PedidoDto pedidoDto) {
		Pedido pedido = DtoToEntity(pedidoDto);

		for (CantidadArticulos cantidadArticulo : pedido.getCantidadArticulos()) {
			Articulo articulo = cantidadArticulo.getArticulo();
			articulo.setStock(articulo.getStock() - cantidadArticulo.getCantidad());
			articuloRepository.save(articulo);
		}

		pedidoRepository.save(pedido);

	}

	@Override
	public void update(PedidoDto pedidoDto) {

		Pedido pedido = pedidoRepository.findById(pedidoDto.getId()).get();
		pedido.setNombre(pedidoDto.getNombre());
		pedido.setUsuario(usuarioRepository.findById(pedidoDto.getIdCliente()).get());
		pedido.setFecha(pedidoDto.getFecha());

		for (int i = 0; i < pedidoDto.getArticulos().size(); i++) {
			Articulo articulo = pedido.getCantidadArticulos().get(i).getArticulo();

			Integer antiguaCantidad = pedido.getCantidadArticulos().get(i).getCantidad();
			Integer nuevaCantidad = pedidoDto.getArticulos().get(i).getCantidad();

			articulo.setStock(articulo.getStock() - (nuevaCantidad - antiguaCantidad));

			pedido.getCantidadArticulos().get(i).setCantidad(pedidoDto.getArticulos().get(i).getCantidad());
			pedido.getCantidadArticulos().get(i).setArticulo(articulo);
		}

		pedidoRepository.save(pedido);

	}

	public PedidoDto EntityToDto(Pedido pedido) {
		List<CantidadArticulos> cantidadarticulos = pedido.getCantidadArticulos();
		List<CantidadArticulosDto> cantidadarticulosdtos = new ArrayList<CantidadArticulosDto>();

		for (CantidadArticulos c : cantidadarticulos) {
			CantidadArticulosDto cantidadarticulosdto = new CantidadArticulosDto();
			cantidadarticulosdto.setId(c.getArticulo().getId());
			cantidadarticulosdto.setCantidad(c.getCantidad());
			c.getArticulo().setStock(c.getArticulo().getStock());
			cantidadarticulosdtos.add(cantidadarticulosdto);
		}

		PedidoDto dto = new PedidoDto();
		dto.setId(pedido.getId());
		dto.setNombre(pedido.getNombre());
		dto.setIdCliente(pedido.getUsuario().getId());
		dto.setFecha(pedido.getFecha());
		dto.setArticulos(cantidadarticulosdtos);
		return dto;
	}

	public Pedido DtoToEntity(PedidoDto pedidoDto) {
		Pedido pedido = new Pedido();

		pedido.setUsuario(usuarioRepository.findById(pedidoDto.getIdCliente()).orElseThrow(null));
		pedido.setFecha(pedidoDto.getFecha());
		pedido.setNombre(pedidoDto.getNombre());

		List<CantidadArticulos> cantidadArtList = new ArrayList<CantidadArticulos>();

		for (CantidadArticulosDto c : pedidoDto.getArticulos()) {
			CantidadArticulos cantidadArt = new CantidadArticulos();
			cantidadArt.setCantidad(c.getCantidad());
			cantidadArt.setPedido(pedido);
			cantidadArt.setArticulo(articuloRepository.findById(c.getId()).get());

			cantidadArtList.add(cantidadArt);
		}

		pedido.setCantidadArticulos(cantidadArtList);

		return pedido;
	}
}
