package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseFundProduct<M extends BaseFundProduct<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setRepaymentTypeId(java.lang.Integer repaymentTypeId) {
		set("repayment_type_id", repaymentTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getRepaymentTypeId() {
		return getInt("repayment_type_id");
	}

	public M setContractVersionId(java.lang.Integer contractVersionId) {
		set("contract_version_id", contractVersionId);
		return (M)this;
	}
	
	public java.lang.Integer getContractVersionId() {
		return getInt("contract_version_id");
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

	public M setInterestDate(java.util.Date interestDate) {
		set("interest_date", interestDate);
		return (M)this;
	}
	
	public java.util.Date getInterestDate() {
		return get("interest_date");
	}

	public M setCapitalDate(java.util.Date capitalDate) {
		set("capital_date", capitalDate);
		return (M)this;
	}
	
	public java.util.Date getCapitalDate() {
		return get("capital_date");
	}

	public M setProductTypeId(java.lang.Integer productTypeId) {
		set("product_type_id", productTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getProductTypeId() {
		return getInt("product_type_id");
	}

}
