package com.campus.common;



import com.campus.model.ActFile;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
	public static void downloadFile(String fileId,GridFsTemplate gridFsTemplate,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		
		
		
		
		/*
		Query query = Query.query(Criteria.where("_id").is(fileId));
		//查询单个文件
//		GridFSDBFile gfsfile = gridFsTemplate.findOne(query);
		com.mongodb.client.gridfs.model.GridFSFile gridFsFile = gridFsTemplate.findOne(query);
//		GridFsResource resource = gridFsTemplate.getResource(findOne.getId().toString());
        com.mongodb.client.gridfs.model.GridFSFile findOne = gridFsTemplate.findOne(query);
		if (gridFsFile == null) {
			 throw new ClassCastException("文件查找失败");
        }
        String fileName = findOne.getFilename().replace(",", "");
        //处理中文文件名乱码
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 通知浏览器进行文件下载
        response.setContentType(findOne.getContentType());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
//        findOne.writeTo(response.getOutputStream());
        InputStream inputStream = resource.getInputStream();
        */
		
    }
	
	 

}
