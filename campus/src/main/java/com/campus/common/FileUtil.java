package com.campus.common;


import com.campus.model.ActFile;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
//import com.jp.framework.common.model.ServiceResultHelper;
//import com.jp.framework.common.util.Constant;
//import com.jp.zpzc.entity.customized.FileInfoAO;
//import com.jp.zpzc.entity.customized.UserAO;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传下载的工具类
 * @author Administrator
 */

public class FileUtil {
	/**
	 * 上传文件到mongodb中,并返回文件名字\mongodb中文件id;
	 * 将文件id,文件名封装到ActFile实体中
	 * @param req
	 * @return
	 */
	public static ActFile uploadFile(HttpServletRequest req,String file ,GridFsTemplate  gridFsTemplate) {
		String fileName=null;
		String contentType=null;
		ObjectId objetId= null;
		try {
			//获取上传文件
			Part part=req.getPart(file);
			//获取提交的文件名
			fileName=part.getSubmittedFileName();
			//获得文件输入流
			InputStream ins=part.getInputStream();
			//获得文件类型
			contentType=part.getContentType();
			//将文件存储到mongodb中,mongodb将会返回这个文件的具体信息(文件编号)
		    objetId = gridFsTemplate.store(ins, fileName,contentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将结果集封装为实体类
		ActFile actfile=new ActFile();
		actfile.setApplyFile1(objetId.toString());
		actfile.setApplyFileName(fileName);
		return actfile;
		
		/*
		//获取当前用户（shiro权限框架的方法）
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // 获得提交的文件名
        String fileName = multiportFile.getOriginalFileName();
        // 获得文件输入流
        InputStream ins = multiportFile.getInputStream();
        // 获得文件类型
        String contentType = multiportFile.getContentType();
        // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
       ObjectId objectId = gridFsTemplate.store(ins, fileName, contentType);
       //将文件信息保存到关系型数据库中进行维护
        FileInfoAO fileInfo = new FileInfoAO();
        fileInfo.setContentType(contentType);
        fileInfo.setFileName(fileName);
        fileInfo.setLastUpdateBy(user != null ? user.getId() : null);
        fileInfo.setMongoFileId(objectId.toString());
		*/
	}
	
	/**
	 * 根据传入的文件id删除mongodb中文件的值
	 * @param objectId
	 */
	public static void deleteMongoFileByObjectId(String objectId,GridFsTemplate gridFsTemplate) {
		try {
			Query query=Query.query(Criteria.where("_id").is(objectId));
			//查询单个文件
//			GridFSDBFile  gfsFile=gridFsTemplate.findOne(query);
//			com.mongodb.client.gridfs.model.GridFSFile   gfsFile=gridFsTemplate.findOne(query);
			gridFsTemplate.delete(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文件id值获取文件的名称
	 * @param objectId
	 * @param gridFsTemplate
	 * @return
	 */
	public static String showMongoFileByObjectId(String objectId,GridFsTemplate gridFsTemplate) {
		String fileName=null;
		try {
			Query query=Query.query(Criteria.where("_id").is(objectId));
			//查询单个文件
//			GridFSDBFile  gfsFile=gridFsTemplate.findOne(query);
			com.mongodb.client.gridfs.model.GridFSFile  gfsFile=gridFsTemplate.findOne(query);
			fileName=gfsFile.getFilename();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	/**
	 * 根据文件id从mongodb中通知浏览器下载文件
	 * @param objectId
	 * @param gridFsTemplate
	 * @throws UnsupportedEncodingException 
	 */
//	public static void downloadFile(String fileId,GridFsTemplate gridFsTemplate,
//			HttpServletRequest request,HttpServletResponse response) throws Exception {
//
//		 Query query = Query.query(Criteria.where("_id").is(fileId));
//	        // 查询单个文件
//	        GridFSDBFile gfsfile = gridFsTemplate.findOne(query);
//	        if (gfsfile == null) {
//	            return;
//	        }
//	        String fileName = gfsfile.getFilename().replace(",", "");
//	        //处理中文文件名乱码
//	        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
//	                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
//	                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
//	            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//	        } else {
//	            //非IE浏览器的处理：
//	            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//	        }
//	        // 通知浏览器进行文件下载
//	        response.setContentType(gfsfile.getContentType());
//	        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
//	        gfsfile.writeTo(response.getOutputStream());
//		
//    }
	
	
	
	/**
     *使用由程序生成的唯一文件名下载获取文件
     * @param fileName 唯一文件名 uuid.后缀
     * @param request
     * @return
     * @throws IOException
     */
    public static ResponseEntity<Resource> downloadFileByFileName(String fileName,HttpServletRequest request,
    		GridFsTemplate gridFsTemplate,GridFSBucket gridFSBucket) throws IOException{
    	//查询单个文件
    	Query query = Query.query(Criteria.where("_id").is(fileName));
    	com.mongodb.client.gridfs.model.GridFSFile gfsfile = gridFsTemplate.findOne(query);
//        GridFSFile file = gridFsTemplate.findOne(query(whereFilename().is(fileName)));
    	if(gfsfile==null){
            throw new RuntimeException("无文件");
        }
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(gfsfile.getObjectId());
        GridFsResource resource = new GridFsResource(gfsfile,in);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"")
                .body((Resource) resource);
    }
 
    /**
     * 使用mongodb的ObjectId（的id）下载文件，此时文件以上传时的原始名保存下载
     * @param fileId  文件在mongodb的ObjectId（的id）
     * @param request
     * @param response
     * @throws IOException
     */
    public static void downloadFileByFileId(String fileId, HttpServletRequest request, HttpServletResponse response,
    		GridFsTemplate gridFsTemplate,GridFSBucket gridFSBucket) throws IOException{
    	
    	Query query = Query.query(Criteria.where("_id").is(fileId));
        com.mongodb.client.gridfs.model.GridFSFile file = gridFsTemplate.findOne(query);
        if(file==null){
            return  ;
        }
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file,in);
        String fileName = file.getFilename().replace(",", "");
        //处理中文文件名乱码
        String userAgent = request.getHeader("User-Agent").toUpperCase();
 
        if (userAgent.contains("MSIE") ||
                userAgent.contains("TRIDENT")
                || userAgent.contains("EDGE")
                || userAgent.contains("CHROME")
                || userAgent.contains("SAFARI")
                || userAgent.contains("MOZILLA")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
//          fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }
        // 通知浏览器进行文件下载
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        IOUtils.copy(resource.getInputStream(),response.getOutputStream());
        
    }

	
	
	
	 

}
