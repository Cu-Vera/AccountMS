package cn.edu.jsu.zct.service;

import java.util.List;

public interface IService<K,V> {

	public boolean insert(V vo) throws Exception;
	public boolean update(V vopr,V vo) throws Exception;
	public boolean delete(V vo) throws Exception;
	public List<V> listById(K id) throws Exception;
	public List<V> listAll() throws Exception;
}
