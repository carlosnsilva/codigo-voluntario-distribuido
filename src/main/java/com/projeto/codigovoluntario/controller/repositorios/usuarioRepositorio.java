package com.projeto.codigovoluntario.controller.repositorios;

import com.projeto.codigovoluntario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepositorio extends JpaRepository<Usuario, Long> {
}
