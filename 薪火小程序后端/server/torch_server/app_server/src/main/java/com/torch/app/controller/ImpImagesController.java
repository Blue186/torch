package com.torch.app.controller;

import com.torch.app.service.ImpImagesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/impImages")
public class ImpImagesController {
    @Resource
    private ImpImagesService impImagesService;
}
