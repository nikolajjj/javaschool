package com.tsystems.service.api;

import com.tsystems.dto.MyOrderDTO;
import com.tsystems.dto.StatisticsCountDTO;

import java.util.List;

public interface ScoreBoardSender {
    public List<MyOrderDTO> sendAllOrders();
    public StatisticsCountDTO sendStatistic();
}
