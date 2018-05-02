package com.jt.manage.service;

import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

/**
 * 文件上传
 * @author 富强
 *
 */
public interface FileService {

	/**
	 * 文件上传
	 * @param uploadFile
	 * @return
	 */
	PicUploadResult uploadFile(MultipartFile uploadFile);
	
	
}
