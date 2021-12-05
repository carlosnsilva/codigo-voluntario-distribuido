package com.projeto.codigovoluntario.controller;

import com.projeto.codigovoluntario.model.Usuario;
import com.projeto.codigovoluntario.service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class usuarioController {

    @Autowired
    private usuarioService userService;

    @GetMapping
    public List<Usuario> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public Usuario getUserById(@PathVariable("id") Long id){
        return this.userService.getUserById(id);
    }

    @PostMapping
    public Usuario insertUser(@RequestBody Usuario usuario){
        return this.userService.insertUser(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateProject(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        return this.userService.updateUser(id,usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
    }

    @DeleteMapping
    public void deleteProject(){
        this.userService.deleteAll();
    }
}
