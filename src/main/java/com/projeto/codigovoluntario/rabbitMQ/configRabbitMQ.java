package com.projeto.codigovoluntario.rabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class configRabbitMQ {

    public configRabbitMQ(){

    }

    public Channel init(String host, String username, String pass) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");

        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();

        return canal;
    }






}
