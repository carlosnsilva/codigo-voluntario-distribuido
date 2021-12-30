package com.projeto.codigovoluntario.rabbitMQ;

import com.google.gson.Gson;
import com.projeto.codigovoluntario.model.Projetos;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Fila {

    private String nomeFila = "filaProjetos";
    private Projetos projeto;

    public Fila(){}

    public Channel init() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();

        return canal;
    }

    public void addProjectFila(Projetos project) throws IOException, TimeoutException {
        Channel canal = init();

        byte[] projeto = convertForByte(project);

        //declarando fila
        canal.queueDeclare(nomeFila, false,false,false,null);

        //adicionando projeto na fila
        canal.basicPublish("", nomeFila, false, false, null, projeto);

    }

    private byte[] convertForByte(Projetos project){
        Gson gson = new Gson();

        byte[] result = gson.toJson(project).getBytes(StandardCharsets.UTF_8);
        return result;
    }

    public Projetos consumerFila() throws IOException, TimeoutException {
        Channel canal = init();

        canal.queueDeclare(nomeFila, false,false,false,null);

        DeliverCallback callback = (consumerTag, delivery) -> {
            projeto = serialize(delivery.getBody());
        };

        return projeto;
    }

    public Projetos serialize(byte[] d){
        Projetos project = new Gson().fromJson(String.valueOf(d),Projetos.class);

        return project;
    }
}
