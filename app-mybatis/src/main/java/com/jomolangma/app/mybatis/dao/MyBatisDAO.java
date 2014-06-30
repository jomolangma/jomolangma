package com.jomolangma.app.mybatis.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jomolangma.app.mybatis.page.Page;
import com.jomolangma.app.mybatis.page.PageRequest;
import com.jomolangma.app.mybatis.plugin.SelectCountSqlInterceptor;

public class MyBatisDAO<T> extends SqlSessionDaoSupport {

	private static Logger logger = LoggerFactory.getLogger(MyBatisDAO.class);

	/**
	 * 插入一个实体
	 * 
	 * @param sqlMapId
	 * @param object
	 * @return
	 */
	public int insert(final String sqlMapId, final T object) {
		return (Integer) execute(new SqlSessionCallback() {
			public Object doInSession(SqlSession session) {
				return session.insert(sqlMapId, object);
			}
		});
	}

	/**
	 * 插入多个实体
	 * 
	 * @param sqlMapId
	 * @param object
	 * @return
	 */
	public int insert(final String sqlMapId, final Collection<T> object) {
		return (Integer) execute(new SqlSessionCallback() {
			public Object doInSession(SqlSession session) {
				return session.insert(sqlMapId, object);
			}
		});
	}

	/**
	 * 查询列表
	 * 
	 * @param sqlMapId
	 * @return
	 */
	public List<T> findForList(String sqlMapId) {
		return findForList(sqlMapId, null);
	}

	/**
	 * 查询列表
	 * 
	 * @param sqlMapId
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findForList(final String sqlMapId, final Object param) {

		return (List<T>) execute(new SqlSessionCallback() {

			public Object doInSession(SqlSession session) {
				if (param != null) {
					return session.selectList(sqlMapId, param);
				} else {
					return session.selectList(sqlMapId);
				}
			}
		});
	}

	/**
	 * 查询列表
	 * 
	 * @param statement
	 * @param parameter
	 * @param offset
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findForList(final String statement, final Object parameter,
			final int offset, final int limit) {

		return (List<T>) execute(new SqlSessionCallback() {

			public Object doInSession(SqlSession session) {
				return session.selectList(statement, parameter, new RowBounds(
						offset, limit));
			}
		});
	}

	/**
	 * 
	 * 带有分页信息的查询
	 * 
	 * @param statement
	 * @param parameter
	 * @param offset
	 * @param limit
	 * @return
	 */
	public Page<T> findForPage(String sqlMapId, PageRequest pageRequest) {

		// 查询总数
		pageRequest.getFilters().put(SelectCountSqlInterceptor.COUNT, null); // 设置是否需要将sql转换成总数查询sql
		Number totalCount = (Number) findForObject(sqlMapId,
				pageRequest.getFilters());
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page<T>(pageRequest, 0);
		}

		pageRequest.getFilters().remove(SelectCountSqlInterceptor.COUNT);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.putAll(pageRequest.getFilters());
		Page<T> page = new Page<T>(pageRequest, totalCount.intValue());
		// 如果总量不大于待查的偏移量，则直接返回
		if (page.getTotalCount() <= pageRequest.getOffset())
			return page;

		List<T> list = findForList(sqlMapId, filters, page.getFirstResult(),
				page.getPageSize());

		page.setResult(list);

		return page;
	}

	private Object selectOne(final String sqlMapId, final Object param) {

		return execute(new SqlSessionCallback() {

			public Object doInSession(SqlSession session) {
				if (param != null)
					return session.selectOne(sqlMapId, param);
				else
					return session.selectOne(sqlMapId);
			}

		});
	}

	/**
	 * 查询得到一个实体
	 * 
	 * @param sqlMapId
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findForObject(final String sqlMapId, final Object param) {
		return (T) selectOne(sqlMapId, param);
	}

	/**
	 * 查询得到一个实体
	 * 
	 * @param sqlMapId
	 * @param param
	 * @return
	 */
	public T findForObject(final String sqlMapId) {
		return findForObject(sqlMapId, null);
	}

	public int findForCount(final String sqlMapId, final Object param) {
		return (Integer) selectOne(sqlMapId, param);
	}

	/**
	 * 修改
	 * 
	 * @param sqlMapId
	 * @param param
	 * @return
	 */
	public int update(final String sqlMapId, final Object object) {

		return (Integer) execute(new SqlSessionCallback() {

			public Object doInSession(SqlSession session) {
				if (object != null)
					return session.update(sqlMapId, object);
				else
					return session.update(sqlMapId);
			}

		});
	}

	/**
	 * 修改
	 * 
	 * @param sqlMapId
	 * @param param
	 * @return
	 */
	public int update(final String sqlMapId) {
		return update(sqlMapId, null);
	}

	/**
	 * 删除
	 * 
	 * @param sqlMapId
	 * @return
	 */
	public int delete(final String sqlMapId) {
		return delete(sqlMapId, null);
	}

	/**
	 * 带有参数删除
	 * 
	 * @param sqlMapId
	 * @param param
	 * @return
	 */
	public int delete(final String sqlMapId, final Object param) {

		return (Integer) execute(new SqlSessionCallback() {

			public Object doInSession(SqlSession session) {
				if (param != null) {
					return session.delete(sqlMapId, param);
				} else {
					return session.delete(sqlMapId);
				}
			}

		});
	}

	/**
	 * 允许回调,暴露SqlSession给调用者
	 * 
	 * @param action
	 * @return
	 */
	public Object execute(SqlSessionCallback action) {
		try {
			return action.doInSession(getSqlSession());
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw new RuntimeException("Database access error.", e);
		}
	}

	public interface SqlSessionCallback {
		public Object doInSession(SqlSession session);
	}
}