package edu.es.eoi.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.es.eoi.marketplace.entity.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

	public List<Articulo> findByNombreContaining(String nombre);

	@Query(value = "SELECT a.id, a.nombre, a.stock, SUM(ca.cantidad) AS cantidadvendida "
			+ "FROM articulo a "
			+ "JOIN cantidad_articulos ca ON a.id = ca.idarticulo "
			+ "GROUP BY a.id, a.nombre, a.precio, a.stock "
			+ "ORDER BY SUM(ca.cantidad) DESC "
			+ "LIMIT 10", nativeQuery = true)
	public List<Object[]> findArticulosMasVendidos();
}
