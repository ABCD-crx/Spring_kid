package com.kob.backend.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: chen
 * Time: 2024/1/17 21:11
 */
@Controller
@RequestMapping("/")
public class IndexController {
    public String index(){
        return "pk/index.html";
        // 文件后缀名可加可不加
        // 访问路径默认src/main/resources/templates/
    }
}
