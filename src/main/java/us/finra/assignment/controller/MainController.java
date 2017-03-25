package us.finra.assignment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import us.finra.assignment.entity.MetaData;
import us.finra.assignment.service.FileService;
import us.finra.assignment.service.MetaDataService;
import us.finra.assignment.utility.Constants;

@RestController
public class MainController {
	
	@Autowired
	MetaDataService metaService;
	@Autowired
	FileService fileService;
	
	@Autowired
	Constants consttents;
	
	@RequestMapping (value = "/getMetaById", method = RequestMethod.GET)
	public MetaData getMetaData(@RequestParam("id") Integer id) {
		return metaService.getMetaDataById(id);
	}
	
	@RequestMapping (value = "/getMetaByName", method = RequestMethod.GET)
	public MetaData getMetaByFileName(@RequestParam("fname") String name) {
		return metaService.getMetaByFileName(name);
	}
	
	@RequestMapping (value = "/ids", method = RequestMethod.GET)
	public List<Integer> getIDs() throws NullPointerException{
		return metaService.getIdList();
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile (@RequestParam("file") MultipartFile file) throws IOException{
				
		metaService.save(fileService.extractMetaData(file));
		fileService.saveFile(file);
		return "upload file ";
	}
	
	@RequestMapping(value = "/download/{fileName}.{extension}", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("fileName") String name, @PathVariable("extension") String ex) throws IOException, FileNotFoundException {
		String pString = metaService.getFilePath(name + "." + ex);
		File file = new File(pString);
	    InputStream in = new FileInputStream(file);	  
	    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
	    response.setHeader("Content-Length", String.valueOf(file.length()));
	    FileCopyUtils.copy(in, response.getOutputStream());
	}
	

	 @ExceptionHandler
	 @ResponseStatus (value = HttpStatus.BAD_REQUEST)
	 public String handleException(Exception e) {
		 e.printStackTrace();
		 return e.getLocalizedMessage();
	 }
}
