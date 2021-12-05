package com.projeto.codigovoluntario.controller;

import com.projeto.codigovoluntario.model.Usuario;
import com.projeto.codigovoluntario.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    @Autowired
    private usuarioService userService;

    @GetMapping("/users")
    public List<Usuario> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public Usuario getUserById(@PathVariable("id") Long id){
        return this.userService.getUserById(id);
    }

    @PostMapping("/users")
    public Usuario insertUser(@RequestBody Usuario usuario){
        return this.userService.insertUser(usuario);
    }

    @PutMapping("/users/{id}")
    public Usuario updateProject(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        return this.userService.updateProject(id,usuario);
    }

    @DeleteMapping("/users/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
    }

    @DeleteMapping("/users")
    public void deleteProject(){
        this.userService.severusDelete();
    }
}
