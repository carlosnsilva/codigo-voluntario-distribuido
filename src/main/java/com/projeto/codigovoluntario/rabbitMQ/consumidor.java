package com.projeto.codigovoluntario.rabbitMQ;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class consumidor extends configRabbitMQ{

    @Value("$spring.rabbitmq.host")
    private String hostname;

    @Value("$spring.rabbitmq.username")
    private String username = "mqadmin";

    @Value("$spring.rabbitmq.password")
    private String password = "Admin123XX_";

    private String filaName;
    private Channel canal;

    public void consumir() throws IOException, TimeoutException {
        canal = init(hostname,username,password);
        filaName = "alunosProjetos";

        canal.queueDeclare(filaName,false,false,false,null);
    }


}
