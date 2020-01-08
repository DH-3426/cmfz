package com.baizhi.dh.controller;

import com.baizhi.dh.config.HttpUtil;
import com.baizhi.dh.entity.Album;
import com.baizhi.dh.entity.Chapter;
import com.baizhi.dh.service.AlbumService;
import com.baizhi.dh.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService service;
    @Autowired
    ChapterService chapterService;
    @RequestMapping("queryAll")
    public HashMap queryAll(Integer page,Integer rows){
        HashMap hashMap = service.selectAll(page, rows);
        return hashMap;
    }
    @RequestMapping("edit")
    public  HashMap edit(Album album,String oper){
        HashMap edit = service.edit(album, oper);
        return  edit;
    }
    @RequestMapping("queryAllChap")
    public HashMap queryAllChap(String id,Integer page,Integer rows){
        HashMap hashMap = chapterService.selectAll(id, page, rows);
        return hashMap;
    }
    @RequestMapping("chapEdit")
    public HashMap chapEdit(String albumId,String oper){
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        HashMap edit = chapterService.edit(chapter, oper);
        return edit;
    }
    @RequestMapping("updateImagePath")
    public HashMap updatePth(MultipartFile cover, String albumId, HttpServletRequest request, HttpSession session){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/albumImg/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        // 网络路径
        String http = HttpUtil.getHttp(cover, request, "/upload/albumImg/");
        Album album = new Album();
        album.setId(albumId);
        album.setCover(http);
        String edit="edit";
        service.edit(album,edit);
        hashMap.put("status",200);
        System.out.println(http+"*******");
        return hashMap;
    }
    @RequestMapping("uploadChapPath")
    public void updateChapPath(MultipartFile url, String id, HttpServletRequest request, HttpSession session) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        String path="/upload/music";
        String realPath = session.getServletContext().getRealPath(path);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String http = HttpUtil.getHttp(url, request, path);
        //执行修改  修改文件的路径
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setUrl(http);
        // 计算文件大小
        Double size = Double.valueOf(url.getSize()/1024/1024);
        chapter.setSize(size);
        // 计算音频时长
        // 使用三方计算音频时间工具类 得出音频时长
        String[] split = http.split("/");
        // 获取文件名
        String name = split[split.length-1];
        // 通过文件获取AudioFile对象 音频解析对象
        AudioFile read = AudioFileIO.read(new File(http, name));
        // 通过音频解析对象 获取 头部信息 为了信息更准确 需要将AudioHeader转换为MP3AudioHeader
        MP3AudioHeader audioHeader = (MP3AudioHeader) read.getAudioHeader();
        // 获取音频时长 秒
        int trackLength = audioHeader.getTrackLength();
        String time = trackLength/60 + "分" + trackLength%60 + "秒";
        chapter.setTime(time);
        String edit="edit";
        chapterService.edit(chapter,edit);
    }
    @RequestMapping("downloadChap")
    public void downloadChap(String url, HttpServletResponse response, HttpSession session) throws IOException {
        // 处理url路径 找到文件
        String[] split = url.split("/");
        String realPath = session.getServletContext().getRealPath("/upload/music/");
        String name = split[split.length-1];
        File file = new File(realPath, name);
        // 调用该方法时必须使用 location.href 不能使用ajax ajax不支持下载
        // 通过url获取本地文件
        response.setHeader("Content-Disposition", "attachment; filename="+name);
        ServletOutputStream outputStream = response.getOutputStream();
        FileUtils.copyFile(file,outputStream);
        // FileUtils.copyFile("服务器文件",outputStream)
        //FileUtils.copyFile();
    }

    //专辑详情
    @RequestMapping("selectOne")
    public HashMap selectOne(String id,String uid){
        Album album = service.selectOne(id);
        Chapter chapter = new Chapter();
        chapter.setAlbumId(id);
        List<Chapter> chapters = chapterService.selectOne(chapter);
        HashMap hashMap = new HashMap();
        hashMap.put("status", 200);
        hashMap.put("album",album);
        hashMap.put("list",chapters);
        return hashMap;
    }
}
