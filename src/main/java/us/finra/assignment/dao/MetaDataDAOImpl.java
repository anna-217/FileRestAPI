package us.finra.assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import us.finra.assignment.entity.MetaData;
import us.finra.assignment.utility.Constants;

@Repository
public class MetaDataDAOImpl implements MetaDataDAO{
	
	@PersistenceContext
	private EntityManager em; 
	
	@Autowired
	Constants cons;
	
	@Override
	@Transactional
	public MetaData getMetaDataById(Integer id) {
		// TODO Auto-generated method stub
		MetaData data = em.find(MetaData.class, id);
		return data;
	}
	@Override
	@Transactional
	public void saveMetaData(MetaData data) {
		// TODO Auto-generated method stub
		em.persist(data);
	}
	@Override
	public String getFilePathByName(String name) {
		// TODO Auto-generated method stub
		return cons.FILE_SYSTEM_PATH + name;
	}
	
	@Override
	@Transactional
	public MetaData getMetaDataByFileName(String name) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT Object(d) FROM MetaData d WHERE name = :name");
		query.setParameter("name", name);
		
		return (MetaData) query.getResultList().get(0);
	}
	
	@Override
	@Transactional
	public List<Integer> getIdList() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT d.id FROM MetaData d");
		
		return query.getResultList();
	}
	@Override
	@Transactional
	public List<MetaData> getNewItemInLastHour() {
		Long cur_time = System.currentTimeMillis();
		Long deadline = cur_time - cons.ONE_HOUR_IN_MS;
		Query query = em.createQuery("SELECT Object(m) FROM MetaData m WHERE creationTime > :time");
		query.setParameter("time", deadline);
		System.out.println(query.getResultList().size());
		return query.getResultList();
	}

	

}
