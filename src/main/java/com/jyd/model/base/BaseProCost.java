package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseProCost<M extends BaseProCost<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setCostTypeId(java.lang.Integer costTypeId) {
		set("cost_type_id", costTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getCostTypeId() {
		return getInt("cost_type_id");
	}

	public M setProductId(java.lang.Integer productId) {
		set("product_id", productId);
		return (M)this;
	}
	
	public java.lang.Integer getProductId() {
		return getInt("product_id");
	}

	public M setWfWorkFlowTypeFlowId(java.lang.Integer wfWorkFlowTypeFlowId) {
		set("wf_work_flow_type_flow_id", wfWorkFlowTypeFlowId);
		return (M)this;
	}
	
	public java.lang.Integer getWfWorkFlowTypeFlowId() {
		return getInt("wf_work_flow_type_flow_id");
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

	public M setValue(java.lang.Double value) {
		set("value", value);
		return (M)this;
	}
	
	public java.lang.Double getValue() {
		return getDouble("value");
	}

}
