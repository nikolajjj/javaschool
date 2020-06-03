package com.tsystems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.client.ScoreBoardBean;
import com.tsystems.dto.StatisticsCountDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.IOException;
import java.io.Serializable;

@Named("userScoreBoard")
@SessionScoped
public class UserScoreBoard implements Serializable {
    @Inject
    private ScoreBoardBean scoreBoardBean;
    @Inject
    @Push(channel = "ScoreBoardChannel")
    private PushContext pushContext;

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger log = Logger.getLogger(UserScoreBoard.class);

    @PostConstruct
    public void onInit() {
        log.info("Session was created");
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
                    ("failover://(tcp://localhost:61616)?initialReconnectDelay=2000&maxReconnectAttempts=5");
            Connection connection = null;
            try {
                connection = connectionFactory.createConnection();
            } catch (JMSException e) {
                log.info("error create conenction");
            }
            try {
                connection.start();
            } catch(Exception e) {
                log.info("error start connection");
            }
            log.info("Connection to activemq was successfully established");

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("ScoreBoardChannel");
            MessageConsumer consumer = session.createConsumer(topic);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        try {
                            TextMessage textMessage = (TextMessage) message;
//                            StatisticsCountDTO statistic = objectMapper.readValue(textMessage.getText(), StatisticsCountDTO.class);
                            pushContext.send("updateScoreBoard");
                        } catch (/*JMSException | IOException | */NullPointerException e) {
                            log.info("Not statistic update");
                        }
                    }
                }
            };
            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            log.error("JMSException inside onInit() USER SESSION BEAN");
        }
    }

    public StatisticsCountDTO getStatistic() {
        if (scoreBoardBean.getStatistic() != null) {
            return scoreBoardBean.getStatistic();
        }
        return null;
    }
}
