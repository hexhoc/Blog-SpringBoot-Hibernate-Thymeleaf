package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ConfigService configService;

    @Autowired
    public ArticleController(ArticleService articleService, ConfigService configService){
        this.articleService = articleService;
        this.configService = configService;
    }

    /**
     * HOME PAGE
     * @return
     */
    @GetMapping("/")
    public String index(HttpServletRequest request){
        return this.page(request, 1);
    }

    /**
     * Blog pagination
     */
    @GetMapping("/page/{pageNum}")
    public String page(HttpServletRequest request, @PathVariable int pageNum) {
        PageResult blogPageResult = articleService.getArticlesForIndexPage(pageNum);
        if (blogPageResult == null) {
            return "error/error_404";
        }
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "home");
        request.setAttribute("configurations", configService.getAllConfigs());

        return "blog/index";
    }
}
