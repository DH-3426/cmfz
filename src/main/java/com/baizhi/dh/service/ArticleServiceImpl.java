package com.baizhi.dh.service;

import com.baizhi.dh.config.ChangUrlUtil;
import com.baizhi.dh.dao.ArticleDao;
import com.baizhi.dh.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao dao;

    @Override
    public HashMap selectAll(Integer page, Integer rows) {
        int i = dao.selectCount(null);
        HashMap<String, Object> map = new HashMap<>();
        //总条数
        map.put("records",i);
        Integer total=0;
        if(i%rows==0){
            total=i/rows;
        }else{
            total=i/rows+1;
        }
        //总页数
        map.put("total",total);
        //当前页
        map.put("page",page);
        //要展示的数据
        List<Article> articles = dao.selectByRowBounds(null, new RowBounds((page - 1)*rows, rows));
        map.put("rows",articles);
        return map;
    }

    @Override
    public HashMap edit(Article article, String edit, MultipartFile inputfile,HttpServletRequest request) {
        HashMap hashMap = new HashMap();
        if(edit.equals("add")){
            String path="/upload/articleImg/";
            String s1 = ChangUrlUtil.changUrl(inputfile, request,path);
            String s = UUID.randomUUID().toString();
            article.setId(s);
            article.setImg(s1);
            article.setCreateDate(new Date());
            article.setPublishDate(new Date());
            dao.insert(article);
            hashMap.put("articleId",article.getId());
        }else if(edit.equals("del")){
            dao.delete(article);
        }else{
            String path="/upload/articleImg/";
                String s1 = ChangUrlUtil.changUrl(inputfile, request,path);
                article.setImg(s1);
            dao.updateByPrimaryKeySelective(article);
            hashMap.put("articleId",article.getId());
        }
        return hashMap;
    }
    @Override
    public void delete(Article article){
        dao.delete(article);
    }

    @Override
    public Article selectOne(String id) {
        Article article = new Article();
        article.setId(id);
        Article article1 = dao.selectOne(article);
        return article1;
    }
}
