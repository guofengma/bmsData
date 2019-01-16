package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSalEmployeeSalary<M extends BaseSalEmployeeSalary<M>> extends Model<M> implements IBean {

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

	public M setSalaryStructureId(java.lang.Integer salaryStructureId) {
		set("salary_structure_id", salaryStructureId);
		return (M)this;
	}
	
	public java.lang.Integer getSalaryStructureId() {
		return getInt("salary_structure_id");
	}

	public M setEffectiveDate(java.util.Date effectiveDate) {
		set("effective_date", effectiveDate);
		return (M)this;
	}
	
	public java.util.Date getEffectiveDate() {
		return get("effective_date");
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

	public M setSalaryLevelId(java.lang.Integer salaryLevelId) {
		set("salary_level_id", salaryLevelId);
		return (M)this;
	}
	
	public java.lang.Integer getSalaryLevelId() {
		return getInt("salary_level_id");
	}

}
