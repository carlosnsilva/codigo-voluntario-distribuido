package com.projeto.codigovoluntario.controller;

import com.projeto.codigovoluntario.model.Projetos;
import com.projeto.codigovoluntario.service.projetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class projetoController {

    @Autowired
    private projetosService projectservice;

    @GetMapping("/projects")
    public List<Projetos> getProject(){
        return this.projectservice.getProjects();
    }

    @GetMapping("/projects/{id}")
    public Projetos getProjectPorId(@PathVariable("id") Long id){
        return this.projectservice.getProjectPorId(id);
    }

    @PostMapping("/projects")
    public Projetos insertProject(@RequestBody Projetos projeto){
        return this.projectservice.insertProject(projeto);
    }

    @PutMapping("/projects/{id}")
    public Projetos updateProject(@PathVariable("id") Long id, @RequestBody Projetos projeto){
        return this.projectservice.updateProject(id, projeto);

    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        this.projectservice.deleteProject(id);

    }
}
