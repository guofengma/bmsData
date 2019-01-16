package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormToLoanV1<M extends BaseWfFormToLoanV1<M>> extends Model<M> implements IBean {

	public M setDateKey(java.lang.String dateKey) {
		set("date_key", dateKey);
		return (M)this;
	}
	
	public java.lang.String getDateKey() {
		return getStr("date_key");
	}

	public M setEmployeeId(java.lang.Integer employeeId) {
		set("employee_id", employeeId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeeId() {
		return getInt("employee_id");
	}

	public M setOldDepartmentId(java.lang.Integer oldDepartmentId) {
		set("old_department_id", oldDepartmentId);
		return (M)this;
	}
	
	public java.lang.Integer getOldDepartmentId() {
		return getInt("old_department_id");
	}

	public M setNewDepartmentId(java.lang.Integer newDepartmentId) {
		set("new_department_id", newDepartmentId);
		return (M)this;
	}
	
	public java.lang.Integer getNewDepartmentId() {
		return getInt("new_department_id");
	}

	public M setReason(java.lang.String reason) {
		set("reason", reason);
		return (M)this;
	}
	
	public java.lang.String getReason() {
		return getStr("reason");
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

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

}
