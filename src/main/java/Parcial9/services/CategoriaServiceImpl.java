package Parcial9.services;

import Parcial9.model.Categoria;
import Parcial9.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository CategoriaRepository;

    @Override
    public ResponseEntity<Categoria> getCategoryFindBycodige(Long codigo) {
        Optional<Categoria> categoria = CategoriaRepository.findById(codigo);
        if(categoria.isPresent()){
            return new ResponseEntity(categoria, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Categoria>> allCategory() {
        List<Categoria> categorias = CategoriaRepository.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categorias,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Categoria> createCategory(Categoria categoria) {
        try {
            CategoriaRepository.save(categoria);
            return new ResponseEntity(categoria, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Categoria> editCategory(Long codigo, Categoria categoria) {
        Optional<Categoria> categoriaBD = CategoriaRepository.findById(codigo);
        if (categoriaBD.isPresent()){
            try {
                categoriaBD.get().setNombre(categoria.getNombre());
                categoriaBD.get().setDescripcion(categoria.getDescripcion());
                CategoriaRepository.save(categoriaBD .get());
                return new ResponseEntity(categoriaBD ,HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Categoria> deleteCategory(Long codigo) {
        Optional<Categoria> categoriaBD = CategoriaRepository.findById(codigo);
        if (categoriaBD.isPresent()){
            CategoriaRepository.delete(categoriaBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
