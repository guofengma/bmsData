package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSalSalaryItem<M extends BaseSalSalaryItem<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setCode(java.lang.String code) {
		set("code", code);
		return (M)this;
	}
	
	public java.lang.String getCode() {
		return getStr("code");
	}

	public M setSalaryItem(java.lang.String salaryItem) {
		set("salary_item", salaryItem);
		return (M)this;
	}
	
	public java.lang.String getSalaryItem() {
		return getStr("salary_item");
	}

	public M setSalaryItemTypeId(java.lang.Integer salaryItemTypeId) {
		set("salary_item_type_id", salaryItemTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getSalaryItemTypeId() {
		return getInt("salary_item_type_id");
	}

	public M setFormula(java.lang.String formula) {
		set("formula", formula);
		return (M)this;
	}
	
	public java.lang.String getFormula() {
		return getStr("formula");
	}

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public M setOperation(java.lang.Integer operation) {
		set("operation", operation);
		return (M)this;
	}
	
	public java.lang.Integer getOperation() {
		return getInt("operation");
	}

	public M setSpecialValue(java.lang.Integer specialValue) {
		set("special_value", specialValue);
		return (M)this;
	}
	
	public java.lang.Integer getSpecialValue() {
		return getInt("special_value");
	}

	public M setState(java.lang.Boolean state) {
		set("state", state);
		return (M)this;
	}
	
	public java.lang.Boolean getState() {
		return get("state");
	}

	public M setSortIndex(java.lang.Integer sortIndex) {
		set("sort_index", sortIndex);
		return (M)this;
	}
	
	public java.lang.Integer getSortIndex() {
		return getInt("sort_index");
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

}