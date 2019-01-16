package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseProRepaymentStage<M extends BaseProRepaymentStage<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setRepaymentStageId(java.lang.Integer repaymentStageId) {
		set("repayment_stage_id", repaymentStageId);
		return (M)this;
	}
	
	public java.lang.Integer getRepaymentStageId() {
		return getInt("repayment_stage_id");
	}

	public M setProductId(java.lang.Integer productId) {
		set("product_id", productId);
		return (M)this;
	}
	
	public java.lang.Integer getProductId() {
		return getInt("product_id");
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

	public M setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

	public M setServiceFeeStage(java.lang.Integer serviceFeeStage) {
		set("service_fee_stage", serviceFeeStage);
		return (M)this;
	}
	
	public java.lang.Integer getServiceFeeStage() {
		return getInt("service_fee_stage");
	}

	public M setReturnPointNum(java.lang.Double returnPointNum) {
		set("return_point_num", returnPointNum);
		return (M)this;
	}
	
	public java.lang.Double getReturnPointNum() {
		return getDouble("return_point_num");
	}

}