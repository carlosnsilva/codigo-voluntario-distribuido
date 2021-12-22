package com.projeto.codigovoluntario.rabbitMQ;

import com.google.gson.Gson;
import com.projeto.codigovoluntario.model.Projetos;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Fila {

    private String nomeFila;

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
        nomeFila = "filaProjetos";

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
}
