package com.hexhoc.springbootblog.article;

import com.hexhoc.springbootblog.article.DTO.ArticleDetailDTO;
import com.hexhoc.springbootblog.article.DTO.ArticleEditDTO;
import com.hexhoc.springbootblog.category.CategoryService;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.common.util.PostResponse;
import com.hexhoc.springbootblog.common.util.UriUtils;
import com.hexhoc.springbootblog.config.ConfigService;
import com.hexhoc.springbootblog.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

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
        //TODO fix pagination
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
        Article article = articleOptional.get();
        request.setAttribute("article", article);
        request.setAttribute("categories", categoryService.getAllCategories());
        request.setAttribute("tags", articleService.getTagsListAsString(article.getTags()));
        return "admin/edit";
    }

    @PostMapping("/admin/articles/save")
    @ResponseBody
    public PostResponse save(ArticleEditDTO articleEditDTO) {
        String saveArticleResult = articleService.saveArticle(articleEditDTO);
        if ("success".equals(saveArticleResult)) {
            return PostResponse.genSuccessResult("Added successfully");
        } else {
            return PostResponse.genFailResult(saveArticleResult);
        }

    }

    @PostMapping("/admin/articles/update")
    @ResponseBody
    public PostResponse update(ArticleEditDTO articleEditDTO) {
        String saveArticleResult = articleService.updateArticle(articleEditDTO);
        if ("success".equals(saveArticleResult)) {
            return PostResponse.genSuccessResult("Added successfully");
        } else {
            return PostResponse.genFailResult(saveArticleResult);
        }
    }

    @PostMapping({"admin/upload/file"})
    @ResponseBody
    public PostResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //Generic method of file name
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
        //Create a file
        File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("The folder creation failed, the path is:" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            PostResponse resultSuccess = PostResponse.genSuccessResult();
            resultSuccess.setData(UriUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return PostResponse.genFailResult("File upload failed");
        }
    }



    }
