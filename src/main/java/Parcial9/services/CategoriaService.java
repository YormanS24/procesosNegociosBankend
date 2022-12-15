package Parcial9.services;

import Parcial9.model.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaService {

    ResponseEntity<Categoria> getCategoryFindBycodige(Long codigo);
    ResponseEntity <List<Categoria>> allCategory();
    ResponseEntity <Categoria> createCategory( Categoria categoria);
    ResponseEntity <Categoria> editCategory(Long codigo, Categoria categoria);
    ResponseEntity <Categoria> deleteCategory(Long codigo);
}
