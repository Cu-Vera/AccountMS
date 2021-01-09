package cn.edu.jsu.zct.service;

import java.util.List;

import cn.edu.jsu.zct.vo.Account;

public interface IAccountService extends IService<String, Account> {
	public List<Account> listById(String id, String type) throws Exception;
	public List<Account> listAll(String type) throws Exception;
}
