package cn.edu.jsu.zct.dao;

import java.util.List;

public interface IDAO<K,V> {

	public boolean doCreate(V vo) throws Exception;
	public boolean doUpdate(V vopr,V vo) throws Exception;
	public boolean doRemove(V vo) throws Exception;
	public List<V> findById(K id) throws Exception;
	public List<V> findAll() throws Exception;
}
