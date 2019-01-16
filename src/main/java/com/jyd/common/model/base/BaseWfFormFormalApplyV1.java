package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormFormalApplyV1<M extends BaseWfFormFormalApplyV1<M>> extends Model<M> implements IBean {

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

	public M setDepartmentId(java.lang.Integer departmentId) {
		set("department_id", departmentId);
		return (M)this;
	}
	
	public java.lang.Integer getDepartmentId() {
		return getInt("department_id");
	}

	public M setPositionId(java.lang.Integer positionId) {
		set("position_id", positionId);
		return (M)this;
	}
	
	public java.lang.Integer getPositionId() {
		return getInt("position_id");
	}

	public M setFormalType(java.lang.Integer formalType) {
		set("formal_type", formalType);
		return (M)this;
	}
	
	public java.lang.Integer getFormalType() {
		return getInt("formal_type");
	}

	public M setEntryDate(java.util.Date entryDate) {
		set("entry_date", entryDate);
		return (M)this;
	}
	
	public java.util.Date getEntryDate() {
		return get("entry_date");
	}

	public M setFormalDate(java.util.Date formalDate) {
		set("formal_date", formalDate);
		return (M)this;
	}
	
	public java.util.Date getFormalDate() {
		return get("formal_date");
	}

	public M setApplyReason(java.lang.String applyReason) {
		set("apply_reason", applyReason);
		return (M)this;
	}
	
	public java.lang.String getApplyReason() {
		return getStr("apply_reason");
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

	public M setBeforeEmployeePropertiesId(java.lang.Integer beforeEmployeePropertiesId) {
		set("before_employee_properties_id", beforeEmployeePropertiesId);
		return (M)this;
	}
	
	public java.lang.Integer getBeforeEmployeePropertiesId() {
		return getInt("before_employee_properties_id");
	}

	public M setAfterEmployeePropertiesId(java.lang.Integer afterEmployeePropertiesId) {
		set("after_employee_properties_id", afterEmployeePropertiesId);
		return (M)this;
	}
	
	public java.lang.Integer getAfterEmployeePropertiesId() {
		return getInt("after_employee_properties_id");
	}

}
