package com.example.myportfolio.aws.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.myportfolio.CommonConstants;
import com.example.myportfolio.dto.ImageDTO;
import com.example.myportfolio.entity.ImageEntity;
import com.example.myportfolio.entity.User;
import com.example.myportfolio.jwt.JwtAuthenticationHelper;
import com.example.myportfolio.mapper.ImageMapper;
import com.example.myportfolio.repository.ImageRepository;
import com.example.myportfolio.repository.UserRepository;
import com.example.myportfolio.service.EmailService;

@Service
public class AwsService {

	private Logger logger = LoggerFactory.getLogger(AwsService.class);

	public static final String S3_URL = "https://%s.s3.%s.amazonaws.com/";

	private final AmazonS3 s3Client;

	private JwtAuthenticationHelper jwtAuthenticationHelper;

	private UserRepository userRepository;

	private ImageRepository imageRepository;
	
	private EmailService emailService;

	@Value("${aws.bucketName}")
	private String bucketName;

	@Value("${aws.region}")
	private String region;

	public AwsService(AmazonS3 s3Client, JwtAuthenticationHelper jwtAuthenticationHelper, UserRepository userRepository,
			ImageRepository imageRepository, EmailService emailService) {
		this.s3Client = s3Client;
		this.jwtAuthenticationHelper = jwtAuthenticationHelper;
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
		this.emailService = emailService;
	}

	public ImageDTO uploadFile(MultipartFile file, String key, long fileSize, String description, String token)
			throws IOException {
		String username = jwtAuthenticationHelper.getUsernameFromToken(token.substring(7));
		ImageDTO imageDTO = null;
		if (StringUtils.hasText(username)) {
			User currentUser = userRepository.findByUsername(username).orElse(null);
			if (currentUser != null) {
				key = generateKey(currentUser, key);
				PutObjectRequest request = new PutObjectRequest(bucketName, key, file.getInputStream(), null);
				s3Client.putObject(request);
				ImageEntity imageEntity = ImageEntity.builder().awsUrl(s3Client.getUrl(bucketName, key).toString())
						.description(description).fileName(file.getName()).size(fileSize).userId(currentUser.getId())
						.build();
				// Need to send mail service
				imageRepository.save(imageEntity);
				imageDTO = ImageMapper.INSTANCE.toDTO(imageEntity);
			} else {
				logger.info(CommonConstants.USER_NOT_FOUND);
			}
		} else {
			logger.info(CommonConstants.DATA_VALID);
		}
		return imageDTO;
	}

	private String generateKey(User currentUser, String key) {
		return "public/emp/" + currentUser.getId() + "/" + key;
	}

}
