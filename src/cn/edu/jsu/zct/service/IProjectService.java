package cn.edu.jsu.zct.service;

import java.util.List;

import cn.edu.jsu.zct.vo.Project;

public interface IProjectService extends IService<String, Project> {

	public List<String> listPrjById(String id) throws Exception;
}
