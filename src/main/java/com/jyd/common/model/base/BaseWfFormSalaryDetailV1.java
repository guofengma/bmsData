package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormSalaryDetailV1<M extends BaseWfFormSalaryDetailV1<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setEmployeeId(java.lang.Integer employeeId) {
		set("employee_id", employeeId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeeId() {
		return getInt("employee_id");
	}

	public M setBeforeTotalSalary(java.math.BigDecimal beforeTotalSalary) {
		set("before_total_salary", beforeTotalSalary);
		return (M)this;
	}
	
	public java.math.BigDecimal getBeforeTotalSalary() {
		return get("before_total_salary");
	}

	public M setAfterTotalSalary(java.math.BigDecimal afterTotalSalary) {
		set("after_total_salary", afterTotalSalary);
		return (M)this;
	}
	
	public java.math.BigDecimal getAfterTotalSalary() {
		return get("after_total_salary");
	}

	public M setBeforeSalaryData(java.lang.String beforeSalaryData) {
		set("before_salary_data", beforeSalaryData);
		return (M)this;
	}
	
	public java.lang.String getBeforeSalaryData() {
		return getStr("before_salary_data");
	}

	public M setAfterSalaryData(java.lang.String afterSalaryData) {
		set("after_salary_data", afterSalaryData);
		return (M)this;
	}
	
	public java.lang.String getAfterSalaryData() {
		return getStr("after_salary_data");
	}

	public M setCreateUser(java.lang.String createUser) {
		set("create_user", createUser);
		return (M)this;
	}
	
	public java.lang.String getCreateUser() {
		return getStr("create_user");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public M setDataKey(java.lang.String dataKey) {
		set("data_key", dataKey);
		return (M)this;
	}
	
	public java.lang.String getDataKey() {
		return getStr("data_key");
	}

}