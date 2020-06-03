package com.tsystems.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.dto.DriverDTO;
import com.tsystems.dto.MyOrderDTO;
import com.tsystems.dto.StatisticsCountDTO;
import com.tsystems.dto.WagonDTO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = ScoreBoardServiceImpl.JNDI)
public class ScoreBoardServiceImpl implements ScoreBoardService {

    public static final String JNDI = "restClientBean";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(ScoreBoardServiceImpl.class);

    private static String httpGet(String url) throws IOException {
        java.net.URL urlRequest = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        if (connection.getResponseCode() != 200) {
            throw new IOException("Some HTTP code error. Code is not 200.");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonResponse = bufferedReader.readLine();
        connection.disconnect();
        return jsonResponse;
    }

    @Override
    public StatisticsCountDTO getStatistic() throws IOException {
        String jsonResponse = httpGet("http://localhost:8080/scoreboard/statistic/get/list");
        try {
            return objectMapper.readValue(jsonResponse, StatisticsCountDTO.class);
        } catch (IOException e) {
            logger.error("Fail while trying to get statistic from first application");
            return null;
        }
    }
}