package edu.es.eoi.marketplace.service;

import java.util.List;

import edu.es.eoi.marketplace.dto.ReportArticuloDto;
import edu.es.eoi.marketplace.dto.ReportPedidoDto;
import edu.es.eoi.marketplace.dto.ReportUsuarioDto;

public interface ReportsService {

	public List<ReportArticuloDto> findByArticulosMasVendidos();

	public List<ReportPedidoDto> findByMejoresPedidos();

	public List<ReportUsuarioDto> findByMejoresUsuarios();
}
