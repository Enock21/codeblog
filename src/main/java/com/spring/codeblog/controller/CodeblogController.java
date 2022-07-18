package com.spring.codeblog.controller;

import com.spring.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CodeblogController {

    @Autowired
    CodeblogService codeblogService;
}
