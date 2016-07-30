package org.zerock.controller;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.util.MediaUtils;
import org.zerock.util.UploadFileUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author wayne
 * @version 1.0
 */
@Slf4j
@Controller
public class UploadController {

	@Resource(name = "uploadPath")
	private String uploadPath;

	private static final Joiner UNDER_BAR_JOINER = Joiner.on("_");

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {

	}

	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {
		log.info("original name : " + file.getOriginalFilename());
		log.info("size : " + file.getSize());
		log.info("content type : " + file.getContentType());

		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

		model.addAttribute("savedName", savedName);

		return "uploadResult";
	}

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {

	}

	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		log.info("originalName: " + file.getOriginalFilename());

		String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());

		return new ResponseEntity<>(uploadedFileName, HttpStatus.CREATED);
	}

	@ResponseBody
	@RequestMapping(value = "/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		ResponseEntity<byte[]> entity = null;

		log.info("FILE NAME : " + fileName);

		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
		MediaType mType = MediaUtils.getMediaType(formatName);

		HttpHeaders headers = new HttpHeaders();

		try (InputStream in = new FileInputStream(uploadPath + fileName)) {
			if (mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.lastIndexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" +
					new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}

			entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		log.info("delete file: " + fileName);

		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();

		MediaType mediaType = MediaUtils.getMediaType(formatName);

		if (mediaType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}

		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
	public ResponseEntity<String> deletefile(@RequestParam("files[]") String[] files) {
		log.info("delete all files: " + files);

		if (ArrayUtils.isEmpty(files)) {
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		}

		for (String fileName : files) {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
			MediaType mType = MediaUtils.getMediaType(formatName);

			if(mType != null) {
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
			}

			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		}

		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}

	private String uploadFile(String originalFilename, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID();

		String savedName = UNDER_BAR_JOINER.join(uuid, originalFilename);

		File target = new File(uploadPath, savedName);

		FileCopyUtils.copy(fileData, target);

		return savedName;
	}

}
