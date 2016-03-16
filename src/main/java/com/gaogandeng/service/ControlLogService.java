package com.gaogandeng.service;

import com.gaogandeng.QueryCondition.ControlLogQuery;
import com.gaogandeng.model.ControlLog;

import java.util.List;

/**
 * Created by lanxing on 16-3-16.
 */
public interface ControlLogService {
    void insertControlLog(ControlLog controlLog);
    List<ControlLog> findControlLogByTime(ControlLogQuery query);
}
