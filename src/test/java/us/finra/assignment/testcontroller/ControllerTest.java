package us.finra.assignment.testcontroller;

import java.io.File;
import java.io.FileInputStream;

import org.aspectj.lang.annotation.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;


@RunWith(SpringRunner.class)
public class ControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	FileInputStream fileInputStream;
	
	@Mock
	MockMultipartFile multipartFile;
	
/*	@Before
	public void setup() {
		
	}*/
	
	public void testUploadFile() {
		
	}
	
}
