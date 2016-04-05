package com.gaogandeng.controller;

import com.gaogandeng.QueryCondition.ControlLogQuery;
import com.gaogandeng.model.ControlLog;
import com.gaogandeng.model.Light;
import com.gaogandeng.service.LightService;
import com.gaogandeng.utils.CmdControlService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lanxing on 16-3-17.
 */
@Controller
@RequestMapping(value = "/control/")
public class LightControl {
    //TODO 对集中控制器进行的控制操作，只需要将相应的控制函数写到本类中并赋予url就行
    private CmdControlService cmdControlService;
    private LightService lightService;

    @Autowired
    public void setLightService(LightService lightService) {
        this.lightService = lightService;
    }

    @Autowired

    public void setCmdControlService(CmdControlService cmdControlService){
        this.cmdControlService=cmdControlService;
    }

    @RequestMapping(value="/query")
    public @ResponseBody
    List<ControlLog> getControlLogByTime(HttpServletRequest request, HttpServletResponse response){
        ControlLogQuery query =new ControlLogQuery();
        String startTime=request.getParameter("start_time");
        String stopTime =request.getParameter("stop_time");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try{
            if(!Strings.isNullOrEmpty(stopTime)){
                Date endDate=sdf.parse(stopTime);
                query.setEndTime(endDate);
            }

            if(!Strings.isNullOrEmpty(startTime)){
                Date startDate = sdf.parse(startTime);
                query.setStartTime(startDate);
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
        List<ControlLog> controlLog=cmdControlService.queryControlLog(query);
        for (ControlLog con: controlLog) {
            String[] lightIds=con.getLightIds().split(";");
            List<Light> lights=con.getLights();
            if(lights == null){
                lights = Lists.newArrayList();
            }
            for(int i=0;i<lightIds.length;i++){
                Light light=lightService.findLightById(Integer.valueOf(lightIds[i]));
                lights.add(light);
            }
            con.setLights(lights);
        }
        return controlLog ;
    }



}
