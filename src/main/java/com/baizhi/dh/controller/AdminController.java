package com.baizhi.dh.controller;

import com.baizhi.dh.config.VerifyCodeUtil;
import com.baizhi.dh.entity.Admin;
import com.baizhi.dh.service.AdminService;
import com.opensymphony.xwork2.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
public class AdminController {
    @Autowired
    AdminService service;
    @ResponseBody
    @RequestMapping("login")
    public String login(String username,String password,String code,HttpSession session){
        //从session中获取code
        String imageCode = (String) session.getAttribute("imageCode");
        if(code.equals(imageCode)){
            Admin admin = service.selectAdmin(username);
            String password1 = admin.getPassword();
            if(admin==null){
                return "用户不存在";
            }else if(!password1.equals(password)){
                System.out.println(password1);
                System.out.println(password);
                return "密码错误";
            }else{
                return "ok";
            }
        }else{
            return "验证码错误";
        }
    }
    //获取验证码图片的方法
    @RequestMapping("getImageCode")
    public String getImageCode(HttpServletRequest request,HttpServletResponse response){
        //1.获取验证码随机字符  参数：生成随机数的位数
        String code = VerifyCodeUtil.generateVerifyCode(4);

        System.out.println("验证码:"+code);

        //获取session
        HttpSession session =request.getSession();
        //存储验证码随机字符
        session.setAttribute("imageCode", code);

        //2.将验证码随机字符生成验证码图片   (宽度，高度，随即字符)
        BufferedImage image = VerifyCodeUtil.getImage(255, 80, code);

        //设置响应格式
        response.setContentType("image/png");

        try {
            //3.响应验证码图片
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Action.NONE;
    }
}
