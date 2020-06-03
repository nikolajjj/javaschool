package com.tsystems.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.dto.StatisticsCountDTO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.IOException;

@Named("scoreboardBean")
@ApplicationScoped
public class ScoreBoardBean {
    @Inject
    private ScoreBoardService scoreBoardService;

    private StatisticsCountDTO statistic = new StatisticsCountDTO();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = Logger.getLogger(ScoreBoardBean.class);

    @PostConstruct
    public void init() {
        log.info("init() ScoreBoardBean was invoked!");
        try {
            statistic = scoreBoardService.getStatistic();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            log.info("fail while trying to fetch orders");
        }
        log.info("After fetching data list...");
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
                ("failover://(tcp://localhost:61616)?initialReconnectDelay=2000&maxReconnectAttempts=3");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("ScoreBoardChannel");
            MessageConsumer consumer = session.createConsumer(topic);

            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        try {
                            TextMessage textMessage = (TextMessage) message;
                            StatisticsCountDTO statistic = objectMapper.readValue(textMessage.getText(), StatisticsCountDTO.class);
                            onNewScoreBoard(statistic);
                        } catch (JMSException | IOException | NullPointerException e) {
                            log.info("error");
                        }
                    }
                }
            };
            consumer.setMessageListener(listener);
        } catch(JMSException e) {
            e.printStackTrace();
        }
    }

    private void onNewScoreBoard(StatisticsCountDTO dto) {
        this.statistic = dto;
    }

    public StatisticsCountDTO getStatistic() {
        if (statistic != null) {
            return statistic;
        }
        return null;
    }
}
