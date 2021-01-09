package cn.edu.jsu.zct.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jsu.zct.dbc.DatabaseConnection;
import cn.edu.jsu.zct.factory.DAOFactory;
import cn.edu.jsu.zct.service.IAccountService;
import cn.edu.jsu.zct.vo.Account;

public class AccountServiceImpl implements IAccountService {

	private DatabaseConnection dbc = new DatabaseConnection();
	
	public AccountServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(Account vo) throws Exception {
		try {
			return DAOFactory.getIAccountDAOInstance(dbc.getConnection(), getType(vo.getClass().getName())).doCreate(vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Account vopr, Account vo) throws Exception {
		try {
			return DAOFactory.getIAccountDAOInstance(dbc.getConnection(), getType(vo.getClass().getName())).doUpdate(vopr, vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Account vo) throws Exception {
		try {
			return DAOFactory.getIAccountDAOInstance(dbc.getConnection(), getType(vo.getClass().getName())).doRemove(vo);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Account> listById(String id) throws Exception {
		List<Account> both = new ArrayList<Account>();
		try {
			both.addAll(DAOFactory.getIAccountDAOInstance(dbc.getConnection(), "Income").findById(id));
			both.addAll(DAOFactory.getIAccountDAOInstance(dbc.getConnection(), "Expense").findById(id));
			return both;
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Account> listById(String id, String type) throws Exception {
		try {
			return DAOFactory.getIAccountDAOInstance(dbc.getConnection(), type).findById(id);
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}
	
	@Override
	public List<Account> listAll() throws Exception {
		List<Account> all = new ArrayList<Account>();
		try {
			all.addAll(DAOFactory.getIAccountDAOInstance(dbc.getConnection(), "Income").findAll());
			all.addAll(DAOFactory.getIAccountDAOInstance(dbc.getConnection(), "Expense").findAll());
			return all;
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Account> listAll(String type) throws Exception {
		try {
			if("Income".equals(type)||"Expense".equals(type))
				return DAOFactory.getIAccountDAOInstance(dbc.getConnection(), type).findAll();
			return null;
		}catch(Exception e){
			throw e;
		}finally {
			this.dbc.close();
		}
	}

	private String getType(String s) {
		String[] as = null;
		as = s.split("[.]");
		return as[as.length-1];
	}
}
