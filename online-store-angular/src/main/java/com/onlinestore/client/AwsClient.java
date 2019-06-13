package com.onlinestore.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AwsClient {
	
	String clientRegion = "";
	String bucketName = "artprojectirl";
	
	AWSCredentials credentials = new BasicAWSCredentials
			("credentials","credentials"); 
	
	public void makeRequest(ServletRequest request, Long id) throws IOException {
		
		String stringObjKeyName = "";
		String fileObjKeyName = "";
		
		
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.EU_WEST_1)
				.build();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = multipartRequest.getFileNames();
		MultipartFile multipartFile = multipartRequest.getFile(iter.next());
		
		byte [] bytes = multipartFile.getBytes();
		InputStream stream = new ByteArrayInputStream(bytes);
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(request.getContentType());
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucketName, Long.toString(id), stream,metadata);
		
		amazonS3Client.putObject(putObjectRequest);
		
		System.out.println("File "+ id + "successfuly uploaded.");
	}

}
