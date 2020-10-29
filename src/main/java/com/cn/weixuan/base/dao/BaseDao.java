package com.cn.weixuan.base.dao;

public interface BaseDao<T> {
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
}
