package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfAgent<M extends BaseWfAgent<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setProcessEmployeeId(java.lang.Integer processEmployeeId) {
		set("process_employee_id", processEmployeeId);
		return (M)this;
	}
	
	public java.lang.Integer getProcessEmployeeId() {
		return getInt("process_employee_id");
	}

	public M setAgentEmployeeId(java.lang.Integer agentEmployeeId) {
		set("agent_employee_id", agentEmployeeId);
		return (M)this;
	}
	
	public java.lang.Integer getAgentEmployeeId() {
		return getInt("agent_employee_id");
	}

	public M setBeginTime(java.util.Date beginTime) {
		set("begin_time", beginTime);
		return (M)this;
	}
	
	public java.util.Date getBeginTime() {
		return get("begin_time");
	}

	public M setEndTime(java.util.Date endTime) {
		set("end_time", endTime);
		return (M)this;
	}
	
	public java.util.Date getEndTime() {
		return get("end_time");
	}

	public M setWfWorkFlowTypeId(java.lang.Integer wfWorkFlowTypeId) {
		set("wf_work_flow_type_id", wfWorkFlowTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getWfWorkFlowTypeId() {
		return getInt("wf_work_flow_type_id");
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