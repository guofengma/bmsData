package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseHrEmpExperience<M extends BaseHrEmpExperience<M>> extends Model<M> implements IBean {

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

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public M setOldData(java.lang.String oldData) {
		set("old_data", oldData);
		return (M)this;
	}
	
	public java.lang.String getOldData() {
		return getStr("old_data");
	}

	public M setNewData(java.lang.String newData) {
		set("new_data", newData);
		return (M)this;
	}
	
	public java.lang.String getNewData() {
		return getStr("new_data");
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

	public M setEffectiveDate(java.util.Date effectiveDate) {
		set("effective_date", effectiveDate);
		return (M)this;
	}
	
	public java.util.Date getEffectiveDate() {
		return get("effective_date");
	}

	public M setOldId(java.lang.Integer oldId) {
		set("old_id", oldId);
		return (M)this;
	}
	
	public java.lang.Integer getOldId() {
		return getInt("old_id");
	}

	public M setNewId(java.lang.Integer newId) {
		set("new_id", newId);
		return (M)this;
	}
	
	public java.lang.Integer getNewId() {
		return getInt("new_id");
	}

}