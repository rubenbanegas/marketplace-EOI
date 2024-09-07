package edu.es.eoi.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.es.eoi.marketplace.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	public List<Pedido> findByNombreContaining(String nombre);
	
	@Query(value = "SELECT pe.id, pe.idcliente, SUM(ca.cantidad * a.precio) AS total_importe "
			+ "FROM pedido pe "
			+ "JOIN cantidad_articulos ca ON pe.id = ca.idpedido "
			+ "JOIN articulo a ON ca.idarticulo = a.id "
			+ "GROUP BY pe.id "
			+ "ORDER BY total_importe DESC "
			+ "LIMIT 3;", nativeQuery = true)
	public List<Object[]> findByMejoresPedidos();
}
