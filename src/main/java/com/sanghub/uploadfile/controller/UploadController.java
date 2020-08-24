package com.sanghub.uploadfile.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

	private final String UPLOAD_DIR = "uploads/";

	@RequestMapping("/")
	public String homepage() {
		return "index";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model)
			throws InterruptedException {

		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/";
		}

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// save the file on the local file system
		try {
//			Path path = Paths.get(UPLOAD_DIR + fileName);
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//			byte[] bytes = file.getBytes();
//			BufferedOutputStream stream = new BufferedOutputStream(
//					new FileOutputStream(new File(UPLOAD_DIR + File.separator + file.getOriginalFilename())));
//			stream.write(bytes);
//			stream.flush();
//			stream.close();
			// Thư mục gốc upload file.
		//	String uploadRootPath = request.getServletContext().getRealPath("upload");
			

			File uploadRootDir = new File(UPLOAD_DIR);
			// Tạo thư mục gốc upload nếu nó không tồn tại.
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + file.getOriginalFilename());

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
			System.out.println(serverFile);
			//
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded !" + fileName + '!');
//		System.out.println(System.getProperty("home"));
		model.addAttribute("filename", fileName);
//		attributes.addFlashAttribute("filename", fileName);
		return "index";
	}
}
