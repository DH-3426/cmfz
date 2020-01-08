package com.baizhi.dh.service;

import com.baizhi.dh.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ArticleService {
    public HashMap selectAll(Integer page, Integer rows);
    public HashMap edit(Article article, String edit, MultipartFile inputfile, HttpServletRequest request);
    public void delete(Article article);
    public Article selectOne(String id);
}
