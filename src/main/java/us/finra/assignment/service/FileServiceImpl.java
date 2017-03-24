package us.finra.assignment.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import us.finra.assignment.entity.MetaData;
import us.finra.assignment.utility.Constants;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	Constants consts;
	
	@Override
	public MetaData extractMetaData(MultipartFile file) {
		// TODO Auto-generated method stub
		MetaData metaData = new MetaData();
		metaData.setFilePath(consts.FILE_SYSTEM_PATH + file.getOriginalFilename());
		metaData.setName(file.getOriginalFilename());
		metaData.setType(file.getContentType());
		metaData.setCreationTime(System.currentTimeMillis());
		return metaData;
	}

	@Override
	public void saveFile(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		byte[] bytes = file.getBytes();
		Path path = Paths.get(consts.FILE_SYSTEM_PATH + file.getOriginalFilename());
		Files.write(path, bytes);
	}

}
