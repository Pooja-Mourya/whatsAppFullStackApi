package com.example.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	public String uploadImage(String path, MultipartFile uploadFile) throws IOException {
 
//       file name
		String name = uploadFile.getOriginalFilename();

//       String randomId= UUID.randomUUID().toString();
		String currentTimestamp = String.valueOf(Instant.now().toEpochMilli());
		String currentName = currentTimestamp.concat(name.substring(name.lastIndexOf(".")));
//       full path
		String filePath = path + File.separator + currentName;

//       create a folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

//       file copy
		Files.copy(uploadFile.getInputStream(), Paths.get(filePath));

		return currentName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream iS = new FileInputStream(fullPath);
		return iS;
	}
}