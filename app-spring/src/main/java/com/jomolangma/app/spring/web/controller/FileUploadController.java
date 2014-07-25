package com.jomolangma.app.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@RequestMapping(value = "/fileope/upload", method = RequestMethod.POST)
	public String upload(
			HttpServletRequest request,
			@RequestParam(value = "myFile", required = false) MultipartFile[] files) {
		try {
			for (int i = 0; i < files.length; i++) {
				FileUploadUtils.upload(request, files[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping(value = "/upload.html")
	public String fileUpload(){
		return "upload";
	}
}
