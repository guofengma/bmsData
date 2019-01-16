package com.jyd.service.front;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jyd.common.model.BaStore;
import com.jyd.common.model.CusContract;

/**
 * 
 * @author hui
 *
 */
public class ContractSercice {
	public static final ContractSercice me = new ContractSercice();
	private final CusContract dao = new CusContract().dao();
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public List<Map<String, Object>> findContractRecordListDay() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT ");
		hql.append("a.id,");
		hql.append("a.must_capital,");
		hql.append("a.alr_capital,");
		hql.append("(a.must_capital - a.alr_capital) AS surplus_capital ,");
		hql.append("a.must_interest,");
		hql.append("a.alr_interest,");
		hql.append("(a.must_interest - a.alr_interest) AS surplus_interest,");
		hql.append("a.principal,");
		hql.append("a.person_responsible_id,");
		hql.append("a.store_id,");
		hql.append("a.cus_contract_no");
		hql.append(" FROM  (");
		hql.append("select ");
		hql.append("c.id,");
		hql.append(
				"(SELECT SUM(CONVERT( capital , DECIMAL (10 , 2 ))) FROM cus_contract_stage WHERE cus_contract_id = c.id) AS must_capital,");
		hql.append(
				"    (SELECT SUM(CONVERT( (SELECT SUM(r.capital) FROM cus_contract_repayment r WHERE r.cus_contract_stage_id = s.id) , DECIMAL (10 , 2 ))) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id) AS alr_capital,");
		hql.append(
				"(SELECT  SUM(CONVERT( (SELECT  SUM(r.interest) FROM  cus_contract_repayment r WHERE r.cus_contract_stage_id = s.id) , DECIMAL (10 , 2 ))) FROM cus_contract_stage s WHERE s.cus_contract_id = c.id) AS alr_interest,");
		hql.append(
				"(SELECT  SUM(CONVERT( interest , DECIMAL (10 , 2 )))FROM cus_contract_stage WHERE cus_contract_id = c.id) AS must_interest,");
		hql.append("c.principal,");
		hql.append("c.person_responsible_id,");
		hql.append("c.store_id,");
		hql.append("c.cus_contract_no");
		hql.append(" from cus_contract c WHERE c.state not in (- 1 , 5)) as a");
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (Record re : Db.find(hql.toString())) {
			mapList.add(re.getColumns());
		}
		return mapList;
	}

	public List<Map<String, Object>> findContractRecordListByWeek(String day) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		String sql = "";
		for (Record re : Db.find(sql)) {
			mapList.add(re.getColumns());
		}
		return mapList;
	}

	
	public Page<CusContract> paginateFindAll(int pageNumber, int pageSize){
		return dao.paginate(pageNumber, pageSize, "select *", " from cus_contract");
	}
	
	
	
	/**
	 * 未结清合同数量
	 * @param pageNumber
	 * @param pageSize
	 * @param storeId
	 * @return
	 */
	public Page<Record> unclearedNum(int pageNumber, int pageSize, String storeId) {
		String select = "select a.* ";
		String sqlExceptSelect = " from cus_contract a, ba_store b where a.store_id = b.id and state NOT IN (-1, 1, 4, 5) and store_id = ? ";
		Page<Record> paginate = Db.paginate(pageNumber, pageSize, select, sqlExceptSelect, storeId);
		List<Record> list = paginate.getList();
		for (Record record : list) {
			String contractNo = record.getStr("cus_contract_no");
			//计算总利息
			System.out.println(contractNo);
			//剩余期数
			
			//剩余本金
			
			//剩余利息
		}
		return paginate;
	}

	
	
	

}
