package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormLeaveEmployeeV1<M extends BaseWfFormLeaveEmployeeV1<M>> extends Model<M> implements IBean {

	public M setDataKey(java.lang.String dataKey) {
		set("data_key", dataKey);
		return (M)this;
	}
	
	public java.lang.String getDataKey() {
		return getStr("data_key");
	}

	public M setEmployeeId(java.lang.Integer employeeId) {
		set("employee_id", employeeId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeeId() {
		return getInt("employee_id");
	}

	public M setLeaveDate(java.util.Date leaveDate) {
		set("leave_date", leaveDate);
		return (M)this;
	}
	
	public java.util.Date getLeaveDate() {
		return get("leave_date");
	}

	public M setState(java.lang.Integer state) {
		set("state", state);
		return (M)this;
	}
	
	public java.lang.Integer getState() {
		return getInt("state");
	}

	public M setReason(java.lang.String reason) {
		set("reason", reason);
		return (M)this;
	}
	
	public java.lang.String getReason() {
		return getStr("reason");
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

	public M setEmployeePropertiesId(java.lang.Integer employeePropertiesId) {
		set("employee_properties_id", employeePropertiesId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeePropertiesId() {
		return getInt("employee_properties_id");
	}

}