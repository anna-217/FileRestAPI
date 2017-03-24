package us.finra.assignment.service;


import java.util.List;
import us.finra.assignment.entity.MetaData;

public interface MetaDataService {
	
	public void save (MetaData data);
	
	public MetaData getMetaDataById (Integer id);
	
	public String getFilePath (String name);
	
	public MetaData getMetaByFileName(String name);
	
	public List<Integer> getIdList();
	
	public List<MetaData> getNewItemInLastHour(); 
	
}
