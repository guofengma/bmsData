package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseCusContractLateFee<M extends BaseCusContractLateFee<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setCusContractId(java.lang.Integer cusContractId) {
		set("cus_contract_id", cusContractId);
		return (M)this;
	}
	
	public java.lang.Integer getCusContractId() {
		return getInt("cus_contract_id");
	}

	public M setCusContractStageId(java.lang.Integer cusContractStageId) {
		set("cus_contract_stage_id", cusContractStageId);
		return (M)this;
	}
	
	public java.lang.Integer getCusContractStageId() {
		return getInt("cus_contract_stage_id");
	}

	public M setOverdueTime(java.util.Date overdueTime) {
		set("overdue_time", overdueTime);
		return (M)this;
	}
	
	public java.util.Date getOverdueTime() {
		return get("overdue_time");
	}

	public M setUnitTimeLateFee(java.lang.Double unitTimeLateFee) {
		set("unit_time_late_fee", unitTimeLateFee);
		return (M)this;
	}
	
	public java.lang.Double getUnitTimeLateFee() {
		return getDouble("unit_time_late_fee");
	}

	public M setTotalLateFee(java.lang.Double totalLateFee) {
		set("total_late_fee", totalLateFee);
		return (M)this;
	}
	
	public java.lang.Double getTotalLateFee() {
		return getDouble("total_late_fee");
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

	public M setState(java.lang.Integer state) {
		set("state", state);
		return (M)this;
	}
	
	public java.lang.Integer getState() {
		return getInt("state");
	}

}
