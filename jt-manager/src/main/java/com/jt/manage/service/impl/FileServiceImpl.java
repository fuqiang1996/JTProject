package com.jt.manage.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Service
public class FileServiceImpl implements FileService{
	@Value("${localPath}")
	private String LPath; //本地地址
	@Value("${urlPath}")
	private String uPath; //虚拟地址
	/**
	 * 文件上传步骤
	 */
	@Override
	public PicUploadResult uploadFile(MultipartFile uploadFile) {
		System.out.println(uploadFile);
		System.out.println(LPath);
		System.out.println(uPath);
		
		//1.new 返回值
		PicUploadResult result = new PicUploadResult();
		//2.获取文件名称
		String fileName = uploadFile.getOriginalFilename();
		//3.判断是否为图片类型
		if(!fileName.matches("^.*(jpg|png|gif)$")){
			result.setError(1);
			return result;
		}
		try {
			//4.判断是否为恶意程序
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			//5.获取图片的宽高
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			if(height == 0 || width == 0){
				//表示不是图片
				result.setError(1);
				return result;
			}
			
			//如果程序执行到这里表示图片正常
			//6.定义本地磁盘的路径  全局变量已经定义
			
			//7.采用时间格式份文件存储 yyyy/MM/dd/HH
			String datePath = new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());
			
			//8.拼接存储文件  D:/Fileupload/yyyy/MM/dd/HH
			String filePath = LPath + datePath ;
			System.out.println("FilePath:"+filePath);
			//判断文件是否存在
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdirs(); //创建文件夹
			}
			
			//9 重构文件名 让文件名尽可能不一致
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			//使用uuid生成随机数
			String uuid = UUID.randomUUID().toString().replace("-", "");
			//形成文件的完整路径  D:/Fileupload/yyyy/MM/dd/HH/adfjklfdl.jsp
			String realPath = filePath +"/"+ uuid + fileType; 
			
			//10 写盘操作
			uploadFile.transferTo(new File(realPath));
			
			// 11 封装虚拟路径 用于用户展示 全局变量已经写好
			
			//12 拼接虚拟路径
			// http://image.jt.com/2018/03/02/
			String realUrlPath = uPath + datePath + "/"+ uuid + fileType;
			
			//赋值url
			result.setUrl(realUrlPath);
			result.setHeight(height+"");
			result.setWidth(width+"");
			return result;
			
		} catch (Exception e) {
			
			result.setError(1);
			return result;
		}
	}

}
