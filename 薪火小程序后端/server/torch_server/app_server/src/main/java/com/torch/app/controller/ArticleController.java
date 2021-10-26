package com.torch.app.controller;

import com.torch.app.service.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Api(tags = {"文章发布相关接口"},value = "文章发布相关接口")
@RestController
@RequestMapping()
public class ArticleController {
    @Resource
    private ArticleService articleService;

}
