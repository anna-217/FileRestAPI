package us.finra.assignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import us.finra.assignment.dao.MetaDataDAO;
import us.finra.assignment.entity.MetaData;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitTest_MetaDataDAO {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private MetaDataDAO metaDataDAO;
	
	@MockBean
	MetaData meta;
	
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
		this.entityManager.persist(meta);
		MetaData metaData = this.metaDataDAO.getMetaDataById(10);
		assertThat(metaData.getCreationTime()== (long) 1490456);
		assertThat(metaData.getName().equals("test.txt"));
		assertThat(metaData.getType().equals("txt"));
	}
}
