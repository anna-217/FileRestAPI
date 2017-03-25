package us.finra.assignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import us.finra.assignment.dao.MetaDataDAO;
import us.finra.assignment.entity.MetaData;
import us.finra.assignment.service.MetaDataService;

import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest_MetaDataService {
	
	@Autowired
	private MetaDataService metaDataService;
	
	@MockBean
	private MetaDataDAO dao;
	
	@MockBean
	private MetaData meta;
	
	@Before
	public void setupMetaData() {
		meta.setCreationTime((long) 1490456);
		meta.setFilePath("E:\\finra files\\test.txt");
		meta.setId(10);
		meta.setName("test.txt");
		meta.setType("txt");
	}
	
	@Test
	public void testGetMetaById() {
		given(dao.getMetaDataById(10)).willReturn(meta);
		assert(metaDataService.getMetaDataById(10).equals(meta));
	}
	
	@Test
	public void testGetMetaByName() {
		given(dao.getMetaDataByFileName("test.txt")).willReturn(meta);
		assert(metaDataService.getMetaByFileName("test.txt").equals(meta));
	}
}
