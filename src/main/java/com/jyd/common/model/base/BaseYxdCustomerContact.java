package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseYxdCustomerContact<M extends BaseYxdCustomerContact<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setYxdCustomerInfoId(java.lang.Integer yxdCustomerInfoId) {
		set("yxd_customer_info_id", yxdCustomerInfoId);
		return (M)this;
	}
	
	public java.lang.Integer getYxdCustomerInfoId() {
		return getInt("yxd_customer_info_id");
	}

	public M setContactName(java.lang.String contactName) {
		set("contact_name", contactName);
		return (M)this;
	}
	
	public java.lang.String getContactName() {
		return getStr("contact_name");
	}

	public M setRelation(java.lang.String relation) {
		set("relation", relation);
		return (M)this;
	}
	
	public java.lang.String getRelation() {
		return getStr("relation");
	}

	public M setMobilephone(java.lang.String mobilephone) {
		set("mobilephone", mobilephone);
		return (M)this;
	}
	
	public java.lang.String getMobilephone() {
		return getStr("mobilephone");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setCreateUser(java.lang.String createUser) {
		set("create_user", createUser);
		return (M)this;
	}
	
	public java.lang.String getCreateUser() {
		return getStr("create_user");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

}
