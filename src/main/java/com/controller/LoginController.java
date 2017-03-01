package com.controller;

/**
 * Created by Donnie on 2017/2/17.
 */

import com.Entity.msg.Article;
import com.Entity.msg.MpNews;
import com.Entity.msg.MpNewsMsg;
import com.google.gson.Gson;
import com.repository.LogRepository;
import com.util.GsonUtil;
import com.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class LoginController{

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    private Gson gson = GsonUtil.getInstance();

    @RequestMapping("/")
    public String login() {
        log.info(authenticationManager.toString());
        return "login";
    }
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestParam String username, @RequestParam String password) {
        if (true) {
            try {
//                Authentication authentication = tryToAuthenticateWithUsernameAndPassword(username, password);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BadCredentialsException e) {
                log.info(e.toString());
            }
        }
        return "sendLog";
    }
    /*private Authentication tryToAuthenticateWithUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(requestAuthentication);
    }
    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("auth failed");
        }
        return responseAuthentication;
    }*/
    @RequestMapping("/print")
    public String sendWechat() throws Exception {
        String accessToken = UrlUtil.getAccessToken();

        MpNewsMsg msg = new MpNewsMsg();

        Article article = new Article(UrlUtil.upload("C:\\Users\\Donnie\\Desktop\\7c739d6.jpg",accessToken, "image" ),"testTitle");
        article.setDigest("this is the digest");
        article.setContent("Content");
        article.setShow_cover_pic(1);

        MpNews mpNews = new MpNews();
        List<Article> articleList = new ArrayList<Article>();
        articleList.add(article);
        mpNews.setArticles(articleList);

        msg.setTouser("@all");
        msg.setMsgtype("mpNews");
        msg.setAgentid(0);
        msg.setMpnews(mpNews);

        String jsonContent = gson.toJson(msg);
        String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;
        log.debug("--------------------------jsonMsg:{}",jsonContent);
        return UrlUtil.urlPost(sendUrl, jsonContent);
    }
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public String selectImg() {
        return "uploadImg";
    }
    @RequestMapping(value = "/img/upload", method = RequestMethod.POST)
    public String postImg(@RequestParam(value = "media") MultipartFile img) throws Exception {
        String result = uploadImg(img);
        return result;
    }
    public String uploadImg(MultipartFile img) throws Exception {
        String accessToken = UrlUtil.getAccessToken();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+accessToken;
        StringBuilder head = new StringBuilder("\r\n");
        String fileUrl = "C:\\Users\\Donnie\\Desktop\\"+img.getOriginalFilename();

        String mediaId = UrlUtil.upload(fileUrl, UrlUtil.getAccessToken(), "image");

        return mediaId;
    }

}
