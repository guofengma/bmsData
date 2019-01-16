//package com.jyd.service.front;
//
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.nio.ByteBuffer;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.apache.hadoop.hbase.thrift.generated.TCell;
//import org.apache.hadoop.hbase.thrift.generated.TRowResult;
//import org.apache.thrift.TException;
//
//import com.jyd.common.Const;
//import com.jyd.common.model.BaStore;
//import com.jyd.common.model.HbaseMapType;
//import com.jyd.tool.HBaseThrift1DAOImpl2;
//
///**
// * 门店业务层
// * 
// * @author hui
// *
// */
//public class StoreService {
//	public static final StoreService me = new StoreService();
//	public final HbaseMapType mapDao = new HbaseMapType().dao();
//	private HBaseThrift1DAOImpl2 client = new HBaseThrift1DAOImpl2();
//	private final BaStore dao = new BaStore().dao();
//	private DecimalFormat df = new DecimalFormat(".##");
//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//	protected byte[] decode(ByteBuffer buffer) {
//		byte[] bytes = new byte[buffer.limit()];
//		for (int i = 0; i < buffer.limit(); i++) {
//			bytes[i] = buffer.get();
//		}
//		return bytes;
//	}
//
//	public List<HbaseMapType> findByStoreId(String storeId) throws TException {
//		Map<String, String> map = new HashMap<>();
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DATE, 0);
//		String newDay = sdf.format(cal.getTime());
//		Map<String, String> attributes = new HashMap<String, String>();
//		List<String> rows = new ArrayList<String>();
//		rows.add(storeId);
//		List<String> columns = new ArrayList<String>();
//		for (int i = 0; i < Const.KEYS.length; i++) {
//			columns.add(Const.COLUMN_FAMILY + ":" + newDay + "_" + Const.KEYS[i]);
//		}
//		List<TRowResult> results = client.getRowsWithColumns(Const.TABLES[3], rows, columns, attributes);
//		for (TRowResult result : results) {
//			Iterator<Entry<ByteBuffer, TCell>> iter = result.columns.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<ByteBuffer, TCell> entry = iter.next();
//				map.put(new String(decode(entry.getKey())), new String(entry.getValue().getValue()));
//			}
//		}
//		List<HbaseMapType> hmtList = new ArrayList<>();
//		hmtList = findMapAll();
//		for (HbaseMapType hmt : hmtList) {
//			hmt.setValue(map.get(Const.COLUMN_FAMILY + ":" + newDay + "_" + hmt.getKeyName()));
//		}
//		return hmtList;
//	}
//
//	/**
//	 * 根据门店id, 日期集合查询数据, 时间段内的相同字段数据累加汇总
//	 * 
//	 * @param storeId
//	 * @param dateList
//	 *            格式：[yyyyMMdd, yyyyMMdd, ...]； 如果为空，默认当前系统时间
//	 * @return
//	 * @throws TException
//	 */
//	public List<HbaseMapType> findSumDataByStoreId(String storeId, List<String> dateList) throws TException {
//		Map<String, String> map = new HashMap<>();
//		String currentDay = sdf.format(new Date());
//		Map<String, String> attributes = new HashMap<String, String>();
//		List<String> rows = new ArrayList<String>();
//		rows.add(storeId);
//		List<String> columns = new ArrayList<String>();
//		for (int i = 0; i < Const.KEYS.length; i++) {
//			if (dateList != null && dateList.size() > 0) {
//				for (String date : dateList) {
//					columns.add(Const.COLUMN_FAMILY + ":" + date + "_" + Const.KEYS[i]);
//				}
//			} else {
//				columns.add(Const.COLUMN_FAMILY + ":" + currentDay + "_" + Const.KEYS[i]);
//			}
//		}
//		List<TRowResult> results = client.getRowsWithColumns(Const.TABLES[3], rows, columns, attributes);
//		for (TRowResult result : results) {
//			Iterator<Entry<ByteBuffer, TCell>> iter = result.columns.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<ByteBuffer, TCell> entry = iter.next();
//				map.put(new String(decode(entry.getKey())), new String(entry.getValue().getValue()));
//			}
//		}
//		// client.close();
//		List<HbaseMapType> hmtList = new ArrayList<>();
//		hmtList = findMapAll();
//		for (HbaseMapType hmt : hmtList) {
//			hmt.setValue(null);
//			if (dateList != null && dateList.size() > 0) {
//				for (String key : map.keySet()) {
//					String subkey = key.replaceAll(Const.COLUMN_FAMILY + ":\\d{8}_(\\w+)", "$1");
//					if (subkey.equals(hmt.getKeyName())) {
//						Double value = hmt.getValue() == null ? 0.0 : Double.parseDouble(hmt.getValue());
//						value += Double.parseDouble(map.get(key));// 时间段内相同字段的数值累加
//						DecimalFormat df = new DecimalFormat("#");
//						hmt.setValue(df.format(value));// 去除科学计数法
//					}
//				}
//			} else {
//				for (String key : map.keySet()) {
//					String subkey = key.replaceAll(Const.COLUMN_FAMILY + ":\\d{8}_(\\w+)", "$1");
//					if (subkey.equals(hmt.getKeyName())) {
//						hmt.setValue(map.get(key));
//					}
//				}
//			}
//		}
//		return hmtList;
//	}
//
//	/**
//	 * 根据门店id, 日期集合查询数据, 时间段内的数据汇总
//	 * 
//	 * @param storeId
//	 * @param dateList
//	 *            格式：[yyyyMMdd, yyyyMMdd, ...]； 如果为空，默认当前系统时间
//	 * @return
//	 * @throws TException
//	 */
//	public Map<String, List<HbaseMapType>> findByStoreId(String storeId, List<String> dateList) throws TException {
//		Map<String, String> map = new HashMap<>();
//		String currentDay = sdf.format(new Date());
//		Map<String, String> attributes = new HashMap<String, String>();
//		List<String> rows = new ArrayList<String>();
//		rows.add(storeId);
//		List<String> columns = new ArrayList<String>();
//		for (int i = 0; i < Const.KEYS.length; i++) {
//			if (dateList != null && dateList.size() > 0) {
//				for (String date : dateList) {
//					columns.add(Const.COLUMN_FAMILY + ":" + date + "_" + Const.KEYS[i]);
//				}
//			} else {
//				columns.add(Const.COLUMN_FAMILY + ":" + currentDay + "_" + Const.KEYS[i]);
//			}
//		}
//		List<TRowResult> results = client.getRowsWithColumns(Const.TABLES[3], rows, columns, attributes);
//		for (TRowResult result : results) {
//			Iterator<Entry<ByteBuffer, TCell>> iter = result.columns.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<ByteBuffer, TCell> entry = iter.next();
//				map.put(new String(decode(entry.getKey())), new String(entry.getValue().getValue()));
//			}
//		}
//
//		Map<String, List<HbaseMapType>> mlList = new HashMap<>();
//		if (dateList != null && dateList.size() > 0) {
//			for (String dateKey : dateList) {
//				List<HbaseMapType> hmtList = new ArrayList<>();
//				mlList.put(dateKey, hmtList);
//				for (String key : map.keySet()) {
//					if (key.contains(dateKey)) {
//						HbaseMapType hbaseMapType = new HbaseMapType();
//						String subkey = key.replaceAll(Const.COLUMN_FAMILY + ":\\d{8}_(\\w+)", "$1");
//						hbaseMapType.setKeyName(subkey);
//						hbaseMapType.setValue(map.get(key));
//						hmtList.add(hbaseMapType);
//					}
//				}
//			}
//		} else {
//			List<HbaseMapType> hmtList = new ArrayList<>();
//			mlList.put(currentDay, hmtList);
//			for (String key : map.keySet()) {
//				HbaseMapType hbaseMapType = new HbaseMapType();
//				String subkey = key.replaceAll(Const.COLUMN_FAMILY + ":\\d{8}_(\\w+)", "$1");
//				hbaseMapType.setKeyName(subkey);
//				hbaseMapType.setValue(map.get(key));
//				hmtList.add(hbaseMapType);
//			}
//		}
//
//		return mlList;
//	}
//
//	public List<HbaseMapType> findMapAll() {
//		String sql = "select * from hbase_map_type";
//		return mapDao.find(sql);
//	}
//
//	public List<BaStore> findAll() {
//		return dao.find("select * from ba_store");
//	}
//
//	public List<Map<String, Object>> getDeptListByStoreId(int currentStoreId,
//			List<Map<String, Object>> deptRecordList) {
//		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
//		for (Map<String, Object> dept : deptRecordList) {
//			if (dept.get("store_id").equals(currentStoreId)) {
//				recordList.add(dept);
//			}
//		}
//		return recordList;
//	}
//
//	public List<Map<String, Object>> findStoreRecordListByDeptRecordList(List<Map<String, Object>> deptRecordList) {
//		BigDecimal values[] = new BigDecimal[Const.KEYS.length];
//		List<BaStore> storeList = new ArrayList<>();
//		storeList = findAll();
//		List<Map<String, Object>> recordList = new ArrayList<>();
//		for (BaStore store : storeList) {
//			for (int i = 0; i < Const.KEYS.length; i++) {
//				values[i] = new BigDecimal("0");
//			}
//			List<Map<String, Object>> tempRecordList = new ArrayList<>();
//			tempRecordList = getDeptListByStoreId(store.getId(), deptRecordList);
//			for (Map<String, Object> record : tempRecordList) {
//				for (int i = 0; i < Const.KEYS.length; i++) {
//					Object temp = record.get(Const.KEYS[i]);
//					temp = temp == null ? 0 : temp;
//					BigDecimal value = new BigDecimal("0");
//					if (temp instanceof Double) {
//						value = new BigDecimal((double) temp);
//					}
//					if (temp instanceof Integer) {
//						value = new BigDecimal((int) temp);
//					}
//					if (temp instanceof Long) {
//						value = new BigDecimal((long) temp);
//					}
//					if (temp instanceof BigDecimal) {
//						value = (BigDecimal) temp;
//					}
//					values[i] = values[i].add(value);
//				}
//			}
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("id", store.getId());
//			map.put("short_name", store.getShortName());
//			for (int i = 0; i < Const.KEYS.length; i++) {
//				map.put(Const.KEYS[i], values[i]);
//			}
//			recordList.add(map);
//		}
//		return recordList;
//	}
//
//	/**
//	 * 根据门店id、日期集合以及类型查询数据, 时间段内的数据汇总
//	 * 
//	 * @param storeId
//	 * @param dateList
//	 *            格式：[yyyyMMdd, yyyyMMdd, ...]； 如果为空，默认当前系统时间
//	 * @return
//	 * @throws TException
//	 * @throws UnsupportedEncodingException
//	 */
//	public Map<String, List<HbaseMapType>> findByStoreIdAndTypes(String storeId, List<String> dateList, String[] types)
//			throws TException {
//
//		Map<String, String> map = new HashMap<>();
//		String currentDay = sdf.format(new Date());
//		Map<String, String> attributes = new HashMap<String, String>();
//		List<String> rows = new ArrayList<String>();
//		rows.add(storeId);
//		List<String> columns = new ArrayList<String>();
//		for (int i = 0; i < types.length; i++) {
//			if (dateList != null && dateList.size() > 0) {
//				for (String date : dateList) {
//					columns.add(Const.COLUMN_FAMILY + ":" + date + "_" + types[i]);
//				}
//			} else {
//				columns.add(Const.COLUMN_FAMILY + ":" + currentDay + "_" + types[i]);
//
//			}
//		}
//		List<TRowResult> results = client.getRowsWithColumns(Const.TABLES[3], rows, columns, attributes);
//		for (TRowResult result : results) {
//			Iterator<Entry<ByteBuffer, TCell>> iter = result.columns.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<ByteBuffer, TCell> entry = iter.next();
//				map.put(new String(decode(entry.getKey())), new String(entry.getValue().getValue()));
//			}
//		}
//
//		Map<String, List<HbaseMapType>> mlList = new HashMap<>();
//		if (dateList != null && dateList.size() > 0) {
//			for (String dateKey : dateList) {
//				List<HbaseMapType> hmtList = new ArrayList<>();
//				mlList.put(dateKey, hmtList);
//				for (String key : map.keySet()) {
//					if (key.contains(dateKey)) {
//						HbaseMapType hbaseMapType = new HbaseMapType();
//						String subkey = key.replaceAll(Const.COLUMN_FAMILY + ":\\d{8}_(\\w+)", "$1");
//						hbaseMapType.setKeyName(subkey);
//						hbaseMapType.setValue(map.get(key));
//						hmtList.add(hbaseMapType);
//					}
//				}
//			}
//		} else {
//			List<HbaseMapType> hmtList = new ArrayList<>();
//			mlList.put(currentDay, hmtList);
//			for (String key : map.keySet()) {
//				HbaseMapType hbaseMapType = new HbaseMapType();
//				String subkey = key.replaceAll(Const.COLUMN_FAMILY + ":\\d{8}_(\\w+)", "$1");
//				hbaseMapType.setKeyName(subkey);
//				hbaseMapType.setValue(map.get(key));
//				hmtList.add(hbaseMapType);
//			}
//		}
//
//		return mlList;
//
//	}
//}