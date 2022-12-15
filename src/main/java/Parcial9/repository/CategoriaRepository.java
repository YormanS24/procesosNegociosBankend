package Parcial9.repository;

import Parcial9.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List <Categoria> findAllByCodigo (Long codigo);
}
