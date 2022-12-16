package Parcial9;


import Parcial9.model.Articulo;
import Parcial9.model.Usuario;
import Parcial9.repository.UsuarioRepository;
import Parcial9.services.UsuarioServiceImpl;
import org.hibernate.sql.Delete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceMockTest {
    public static Usuario mockUser() {
        Usuario modelo = new Usuario();
        modelo.setId(1L);
        modelo.setNombre("Josue");
        modelo.setApellidos("Campo");
        modelo.setDocumento("1002334333");
        modelo.setDireccion("Ocaña");
        modelo.setFechaNacimiento(new Date(10,10,20));
        modelo.setTelefono("3143454323");
        modelo.setCorreo("josue@gmail.com");
        modelo.setPassword("ashwusudsu");
        return modelo;
    }
    public static Usuario mockUserMod() {
        Usuario modelo = new Usuario();
        modelo.setNombre("yeison");
        modelo.setApellidos("ascanio");
        modelo.setDocumento("1002894333");
        modelo.setDireccion("Ocaña");
        modelo.setFechaNacimiento(new Date(10,10,20));
        modelo.setTelefono("3123454323");
        modelo.setCorreo("yeison@gmail.com");
        modelo.setPassword("aureisfidsu");
        return modelo;
    }
    @InjectMocks
    private UsuarioServiceImpl userService;

    @Mock
    private UsuarioRepository userRepository;

    @DisplayName("Test para obtener usuario por ID")
    @Test
    void GetUserByIdTest() {

        //Given
        Usuario user = mockUser();
        Usuario UserMod = mockUserMod();
        //When
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        ResponseEntity<Usuario> respuesta = userService.getUserById(user.getId());
        //Then
        Assertions.assertNotNull(respuesta);

    }

    @DisplayName("Test para eliminar un usuario POR ID")
    @Test
    void  deleteUserTest(){
        Usuario usuario = mockUser();
        given(userRepository.findById(usuario.getId())).willReturn(Optional.of(usuario));
        userRepository.deleteById(usuario.getId());
        Optional<Usuario> elmUsuario = userRepository.findById(usuario.getId());
        assertThat(elmUsuario).isEmpty();
 }


    @DisplayName("Test para listar a los Usuarios")
    @Test
    void getAllUsersTest() {

        Usuario usuario = mockUser();
        ResponseEntity<List<Usuario>> lista = userService.allUsers();
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para crear Usuario")
    @Test
    void createUserTest() {
        //Given
        Usuario usuario = mockUser();
        Usuario usuarioMod = mockUserMod();
        given(userRepository.findAllByNombre(usuario.getNombre())).willReturn(List.of(usuario));
        given(userRepository.findAllByApellidos(usuario.getApellidos())).willReturn(List.of(usuario));
        given(userRepository.findAllByNombreAndApellidos(usuario.getNombre(), usuario.getApellidos())).willReturn(List.of(usuario));
        given(userRepository.findByCorreo(usuario.getCorreo())).willReturn(usuario);
        given(userRepository.save(usuarioMod)).willReturn(usuarioMod);
        //When

        ResponseEntity<Usuario> articuloGuardado = userService.createUser(usuario);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }



    @DisplayName("Test para obtener usuarios por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        Usuario user = mockUser();

        //When
        given(userRepository.findAllByNombre(user.getNombre())).willReturn(List.of(user));
        given(userRepository.findAllByApellidos(user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findAllByNombreAndApellidos(user.getNombre(), user.getApellidos())).willReturn(List.of(user));
        given(userRepository.findByCorreo(user.getCorreo())).willReturn(user);
        ResponseEntity<Usuario> respuesta = userService.getUserById(user.getId());

        //Then
        Assertions.assertNotNull(respuesta);

    }

    @Test
    @DisplayName("Test para una lista vacia")
    void listaArticulosVacia() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity mockUserMod = userService.allUsers();

        Assertions.assertNotNull(mockUserMod);
        Assertions.assertEquals( 404, mockUserMod.getStatusCodeValue());
    }


    @Test
    void whenNoEncuentraNingunUsuario() {
        Articulo articulo = null;

        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        List<Usuario> usuario1 = userService.allUsers().getBody();
        Assertions.assertEquals(null, usuario1);
    }




}