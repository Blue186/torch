package com.torch.app.controller;

import com.torch.app.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping()
public class ArticleController {
    @Resource
    private ArticleService articleService;

}
