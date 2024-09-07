package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.ReportArticuloDto;
import edu.es.eoi.marketplace.dto.ReportPedidoDto;
import edu.es.eoi.marketplace.dto.ReportUsuarioDto;
import edu.es.eoi.marketplace.repository.ArticuloRepository;
import edu.es.eoi.marketplace.repository.PedidoRepository;
import edu.es.eoi.marketplace.repository.UsuarioRepository;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	ArticuloRepository articuloRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public List<ReportArticuloDto> findByArticulosMasVendidos() {
		List<Object[]> articulos = articuloRepository.findArticulosMasVendidos();
		List<ReportArticuloDto> articulosDto = new ArrayList<ReportArticuloDto>();

		for (Object[] articulo : articulos) {
			Integer idArticulo = (Integer) articulo[0];
			String nombre = (String) articulo[1];
			Integer stock = (Integer) articulo[2];
			Double cantidadVendida = (Double) articulo[3];
			
			ReportArticuloDto reportArticuloDto = new ReportArticuloDto(idArticulo, nombre, stock, cantidadVendida);

			articulosDto.add(reportArticuloDto);
		}

		return articulosDto;
	}

	@Override
	public List<ReportPedidoDto> findByMejoresPedidos() {
		List<Object[]> mejoresPedidos = pedidoRepository.findByMejoresPedidos();
		List<ReportPedidoDto> mejoresPedidosDto = new ArrayList<>();

		for (Object[] mejorPedido : mejoresPedidos) {
			Integer idPedido = (Integer) mejorPedido[0];
			Integer idCliente = (Integer) mejorPedido[1];
			Double totalImporte = (Double) mejorPedido[2];

			ReportPedidoDto mejorPedidoDto = new ReportPedidoDto(idPedido, idCliente, totalImporte);
			mejoresPedidosDto.add(mejorPedidoDto);
		}
		return mejoresPedidosDto;
	}

	@Override
	public List<ReportUsuarioDto> findByMejoresUsuarios() {
		List<Object[]> mejoresUsuarios = usuarioRepository.findMejoresUsuarios();
		List<ReportUsuarioDto> mejoresUsuariosDto = new ArrayList<>();
		
		for (Object[] mejorUsuario : mejoresUsuarios) {
			Integer idUsuario = (Integer) mejorUsuario[0];
			String nombre = (String) mejorUsuario[1];
			Double totalImporte = (Double) mejorUsuario[2];
			
			ReportUsuarioDto reportUsuarioDto = new ReportUsuarioDto(idUsuario, nombre, totalImporte);
			mejoresUsuariosDto.add(reportUsuarioDto);
		}
		
		return mejoresUsuariosDto;
	}

}
