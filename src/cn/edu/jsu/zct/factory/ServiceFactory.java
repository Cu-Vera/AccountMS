package cn.edu.jsu.zct.factory;

import cn.edu.jsu.zct.service.IAccountService;
import cn.edu.jsu.zct.service.IProjectService;
import cn.edu.jsu.zct.service.IUser_Service;
import cn.edu.jsu.zct.service.impl.AccountServiceImpl;
import cn.edu.jsu.zct.service.impl.ProjectServiceImpl;
import cn.edu.jsu.zct.service.impl.User_ServiceImpl;

public class ServiceFactory {

	public static IAccountService getIAccountInstance() {
		return new AccountServiceImpl();
	}
	
	public static IProjectService getIProjectInstance() {
		return new ProjectServiceImpl();
	}
	
	public static IUser_Service getIUser_Instance() {
		return new User_ServiceImpl();
	}
}
