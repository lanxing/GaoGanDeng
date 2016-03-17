package com.gaogandeng.controller;

import com.gaogandeng.model.Light;
import com.gaogandeng.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lanxing on 16-3-17.
 */

@Controller
public class Test {
    private LightService lightService;

    @Autowired
    public void setLightService(LightService lightService) {
        this.lightService = lightService;
    }

    @RequestMapping(value = "/test")
    public @ResponseBody
    Light getLight(HttpServletRequest request){
        Light light = lightService.findLightById(1);
        return light;
    }
}
