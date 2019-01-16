package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSalMonthSalary<M extends BaseSalMonthSalary<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setYearmonth(java.lang.String yearmonth) {
		set("yearmonth", yearmonth);
		return (M)this;
	}
	
	public java.lang.String getYearmonth() {
		return getStr("yearmonth");
	}

	public M setEmployeeId(java.lang.Integer employeeId) {
		set("employee_id", employeeId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeeId() {
		return getInt("employee_id");
	}

	public M setTotal(java.lang.String total) {
		set("total", total);
		return (M)this;
	}
	
	public java.lang.String getTotal() {
		return getStr("total");
	}

	public M setFormula(java.lang.String formula) {
		set("formula", formula);
		return (M)this;
	}
	
	public java.lang.String getFormula() {
		return getStr("formula");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
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

	public M setIsLock(java.lang.Integer isLock) {
		set("is_lock", isLock);
		return (M)this;
	}
	
	public java.lang.Integer getIsLock() {
		return getInt("is_lock");
	}

	public M setBankCode(java.lang.String bankCode) {
		set("bank_code", bankCode);
		return (M)this;
	}
	
	public java.lang.String getBankCode() {
		return getStr("bank_code");
	}

	public M setPayDate(java.util.Date payDate) {
		set("pay_date", payDate);
		return (M)this;
	}
	
	public java.util.Date getPayDate() {
		return get("pay_date");
	}

	public M setDepartmentId(java.lang.Integer departmentId) {
		set("department_id", departmentId);
		return (M)this;
	}
	
	public java.lang.Integer getDepartmentId() {
		return getInt("department_id");
	}

	public M setStoreId(java.lang.Integer storeId) {
		set("store_id", storeId);
		return (M)this;
	}
	
	public java.lang.Integer getStoreId() {
		return getInt("store_id");
	}

}