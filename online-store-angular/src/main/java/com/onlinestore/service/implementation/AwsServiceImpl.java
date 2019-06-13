package com.onlinestore.service.implementation;

import java.io.IOException;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.client.AwsClient;
import com.onlinestore.service.AwsService;

@Service
public class AwsServiceImpl implements AwsService{
	
	@Autowired
	private AwsClient client;
	
	public void uploadImage(ServletRequest request, Long id) throws IOException {
		this.client.makeRequest(request, id);
	}

}
