package Parcial9.repository;

import Parcial9.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    List <Articulo> findAllByCodigo (Long codigo);
}
