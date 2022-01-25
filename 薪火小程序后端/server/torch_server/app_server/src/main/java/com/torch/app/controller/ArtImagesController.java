package com.torch.app.controller;

import com.torch.app.service.ArtImagesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/artImages")
public class ArtImagesController {
    @Resource
    private ArtImagesService artImagesService;

}
