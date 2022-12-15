package Parcial9.controller;

import Parcial9.model.Categoria;
import Parcial9.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository CategoriaRepository;

    @PostMapping(value = "/categoria")
    public ResponseEntity crearCategoria(@RequestBody Categoria categoria){
        try {
            CategoriaRepository.save(categoria);
            return new ResponseEntity(categoria, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity getCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = CategoriaRepository.findById(id);
        if(categoria.isPresent()){
            return new ResponseEntity(categoria, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }


    @PutMapping("/categoria/{id}")
    public ResponseEntity editarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        Optional<Categoria> categoriaBD = CategoriaRepository.findById(id);
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


    @GetMapping(value = "/categorias")
    public ResponseEntity listarCategorias(){
        List<Categoria> categorias = CategoriaRepository.findAll();
        if (categorias.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categorias,HttpStatus.OK);
    }




    @DeleteMapping("/categoria/{id}")
    public ResponseEntity eliminarCategoria(@PathVariable Long id){
        Optional<Categoria> categoriaBD = CategoriaRepository.findById(id);
        if (categoriaBD.isPresent()){
            CategoriaRepository.delete(categoriaBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
