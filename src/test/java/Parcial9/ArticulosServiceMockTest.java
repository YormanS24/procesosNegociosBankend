package Parcial9;
import Parcial9.model.Articulo;
import Parcial9.repository.ArticuloRepository;
import Parcial9.services.ArticuloServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static Parcial9.CategoriaServiceMockTest.mockCategoria;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ArticulosServiceMockTest {
    public static Articulo mockArticulo() {
        Articulo modelo = new Articulo();
        modelo.setCodigo(1L);
        modelo.setNombre("Limpido");
        modelo.setCategoria(mockCategoria());
        modelo.setDescripcion("HCL");
        modelo.setPrecio_venta("2000");
        modelo.setPrecio_compra("5000");
        modelo.setFecha_registro(new Date(10,10,20));

        return modelo;
    }
    public static Articulo mockArticuloMod() {
        Articulo modelo = new Articulo();
        modelo.setCodigo(1L);
        modelo.setNombre("Lejia");
        modelo.setCategoria(mockCategoria());
        modelo.setDescripcion("HCL");
        modelo.setPrecio_venta("2000");
        modelo.setPrecio_compra("5000");
        modelo.setFecha_registro(new Date(10,10,20));
        return modelo;
    }


    @InjectMocks
    private ArticuloServiceImpl articuloService;
    @Mock
    private ArticuloRepository articuloRepository;

    @DisplayName("Test para obtener articulos por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        Articulo articulo = mockArticulo();
        Articulo articuloMod = mockArticulo();
        //When
        when(articuloRepository.findByCodigo(anyLong())).thenReturn(Optional.of(articulo));
        ResponseEntity<Articulo> respuesta = articuloService.getArticleFindBycodige(articuloMod.getCodigo());

        //Then
        Assertions.assertNotNull(respuesta);

    }


    @DisplayName("Test para listar a los Articulos")
    @Test
    void getAllArticlesTest() {

        Articulo articulo = mockArticulo();
        ResponseEntity<List<Articulo>> lista = articuloService.allArticles();
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para crear Articulo")
    @Test
    void createArticleTest() {
        //Given
        Articulo articulo = mockArticulo();
        Articulo articuloMod = mockArticulo();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articuloMod)).willReturn(articuloMod);
        //When

        ResponseEntity<Articulo> articuloGuardado = articuloService.createArticle(articulo);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para editar un Articulo")
    @Test
    void editArticleTest() {
        // Given
        Articulo articulo = mockArticulo();
        Articulo articuloMod = mockArticuloMod();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articuloMod)).willReturn(articuloMod);

        //when

        ResponseEntity<Articulo> articuloGuardado = articuloService.editArticle(articulo.getCodigo(), articuloMod);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @Test
    @DisplayName("Test para una lista vacia")
    void listaArticulosVacia() {
        when(articuloRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity mockArticleService = articuloService.allArticles();

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }

    @Test
    void whenNoEncuentraNingunArticulo() {
        Articulo articulo = null;

        when(articuloRepository.findAll()).thenReturn(Collections.emptyList());
        List<Articulo> articulo1 = articuloService.allArticles().getBody();
        Assertions.assertEquals(null, articulo1);
    }
}