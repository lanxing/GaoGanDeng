package com.gaogandeng.utils;

import com.gaogandeng.Enum.CmdStatus;
import com.gaogandeng.Enum.CmdType;
import com.gaogandeng.QueryCondition.ControlLogQuery;
import com.gaogandeng.QueryCondition.LightControlQuery;
import com.gaogandeng.model.ControlLog;
import com.gaogandeng.model.LightControlLog;
import com.gaogandeng.service.ControlLogService;
import com.gaogandeng.service.LightControlLogService;
import com.gaogandeng.service.LightService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lanxing on 16-3-16.
 * 对任务进行处理的类，本类主要用来处理业务代码，当需要与数据库进行交互时最好用该类
 */

@Service("cmdControlService")
public class CmdControlService {
    private ControlLogService controlLogService;
    private LightControlLogService lightControlLogService;
    private LightService lightService;

    @Autowired
    public void setControlLogService(ControlLogService controlLogService) {
        this.controlLogService = controlLogService;
    }

    @Autowired
    public void setLightControlLogService(LightControlLogService lightControlLogService) {
        this.lightControlLogService = lightControlLogService;
    }

    @Autowired
    public void setLightService(LightService lightService) {
        this.lightService = lightService;
    }

    /**
     *根据查询条件查询任务，此任务并没有进行细化
     * @param query
     * @return
     */
    public List<ControlLog> queryControlLog(ControlLogQuery query){
        return controlLogService.findControlLogByTime(query);
    }

    /**
     * 根据查询条件查询细化后的控制任务
     * @param query
     * @return
     */
    public List<LightControlLog> queryLightControlLog(LightControlQuery query){
        return lightControlLogService.findLightControlLog(query);
    }

    /**
     * 插入控制任务，此任务为未细分的任务，因此在插入后需要对任务进行细分，将其插入lightcontrollogs表中
     * 返回的为List<HashMap<Date, Integer>>键值对，存放执行任务执行的时间以及任务编号
     * @param log
     * @return 需要执行任务时间-任务编号，如果返回null则表示该任务为即时任务，需要对其另行处理{@See paraseCmd}
     */
    public List<Map<Date, String> > insertControlLog(ControlLog log){
        controlLogService.insertControlLog(log);
        Date openTime = log.getOpenTime();
        Date closeTime = log.getCloseTime();

        if(openTime == null && closeTime == null){
            return null;                                    //即时命令
        }
        List<Map<Date, String> >lists = Lists.newArrayList();
        String[] lightIds = log.getLightIds().split(";");

        /**
         * 设置查询条件，只查询未执行的记录
         */
        LightControlQuery query = new LightControlQuery();
        query.setStatus(CmdStatus.UNEXECUTED.getStatus());

        /**
         * 如果开灯时间不为null，则需要将任务划分设置灯具开启时间
         */
        if(openTime != null){
            for (String lightId : lightIds){
                query.setLightId(Integer.valueOf(lightId));
                query.setCmd(CmdType.OPEN.getType());           //同时查询条件需要加上任务类型
                List<LightControlLog> lightControlLogs = lightControlLogService.findLightControlLog(query);
                if(lightControlLogs != null && lightControlLogs.size() != 0){
                    //如果查询到已经记录，则将其置为覆盖状态
                    for (LightControlLog lightControlLog : lightControlLogs){
                        lightControlLog.setStatus(CmdStatus.OVERWRITE.getStatus());
                        lightControlLogService.updateStatus(lightControlLog);
                    }
                }

                LightControlLog tmp = new LightControlLog();
                tmp.setBright(log.getBright());
                tmp.setCmdTime(openTime);
                tmp.setCmd(CmdType.OPEN.getType());
                tmp.setStatus(CmdStatus.UNEXECUTED.getStatus());
                tmp.setLight(lightService.findLightById(Integer.valueOf(lightId)));
                lightControlLogService.insertLightControl(tmp);
                Map<Date, String> tmpMap = Maps.newHashMap();
                tmpMap.put(openTime, String.valueOf(tmp.getId()));
                lists.add(tmpMap);
            }
        }

        if (closeTime != null){
            for (String lightId : lightIds){
                query.setLightId(Integer.valueOf(lightId));
                query.setCmd(CmdType.CLOSE.getType());
                List<LightControlLog> lightControlLogs = lightControlLogService.findLightControlLog(query);
                if(lightControlLogs != null && lightControlLogs.size() != 0){
                    //如果查询到记录，则将其置为覆盖状态
                    for (LightControlLog lightControlLog : lightControlLogs){
                        lightControlLog.setStatus(CmdStatus.OVERWRITE.getStatus());
                        lightControlLogService.updateStatus(lightControlLog);
                    }
                }
                LightControlLog tmp = new LightControlLog();
                tmp.setCmdTime(closeTime);
                tmp.setCmd(CmdType.CLOSE.getType());
                tmp.setStatus(CmdStatus.UNEXECUTED.getStatus());
                tmp.setLight(lightService.findLightById(Integer.valueOf(lightId)));
                lightControlLogService.insertLightControl(tmp);
                Map<Date, String> tmpMap = Maps.newHashMap();
                tmpMap.put(openTime, String.valueOf(tmp.getId()));
                lists.add(tmpMap);
            }
        }

        return lists;
    }

    /**
     * 根据命令类型将其解析为字符串，发送到redis消息队列中
     * @param log
     * @return
     */
    public String paraseCmd(ControlLog log){

        //TODO 根据命令格式按照制定好的协议将其解析为字符串

        return null;
    }
}
