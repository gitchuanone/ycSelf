package com.campus.controller;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class TestUpDownFileController {
	// 获得SpringBoot提供的mongodb的GridFS对象  
    @Autowired  
    private GridFsTemplate gridFsTemplate;  
  
    // 上传文件控制器  
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)  
    @ResponseBody  
    public String uploadfile(HttpServletRequest request) {  
        String result = "error";  
        try {  
            /** 
             * Servlet3.0新增了request.getParts()/getPart(String filename) api， 
             * 用于获取使用multipart/form-data格式传递的http请求的请求体， 通常用于获取上传文件。 
             */  
            Part part = request.getPart("file");  
            
            // 获得提交的文件名  
            String filename = part.getSubmittedFileName();  
            // 获得文件输入流  
            InputStream ins = part.getInputStream();  
            // 获得文件类型  
            String contenttype = part.getContentType();  
            // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息  
            ObjectId gfs = gridFsTemplate.store(ins, filename, contenttype);  
            result = gfs.toString();  
  
        } catch (IOException e) {  
        } catch (ServletException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    
    // 下载文件控制器  
    /** 
     * 关于Query的具体用发下面的链接给的很清楚了，这里就不多说了。 
     * @link{http://www.baeldung.com/queries-in-spring-data-mongodb} 
     */ 
/*
    @RequestMapping(value = "/downloadfile", method = RequestMethod.POST)  
    @ResponseBody  
    public QueryResult<String> downloadfile(@RequestParam(name = "fname", required = true) String filename, HttpServletRequest request,HttpServletResponse response) {  
        Query query = Query.query(Criteria.where("filename").is(filename));  
  
        // 查询单个文件  
//        GridFSDBFile gfsfile = gridFsTemplate.findOne(query);  
        com.mongodb.client.gridfs.model.GridFSFile gfsfile = gridFsTemplate.findOne(query);  
  
        String fileString = FileUtil.downloadFile(gfsfile.getId().toString(),gridFsTemplate,gridFSBucket,request,response);
        OneResult<String> oneResult = new OneResult<>();
        oneResult.setResult(fileString);
        QueryOneResult
        return QueryResult.returnSuccess(oneResult);
    }  */
	
	
	
}
