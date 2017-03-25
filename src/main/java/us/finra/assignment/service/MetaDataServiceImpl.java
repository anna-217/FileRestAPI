package us.finra.assignment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.finra.assignment.dao.MetaDataDAO;
import us.finra.assignment.entity.MetaData;

@Service
public class MetaDataServiceImpl implements MetaDataService{

	@Autowired
	MetaDataDAO metaDAO;
	
	@Override
	@Transactional
	public void save(MetaData data) {
		// TODO Auto-generated method stub
		metaDAO.saveMetaData(data);
	}

	@Override
	@Transactional
	public MetaData getMetaDataById(Integer id) {
		// TODO Auto-generated method stub		
		return metaDAO.getMetaDataById(id);
	}

	@Override
	public String getFilePath(String name) {
		// TODO Auto-generated method stub
		return metaDAO.getFilePathByName(name);
	}

	@Override
	public MetaData getMetaByFileName(String name) {
		// TODO Auto-generated method stub
		return metaDAO.getMetaDataByFileName(name);
	}

	@Override
	public List<Integer> getIdList() {
		// TODO Auto-generated method stub
		return metaDAO.getIdList();
	}

	@Override
	public List<MetaData> getNewItemInLastHour() {
		// TODO Auto-generated method stub
		return metaDAO.getNewItemInLastHour();
	}


}
