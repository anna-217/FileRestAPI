package us.finra.assignment.dao;

import java.util.List;

import us.finra.assignment.entity.MetaData;

public interface MetaDataDAO {
	
	public void saveMetaData (MetaData data);
	
	public MetaData getMetaDataById (Integer id);
	
	public String getFilePathByName(String name);
	
	public MetaData getMetaDataByFileName(String fileName);
	
	public List<Integer> getIdList();
	
	public List<MetaData> getNewItemInLastHour(); 
}
