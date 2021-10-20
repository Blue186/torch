package com.torch.app.controller;

import com.torch.app.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping()
public class CommentController {
    @Resource
    private CommentService commentService;

}
