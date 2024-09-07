package edu.es.eoi.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.es.eoi.marketplace.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query(value = "SELECT us.id, us.nombre, SUM(ca.cantidad * a.precio) AS total_importe "
			+ "FROM usuario us "
			+ "JOIN pedido pe ON us.id = pe.idcliente "
			+ "JOIN cantidad_articulos ca ON pe.id = ca.idpedido "
			+ "JOIN articulo a ON ca.idarticulo = a.id "
			+ "GROUP BY us.id "
			+ "ORDER BY total_importe DESC ", nativeQuery = true)
	public List<Object[]> findMejoresUsuarios();
	
}
