package com.jyd.hbase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HbaseDAO {
	List<Map<String, String>> getRowsWithColumns(String tableName, List<String> rowKeys, List<String> columns)
			throws IOException;

	void update(String tableName, String rowKey, Map<String, String> fieldNameValues) throws IOException;

	Map<String, String> getRowsWithColumns(String tableName, String rowKey, List<String> columns) throws IOException;
}
