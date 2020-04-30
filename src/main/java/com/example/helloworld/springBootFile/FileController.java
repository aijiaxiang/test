package com.example.helloworld.springBootFile;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:aijiaxiang
 * Date:2020/3/5
 * Description:
 */
@Controller
@RequestMapping("")
public class FileController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PostMapping("/file")
    public FileInfo upload(MultipartFile file, HttpServletRequest request) throws Exception{
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());//原图片名
        System.out.println(file.getSize());//大小
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //图片名
        String fileName = URLEncoder.encode(file.getOriginalFilename(), "UTF-8");
        fileName=sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        //路径
        String path=request.getSession().getServletContext().getRealPath("/")+ "upload\\";
        File localFile = new File(path, fileName);

        file.transferTo(localFile);
        //返回json格式
        return new FileInfo(localFile.getAbsolutePath());

    }

    /**
     * 下载图片
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/{id}")//id为要下载的图片名：123.jsp
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        String path=request.getSession().getServletContext().getRealPath("/")+ "upload\\";

        //根据url获取输入流
        //URL url = new URL(zipUrl);
        //HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        //conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        //InputStream inputStream = conn.getInputStream();
        //-------------------------------------------

        //下载
        try (InputStream inputStream = new FileInputStream(new File(path, id ));
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + id);

            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片上传路径
     * @return
     * @throws Exception
     */
    public static String getPath() throws Exception {
        //路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists()) {
            upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        }
        return upload.getAbsolutePath();
    }


}
