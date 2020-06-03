package com.tsystems.client;

import com.tsystems.dto.*;

import java.io.IOException;
import java.util.List;

public interface ScoreBoardService {
    public StatisticsCountDTO getStatistic() throws IOException;
}
