package com.hexhoc.springbootblog.tag;

import com.hexhoc.springbootblog.article.ArticleService;
import com.hexhoc.springbootblog.common.util.PageResult;
import com.hexhoc.springbootblog.common.util.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService, ArticleService articleService) {
        this.tagService = tagService;
    }

    @GetMapping("admin/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }

    @GetMapping("admin/tags/list")
    @ResponseBody
    public PostResponse list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return PostResponse.genFailResult("Parameter abnormal！");
        }
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        return PostResponse.genSuccessResult(tagService.getTagsPage(page, limit));
    }

    @PostMapping("admin/tags/save")
    @ResponseBody
    public PostResponse save(@RequestParam("tagName") String tagName) {
        if (StringUtils.isEmpty(tagName)) {
            return PostResponse.genFailResult("Parameter exception!");
        }
        if (tagService.saveTag(tagName)) {
            return PostResponse.genSuccessResult();
        } else {
            return PostResponse.genFailResult("Duplicate tag name");
        }
    }

    @PostMapping("admin/tags/delete")
    @ResponseBody
    public PostResponse delete(@RequestBody ArrayList<Integer> ids) {
        if (ids.size() <1) {
            return PostResponse.genFailResult("Parameter exception!");
        }
        if (tagService.deleteBatch(ids)) {
            return PostResponse.genSuccessResult();
        } else {
            return PostResponse.genFailResult("Related data, please do not delete forcibly");
        }
    }

}
