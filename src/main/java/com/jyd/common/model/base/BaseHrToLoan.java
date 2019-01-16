package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseHrToLoan<M extends BaseHrToLoan<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setRawDepartment(java.lang.String rawDepartment) {
		set("raw_department", rawDepartment);
		return (M)this;
	}
	
	public java.lang.String getRawDepartment() {
		return getStr("raw_department");
	}

	public M setTempDepartment(java.lang.String tempDepartment) {
		set("temp_department", tempDepartment);
		return (M)this;
	}
	
	public java.lang.String getTempDepartment() {
		return getStr("temp_department");
	}

	public M setTransferCause(java.lang.String transferCause) {
		set("transfer_cause", transferCause);
		return (M)this;
	}
	
	public java.lang.String getTransferCause() {
		return getStr("transfer_cause");
	}

	public M setEffectiveDate(java.util.Date effectiveDate) {
		set("effective_date", effectiveDate);
		return (M)this;
	}
	
	public java.util.Date getEffectiveDate() {
		return get("effective_date");
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

	public M setEmployeeId(java.lang.Integer employeeId) {
		set("employee_id", employeeId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeeId() {
		return getInt("employee_id");
	}

	public M setDepartmentOld(java.lang.Integer departmentOld) {
		set("department_old", departmentOld);
		return (M)this;
	}
	
	public java.lang.Integer getDepartmentOld() {
		return getInt("department_old");
	}

	public M setDepartmentNew(java.lang.Integer departmentNew) {
		set("department_new", departmentNew);
		return (M)this;
	}
	
	public java.lang.Integer getDepartmentNew() {
		return getInt("department_new");
	}

}
