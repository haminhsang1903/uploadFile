package com.sanghub.uploadfile.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class AjaxController {
	@RequestMapping("ajax")
	public String ajax() {
		return "uploadAjax";
	}
	
	@PostMapping("/file-upload")
	@ResponseBody
	public ResponseEntity<String> fileUpload(MultipartFile file) {
	    try {

	        // upload directory - change it to your own
	        String UPLOAD_DIR = "/uploads";

	        // create a path from file name
	        Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());

	        // save the file to `UPLOAD_DIR`
	        // make sure you have permission to write
	        Files.write(path, file.getBytes());
	    }
	    catch (Exception ex) {
	        ex.printStackTrace();
	        return new ResponseEntity<>("Invalid file format!!", HttpStatus.BAD_REQUEST);
	    }

	    return new ResponseEntity<>("File uploaded!!", HttpStatus.OK);
	}
}
