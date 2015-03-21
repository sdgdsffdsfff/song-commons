package com.song.commons.dao.keygen;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.apache.commons.dbcp.BasicDataSource;

public class KeyInfo {

	private long keyMax;
	private long keyMin;
	private long nextKey;
	private int poolSize;
	private String keyName;
	private boolean runDB;

	public KeyInfo(int poolSize, String keyName) {
		this.poolSize = poolSize;
		this.keyName = keyName;
	}

	public long getKeyMax() {
		return keyMax;
	}

	public long getKeyMin() {
		return keyMin;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public String getKeyName() {
		return keyName;
	}

	public long getNextKey(BasicDataSource dataSource,
			PlatformTransactionManager tm) {
		if (nextKey > keyMax || !runDB) {
			this.retrieveFromDB(dataSource, tm);
		}
		return nextKey++;
	}

	private void retrieveFromDB(final BasicDataSource dataSource,
			PlatformTransactionManager tm) {
		TransactionTemplate tt = new TransactionTemplate(tm);
		tt.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
				JdbcTemplate jt = new JdbcTemplate(dataSource);
				String sql1 = "UPDATE KEY_TABLE SET KEY_VALUE=KEY_VALUE+"
						+ poolSize + " WHERE KEY_NAME='" + keyName + "'";
				String sql2 = "SELECT KEY_VALUE FROM KEY_TABLE WHERE KEY_NAME='"
						+ keyName + "'";
				jt.update(sql1);
				long keyFormDB = jt.queryForLong(sql2);
				keyMax = keyFormDB;
				keyMin = keyFormDB - poolSize + 1;
				nextKey = keyMin;
				runDB = true;
				return null;
			}
		});
	}
}
