package cn.edu.jsu.zct.service.impl;

import java.util.List;

import cn.edu.jsu.zct.dbc.DatabaseConnection;
import cn.edu.jsu.zct.factory.DAOFactory;
import cn.edu.jsu.zct.service.IUser_Service;
import cn.edu.jsu.zct.vo.User_;

public class User_ServiceImpl implements IUser_Service {
	
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(User_ vo) throws Exception {
		try {
			return DAOFactory.getIUser_DAOInstance(dbc.getConnection()).doCreate(vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(User_ vopr, User_ vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User_ vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User_> listById(String id) throws Exception {
		try {			
			return DAOFactory.getIUser_DAOInstance(dbc.getConnection()).findById(id);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<User_> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
