package cn.edu.jsu.zct.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.edu.jsu.zct.dbc.DatabaseConnection;
import cn.edu.jsu.zct.factory.DAOFactory;
import cn.edu.jsu.zct.service.IProjectService;
import cn.edu.jsu.zct.vo.Project;

public class ProjectServiceImpl implements IProjectService {

	private DatabaseConnection dbc = new DatabaseConnection();
	
	@Override
	public boolean insert(Project vo) throws Exception {
		try {
			return DAOFactory.getIProjectDAOInstance(dbc.getConnection()).doCreate(vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Project vopr, Project vo) throws Exception {
		try {
			return DAOFactory.getIProjectDAOInstance(dbc.getConnection()).doUpdate(vopr, vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Project vo) throws Exception {
		try {
			DAOFactory.getIAccountDAOInstance(dbc.getConnection(), "Income").doRemoveByPrj(vo.getId(), vo.getName());
			return DAOFactory.getIProjectDAOInstance(dbc.getConnection()).doRemove(vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Project> listById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listPrjById(String id) throws Exception {
		List<String> pr = new ArrayList<String>();
		try {
			Iterator<Project> iter = DAOFactory.getIProjectDAOInstance(dbc.getConnection()).findById(id).iterator();
			while(iter.hasNext()) {
				pr.add(iter.next().getName());
			}
			return pr;
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

}
