package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseHrParentDepartment<M extends BaseHrParentDepartment<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setDepartmentId(java.lang.Integer departmentId) {
		set("department_id", departmentId);
		return (M)this;
	}
	
	public java.lang.Integer getDepartmentId() {
		return getInt("department_id");
	}

	public M setParentDepartmentId(java.lang.Integer parentDepartmentId) {
		set("parent_department_id", parentDepartmentId);
		return (M)this;
	}
	
	public java.lang.Integer getParentDepartmentId() {
		return getInt("parent_department_id");
	}

}
