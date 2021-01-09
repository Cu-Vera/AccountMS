package cn.edu.jsu.zct.dao;

import cn.edu.jsu.zct.vo.Account;

public interface IAccountDAO extends IDAO<String, Account> {
	public boolean doRemoveByPrj(String id, String prj) throws Exception;
}
