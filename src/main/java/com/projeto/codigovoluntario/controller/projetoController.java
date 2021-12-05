package com.projeto.codigovoluntario.controller;

import com.projeto.codigovoluntario.model.Projetos;
import com.projeto.codigovoluntario.service.projetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class projetoController {

    @Autowired
    private projetosService projectservice;

    @GetMapping
    public List<Projetos> getProject(){
        return this.projectservice.getProjects();
    }

    @GetMapping("/{id}")
    public Projetos getProjectPorId(@PathVariable("id") Long id){
        return this.projectservice.getProjectPorId(id);
    }

    @PostMapping
    public Projetos insertProject(@RequestBody Projetos projeto){
        return this.projectservice.insertProject(projeto);
    }

    @PutMapping("/{id}")
    public Projetos updateProject(@PathVariable("id") Long id, @RequestBody Projetos projeto){
        return this.projectservice.updateProject(id, projeto);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        this.projectservice.deleteProject(id);
    }
}
