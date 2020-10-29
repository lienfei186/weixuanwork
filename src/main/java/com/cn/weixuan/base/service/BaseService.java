package com.cn.weixuan.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cn.weixuan.base.dao.BaseDao;
import com.cn.weixuan.base.module.BaseModule;
import com.cn.weixuan.tools.ToolString;

import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseService<Dao extends BaseDao<T>, T extends BaseModule<T>> {
	protected final static Logger LOGGER = LoggerFactory.getLogger(BaseService.class);
	/**
	 * 持久层对象
	 */
	@Autowired
	protected Dao dao;

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(T t) {
		return dao.get(t);
	}

	@Transactional(readOnly = false)
	public void insert(T entity) {
		dao.insert(entity);
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getID() == null || "".equals(entity.getID())) {
			entity.setID(ToolString.getUUID());
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}
}
