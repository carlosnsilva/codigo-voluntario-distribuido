package com.projeto.codigovoluntario.rabbitMQ;

import com.google.gson.Gson;
import com.projeto.codigovoluntario.model.Projetos;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Fila {

    private String nomeFila = "filaProjetos";

    public Fila(){}

    public Channel init() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("toad.rmq.cloudamqp.com");
        connectionFactory.setUsername("rcukcycw");
        connectionFactory.setPassword("dT3RaERSchm7_nTvu6JusptJrvt2YD9H");
        connectionFactory.setVirtualHost("rcukcycw");
//        connectionFactory.setHost("localhost");
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");

        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();

        return canal;
    }

    public void publishFila(Projetos project) throws IOException, TimeoutException {
        Channel canal = init();

        // byte[] projeto = convertForByte(project);

        //declarando fila
        canal.queueDeclare(nomeFila, false,false,false,null);

        //adicionando projeto na fila
        canal.basicPublish("", nomeFila, false, false, null, project.getNome().getBytes());
        canal.basicPublish("", nomeFila, false, false, null, project.getDescricao().getBytes());
        canal.basicPublish("", nomeFila, false, false, null, project.getTecnologias().getBytes());
        canal.basicPublish("", nomeFila, false, false, null, project.getUrl().getBytes());

    }

    private byte[] convertForByte(Projetos project){
        Gson gson = new Gson();

        byte[] result = gson.toJson(project).getBytes(StandardCharsets.UTF_8);
        return result;
    }

    public List<String> consumerFila() throws IOException, TimeoutException {
        Channel canal = init();
        List<String> strings = new ArrayList<>();

        canal.queueDeclare(nomeFila, false,false,false,null);
        
        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            // System.out.println("Eu " + consumerTag + " Recebi: " + mensagem);
            strings.add(mensagem);
            
        };

        // fila, noAck, callback, callback em caso de cancelamento (por exemplo, a fila foi deletada)
        canal.basicConsume(nomeFila, true, callback, consumerTag -> {
            System.out.println("Cancelaram a fila: " + nomeFila);
        });

        return strings;
    }

    public Projetos serialize(String d){
        Projetos project = new Gson().fromJson(d,Projetos.class);

        return project;
    }
}
