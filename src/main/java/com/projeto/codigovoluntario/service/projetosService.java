package com.projeto.codigovoluntario.service;

import com.projeto.codigovoluntario.controller.repositorios.projetoRepositorio;
import com.projeto.codigovoluntario.model.Projetos;
import com.projeto.codigovoluntario.rabbitMQ.Fila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class projetosService {

    @Autowired
    private projetoRepositorio projectRepository;

    private Fila fila = new Fila();

    public List<Projetos> getProjects(){
        return this.projectRepository.findAll();
    }

    public Projetos getProjectPorId(Long idProject){
        return this.projectRepository.findById(idProject).orElse(null);
    }

    public Projetos insertProject(Projetos projeto){
        return this.projectRepository.save(projeto);
    }

    public Projetos insertProjectInFila(Projetos projetos) throws IOException, TimeoutException {
        fila.publishFila(projetos);
        List<String> atributos = fila.consumerFila();
        System.out.println(atributos);
        if (atributos.size() == 4) {
        	Projetos p = new Projetos();
            p.setNome(atributos.get(0));
            p.setDescricao(atributos.get(1));
            p.setTecnologias(atributos.get(2));
            p.setUrl(atributos.get(3));

            return insertProject(p);
        }
        return null;
    }

    public Projetos updateProject(Long id, Projetos project){
        return projectRepository.findById(id)
                .map(body -> {
                    Projetos projetoAntigo = projectRepository.findById(id).orElse(null);
                    if(project.getNome() == null){
                        project.setNome(projetoAntigo.getNome());

                    }
                    if(project.getDescricao() == null){
                        project.setDescricao(projetoAntigo.getDescricao());

                    }
                    if(project.getTecnologias() == null){
                        project.setTecnologias(projetoAntigo.getTecnologias());

                    }
                    if(project.getUrl() == null){
                        project.setUrl(projetoAntigo.getUrl());
                    }

                    body.setNome(project.getNome());
                    body.setDescricao(project.getDescricao());
                    body.setTecnologias(project.getTecnologias());
                    body.setUrl(project.getUrl());

                    Projetos p = projectRepository.save(body);
                    return p;
                }).orElse(null);
    }


    public void deleteProject(Long idProject){
        this.projectRepository.deleteById(idProject);

    }
}
