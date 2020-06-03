package com.tsystems.controller.rest;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.dto.StatisticsCountDTO;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.entity.Order;
import com.tsystems.service.api.DriverService;
import com.tsystems.service.api.ScoreBoardSender;
import com.tsystems.service.api.WagonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StatisticCountRestController {
    private ScoreBoardSender scoreBoardSender;

    private final static Logger log = Logger.getLogger(StatisticCountRestController.class);

    @Autowired
    public StatisticCountRestController(ScoreBoardSender scoreBoardSender) {
        this.scoreBoardSender = scoreBoardSender;
    }

    @GetMapping("/scoreboard/statistic/get/list")
    public String sendStatistic() {
        return ConverterUtil.parseJson(scoreBoardSender.sendStatistic());
    }
}
