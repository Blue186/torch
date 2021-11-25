package com.torch.app.controller;

import com.torch.app.service.ActivityTimesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ActivityTimes")
public class ActivityTimesController {
    @Resource
    private ActivityTimesService activityTimesService;

}
