package com.gaogandeng.utils;

import com.gaogandeng.QueryCondition.LightStatusQuery;
import com.gaogandeng.model.LightStatusLog;
import com.gaogandeng.service.LightStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanxing on 16-3-16.
 * 用于服务灯具状态的业务类，业务代码可以写在此类中,可参考{@link com.gaogandeng.service.LightStatusLogService}
 */

@Service("lightStatusService")
public class LightStatusService {
    private LightStatusLogService lightStatusLogService;

    @Autowired
    public void setLightStatusLogService(LightStatusLogService lightStatusLogService) {
        this.lightStatusLogService = lightStatusLogService;
    }

    /**
     * 根据查询条件查询灯具的状态
     * @param query
     * @return
     */
    public List<LightStatusLog> queryLightStatus(LightStatusQuery query){
        return lightStatusLogService.findLightStatus(query);
    }
}
