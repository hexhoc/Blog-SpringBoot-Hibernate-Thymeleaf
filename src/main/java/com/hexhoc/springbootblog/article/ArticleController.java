package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.common.util.PostResponse;
import com.hexhoc.springbootblog.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @GetMapping({"/", "/index", "/index.html"})
    public String index(Model model){
        return this.page(model, 1);
    }

    /**
     * Blog pagination
     */
    @GetMapping("/page/{pageNum}")
    public String page(Model model, @PathVariable int pageNum) {
        PageResult blogPageResult = articleService.getArticlesForIndexPage(pageNum);
        if (blogPageResult == null) {
            return "error/error_404";
        }
        model.addAttribute("blogPageResult", blogPageResult);
        model.addAttribute("pageName", "home");
        model.addAttribute("configurations", configService.getAllConfigs());

        return "blog/index";
    }

    /**
     * Details page
     *
     * @return
     */
    @GetMapping({ "/article/{articleId}"})
    public String articleDetails(Model model, @PathVariable Long articleId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {
        ArticleDetailDTO articleDetailDTO = articleService.getArticleDetailDTOById(articleId);
        model.addAttribute("articleDetailDTO", articleDetailDTO);
//        model.addAttribute("commentPageResult", commentService.getCommentPageByBlogIdAndPageNum(ArticleId, commentPage));
        model.addAttribute("pageName", "Details");
        model.addAttribute("configurations", configService.getAllConfigs());

        return "blog/detail";
    }


    /**
     * Get all articles by page. Only for admin users
     * @param params
     * @return
     */
    @GetMapping("/admin/articles/list")
    @ResponseBody
    public PostResponse list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return PostResponse.genFailResult("Parameter abnormal！");
        }
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        return PostResponse.genSuccessResult(articleService.getArticlesPage(page, limit));
    }



    @GetMapping("/admin/articles")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "articles");
        return "admin/articles";
    }



}
