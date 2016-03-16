package com.gaogandeng.service;

import com.gaogandeng.QueryCondition.LightStatusQuery;
import com.gaogandeng.model.LightStatusLog;

import java.util.List;

/**
 * Created by lanxing on 16-3-16.
 */
public interface LightStatusLogService {
    void insertLightStatus(LightStatusLog lightStatusLog);
    List<LightStatusLog> findLightStatus(LightStatusQuery query);
}
