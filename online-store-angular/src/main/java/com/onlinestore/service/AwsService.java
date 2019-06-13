package com.onlinestore.service;

import java.io.IOException;

import javax.servlet.ServletRequest;

public interface AwsService {
	
	public void uploadImage(ServletRequest request, Long id) throws IOException;

}
