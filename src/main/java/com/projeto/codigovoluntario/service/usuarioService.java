package com.projeto.codigovoluntario.service;

import com.projeto.codigovoluntario.controller.repositorios.usuarioRepositorio;
import com.projeto.codigovoluntario.model.Projetos;
import com.projeto.codigovoluntario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usuarioService {

    @Autowired
    private usuarioRepositorio userRepository;

    public List<Usuario> getUsers(){
        return this.userRepository.findAll();
    }

    public Usuario getUserById(Long idUser){
        return this.userRepository.findById(idUser).orElse(null);
    }

    public Usuario insertUser(Usuario user){
        return this.userRepository.save(user);
    }

    public Usuario updateProject(Long id, Usuario usuario){

        return userRepository.findById(id)
                .map(body -> {
                    Usuario userOld = userRepository.findById(id).orElse(null);;

                    if(usuario.getNome().isEmpty() || usuario.getNome() == null){
                        usuario.setNome(userOld.getNome());
                    }
                    if(usuario.getEmail().isEmpty() || usuario.getEmail() == null){
                        usuario.setEmail(userOld.getEmail());
                    }
                    if(usuario.getSenha().isEmpty() || usuario.getEmail() == null){
                        usuario.setSenha(userOld.getSenha());
                    }

                    body.setNome(usuario.getNome());
                    body.setEmail(usuario.getEmail());
                    body.setSenha(usuario.getSenha());

                    Usuario u = userRepository.save(body);
                    return u;
                }).orElse(null);
    }

    public void deleteUser(Long idUser){
        this.userRepository.deleteById(idUser);
    }

    public void severusDelete(){
        this.userRepository.deleteAll();
    }
}
