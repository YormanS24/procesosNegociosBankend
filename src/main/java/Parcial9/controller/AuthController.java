package Parcial9.controller;

import Parcial9.model.Usuario;
import Parcial9.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/auth/login")
    public ResponseEntity login(@RequestBody Usuario usuario){
        return  usuarioService.login(usuario.getCorreo(),usuario.getPassword());
    }
}
