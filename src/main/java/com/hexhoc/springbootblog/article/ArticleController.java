package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleEditDTO;
import com.hexhoc.springbootblog.category.CategoryService;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.common.util.PostResponse;
import com.hexhoc.springbootblog.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ConfigService configService;
    private final CategoryService categoryService;

    @Autowired
    public ArticleController(ArticleService articleService, ConfigService configService, CategoryService categoryService){
        this.articleService = articleService;
        this.configService = configService;
        this.categoryService = categoryService;
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

    @GetMapping("/admin/articles/edit")
    public String edit(Model model) {
        model.addAttribute("path", "edit");
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/edit";
    }

    @GetMapping("/admin/articles/edit/{articleId}")
    public String edit(HttpServletRequest request, @PathVariable("articleId") Long articleId) {
        request.setAttribute("path", "edit");
        Optional<Article> articleOptional = articleService.getArticleById(articleId);
        if (articleOptional.isEmpty()) {
            return "error/error_400";
        }
        request.setAttribute("article", articleOptional.get());
        request.setAttribute("categories", categoryService.getAllCategories());
        return "admin/edit";
    }

    //TODO Remake to DTO
    @PostMapping("/admin/articles/save")
    @ResponseBody
    public PostResponse save(@RequestParam ArticleEditDTO articleEditDTO) {

        String saveArticleResult = articleService.saveArticle(articleEditDTO);
        if ("success".equals(saveArticleResult)) {
            return PostResponse.genSuccessResult("Added successfully");
        } else {
            return PostResponse.genFailResult(saveArticleResult);
        }

    }

    @PostMapping("/admin/articles/update")
    @ResponseBody
    public PostResponse update(@RequestParam ArticleEditDTO articleEditDTO) {
        String saveArticleResult = articleService.saveArticle(articleEditDTO);
        if ("success".equals(saveArticleResult)) {
            return PostResponse.genSuccessResult("Added successfully");
        } else {
            return PostResponse.genFailResult(saveArticleResult);
        }
    }


}
