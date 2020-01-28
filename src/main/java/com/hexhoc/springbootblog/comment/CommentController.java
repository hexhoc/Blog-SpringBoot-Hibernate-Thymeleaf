package com.hexhoc.springbootblog.comment;

import com.hexhoc.springbootblog.common.util.CleanStringUtils;
import com.hexhoc.springbootblog.common.util.PatternValidatorUtils;
import com.hexhoc.springbootblog.common.util.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    /**
     * Comment action
     */
    @PostMapping(value = "/article/comment")
    @ResponseBody
    public PostResponse comment(HttpServletRequest request, HttpSession session,
                                @RequestParam Long articleId, @RequestParam String verifyCode,
                                @RequestParam String commentator, @RequestParam String email,
                                @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (StringUtils.isEmpty(verifyCode)) {
            return PostResponse.genFailResult("verification code must be filled");
        }
        String captchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(captchaCode)) {
            return PostResponse.genFailResult("Illegal request");
        }
        if (!verifyCode.equals(captchaCode)) {
            return PostResponse.genFailResult("Verification code error");
        }
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            return PostResponse.genFailResult("Illegal request");
        }
        if (null == articleId || articleId < 0) {
            return PostResponse.genFailResult("Illegal request");
        }
        if (StringUtils.isEmpty(commentator)) {
            return PostResponse.genFailResult("Please enter a title");
        }
        if (StringUtils.isEmpty(email)) {
            return PostResponse.genFailResult("Please input the email address");
        }
        if (!PatternValidatorUtils.isEmail(email)) {
            return PostResponse.genFailResult("Please input the correct email address");
        }
        if (StringUtils.isEmpty(commentBody)) {
            return PostResponse.genFailResult("Please enter the content of the comment");
        }
        if (commentBody.trim().length() > 200) {
            return PostResponse.genFailResult("Comment content is too long");
        }
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentator(CleanStringUtils.cleanString(commentator));
        comment.setEmail(email);
        if (PatternValidatorUtils.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }

        comment.setBody(CleanStringUtils.cleanString(commentBody));
        commentService.addComment(comment);
        return PostResponse.genSuccessResult();
    }

}
