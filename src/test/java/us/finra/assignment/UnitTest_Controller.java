package us.finra.assignment;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.finra.assignment.controller.MainController;
import us.finra.assignment.entity.MetaData;
import us.finra.assignment.service.FileService;
import us.finra.assignment.service.MetaDataService;
import us.finra.assignment.utility.Constants;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;


@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class UnitTest_Controller {
	
	@MockBean
	private MetaDataService metaDataService;
	
	@MockBean
	private FileService fileService;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private Constants constans;
	
	MetaData meta;
	String jsonMeta;
	
	MockMultipartFile file1;
	MockMultipartFile file2;
	
	
	@Before
	public void setup() throws JsonProcessingException{
		meta = new MetaData();
		meta.setCreationTime((long) 1490456);
		meta.setFilePath("E:\\finra files\\test.txt");
		meta.setId(10);
		meta.setName("test.txt");
		meta.setType(null);
		
		ObjectMapper mapper = new ObjectMapper();
		jsonMeta = mapper.writeValueAsString(meta);
	}
	
	@Before
	public void setupFile() throws IOException {
		file1 = new MockMultipartFile("file", "test.txt", null, "this is a mock test".getBytes());
		
/*		File fpath = new File("E:\\finra files\\test.txt");
		FileInputStream inputStream = new FileInputStream(fpath);
		file2 = new MockMultipartFile("file", "test.txt", null, inputStream);*/
	}
	
	
//	@Ignore
	@Test
	public void testGetMetaByID() throws Exception {		
		given(this.metaDataService.getMetaDataById(10)).willReturn(meta);
		this.mvc.perform(get("/getMetaById?id=10"))
				.andExpect(status().isOk()).andExpect(content().json(jsonMeta));
	}
	
//	@Ignore
	@Test
	public void testUpload () throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/upload").file(file1);
		this.mvc.perform(builder).andExpect(status().isOk()).andReturn().equals("upload file ");
	}
	
	@Test
	public void testGetIds() throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(22);
		
		given(this.metaDataService.getIdList()).willReturn(list);
		this.mvc.perform(get("/ids")).andExpect(status().isOk()).andReturn().equals(list);
	}
}
