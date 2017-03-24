package us.finra.assignment.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import us.finra.assignment.entity.MetaData;

public interface FileService {
	
	public MetaData extractMetaData (MultipartFile file);
	public void saveFile(MultipartFile file) throws IOException;	
}
