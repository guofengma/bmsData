package com.jyd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jyd.common.model.CusContract;

/**
 * 
 * @author mjy
 *
 */
public class ContractService {
	public static final ContractService me = new ContractService();
	private CusContract dao = new CusContract().dao();

	public CusContract getById(int id) {
		return dao.findById(id);
	}

	public Record findLoanAmount() {
		return Db.findFirst(
				"SELECT (case when sum(borrowing_amount) is null then 0 else sum(borrowing_amount) end) as loan_amount FROM cus_contract WHERE state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE() ,'%Y%m%d')");
	}

	public Record findDemandAmount() {
		return Db.findFirst(
				"SELECT (case when sum(borrowing_amount) is null then 0 else sum(borrowing_amount) end) as loan_amount FROM cus_contract WHERE state =-1 AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE() ,'%Y%m%d')");
	}

	public List<Record> findLoanAmounts() {
		return Db.find(
				"SELECT (case when sum(borrowing_amount) is null then 0 else sum(borrowing_amount) end) as loan_amount,store_id FROM cus_contract WHERE state NOT IN (5, -1) AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE() ,'%Y%m%d') group by store_id");
	}

	public List<Record> findDemandAmounts() {
		return Db.find(
				"SELECT SUM(borrowing_amount) as demand_amount,store_id FROM cus_contract WHERE state =-1 AND date_format(create_date,'%Y%m%d') =date_format(SYSDATE(),'%Y%m%d') group by store_id");
	}

	public List<Record> find_5w() {
		return Db.find(
				"SELECT COUNT(1) as principal,store_id FROM cus_contract WHERE state =-1 AND principal <= 50000 AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') group by store_id");
	}

	public List<Record> find_5_10w() {
		return Db.find(
				"SELECT COUNT(1) as principal,store_id FROM cus_contract WHERE state =-1 AND principal > 50000 AND principal<=100000 AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') group by store_id");
	}

	public List<Record> find_10_20w() {
		return Db.find(
				"SELECT COUNT(1) as principal,store_id FROM cus_contract WHERE state =-1 AND principal > 100000 AND principal<=2000000 AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') group by store_id");
	}

	public List<Record> find_20_30w() {
		return Db.find(
				"SELECT COUNT(1) as principal,store_id FROM cus_contract WHERE state =-1 AND principal > 200000 AND principal<=3000000 AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') group by store_id");
	}

	public List<Record> find_30w() {
		return Db.find(
				"SELECT COUNT(1) as principal,store_id FROM cus_contract WHERE state =-1 AND principal > 300000 AND date_format(create_date,'%Y%m%d') = date_format(SYSDATE(),'%Y%m%d') group by store_id");
	}

	public List<CusContract> findContractByEmpId(int empId) {
		return dao.find("select * from cus_contract where id=" + empId + " and state !=5");
	}

	public List<Record> findClassify() {
		return Db.find(
				"SELECT SUM(borrowing_amount) as demand_amount,store_id FROM cus_contract WHERE state =-1 AND date_format(create_date,'%Y%m%d') =date_format(SYSDATE(),'%Y%m%d') and ba_product_classify_id=1 group by store_id");
	}

	public double findLate_m_1() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days <=30";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_2() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >30 and overdue_current_days<=60";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_3() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >60 and overdue_current_days<=90";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_4() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >90 and overdue_current_days<=120";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_5() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >120 and overdue_current_days<=150";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_6() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >150 and overdue_current_days<=180";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_7() {
		String sql = "select sum(late_fee) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >180";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_1_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >30";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_2_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >30 and overdue_current_days<=60";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_3_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >60 and overdue_current_days<=90";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_4_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >90 and overdue_current_days<=120";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_5_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >120 and overdue_current_days<=150";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_6_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >150 and overdue_current_days<=180";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public double findLate_m_7_2() {
		String sql = "select count(1) as late_m_1 from cus_contract where late_state =1 and overdue_current_days >180";
		return Db.findFirst(sql).getDouble("late_m_1");
	}

	public List<Map<String, Object>> findCRList() {
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

}
