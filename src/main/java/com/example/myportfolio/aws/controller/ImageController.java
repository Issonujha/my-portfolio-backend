package com.example.myportfolio.aws.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.myportfolio.aws.service.AwsService;
import com.example.myportfolio.common.CommonConstants;
import com.example.myportfolio.dto.ImageDTO;
import com.example.myportfolio.logging.LoggingService;

@RestController
@RequestMapping("/file")
public class ImageController {

	private AwsService awsService;

	public ImageController(AwsService awsService) {
		this.awsService = awsService;
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("description") String description, @RequestHeader("Authorization") String token) {
		ImageDTO imageDTO = null;
		if (StringUtils.hasText(token) && token.length() > 8) {
			try {
				// Process file (save, validate, etc.)
				String fileName = file.getOriginalFilename();
				long fileSize = file.getSize();
				imageDTO = awsService.uploadFile(file, fileName, fileSize, description, token);
				return ResponseEntity.ok(imageDTO);
			} catch (Exception e) {
				LoggingService.log(null, null, e);
				return ResponseEntity.badRequest().body(CommonConstants.SOMETHING_WENTS_WRONG);
			}
		} else {
			return ResponseEntity.badRequest().body(CommonConstants.DATA_VALID);
		}
	}

}
