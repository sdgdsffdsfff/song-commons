package com.song.commons.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import com.song.commons.Sequence;
import com.song.commons.dao.keygen.KeyGenerator;

public abstract class BasicDao<T> {

	private JdbcTemplate jdbcTemplate;
	
	private BasicDataSource dataSource;
	
	private PlatformTransactionManager transactionManager;

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * 从数据库到对象的转化
	 * 
	 * @param rs
	 * @param rowNum
	 * @return
	 * @throws SQLException
	 */
	abstract protected T rowMapper(ResultSet rs, int rowNum)
			throws SQLException;

	/**
	 * 获得表字段字符的拼接
	 * 
	 * @param tableName
	 * @return
	 */
	abstract protected String getFields(String tableName);

	/**
	 * 获取对应表字段"?"字符拼接
	 * 
	 * @return
	 */
	abstract protected String getParamMarks();

	/**
	 * 获取对象成员变量的值，并将所有值返回装入数组
	 * 
	 * @param t
	 * @return
	 */
	abstract protected Object[] getParams(final T t);

	/**
	 * 组装SQL，Where查询条件（用于比较单纯而简单的查询）
	 * 
	 * @param t
	 * @param sqlWhere
	 * @return
	 */
	abstract protected List<Object> getParams(final T t,
			final StringBuffer sqlWhere);

	/**
	 * 数据持久保存时，对象初始化
	 * 
	 * @param t
	 */
	abstract protected void init(final T t);

	/**
	 * 获取表的主键值
	 * 
	 * @param tables
	 * @param length
	 * @return
	 */
	public String getId(Tables tables, int length) {
		return Sequence.getInstance().getSequence(length);
	}

	public long getId(Tables tables) {
		return KeyGenerator.getInstance().getNextKey(tables.toString(),
				dataSource, transactionManager);
	}

	public T getEntity(String sql, Object id) {
		Object[] params = new Object[] { id };
		return getEntity(sql, params);
	}

	public T getEntity(String sql, Object[] params) {
		/*
		 * List<T> list = jdbcTemplate.query(sql, params, new RowMapper<T>() {
		 * public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		 * return rowMapper(rs, rowNum); } }); if (!list.isEmpty()) { return
		 * list.get(0); } else { return null; }
		 */
		return jdbcTemplate.query(sql, new ResultSetExtractor<T>() {

			@Override
			public T extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					return rowMapper(rs, 0);
				} else {
					return null;
				}
			}

		}, params);
	}

	public List<T> getEntityList(String sql, Object[] params) {
		return jdbcTemplate.query(sql, params, new RowMapper<T>() {
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rowMapper(rs, rowNum);
			}
		});
	}

	public int delEntity(String sql, Object id) {
		return jdbcTemplate.update(sql, new Object[] { id });
	}

	public int delEntity(String sql, Object[] params) {
		return jdbcTemplate.update(sql, params);
	}

	public int addEntity(String sql, Object[] params) {
		return jdbcTemplate.update(sql, params);
	}

	public int updateEntity(String sql, Object[] params) {
		return jdbcTemplate.update(sql, params);
	}

	public String getSqlStr(String sql, int startIndex, int endIndex) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (").append(sql).append(") T_NEW");
		sb.append(" LIMIT ").append(startIndex).append(",").append(endIndex);
		return sb.toString();
	}

}
