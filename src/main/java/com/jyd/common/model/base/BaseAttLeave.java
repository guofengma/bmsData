package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAttLeave<M extends BaseAttLeave<M>> extends Model<M> implements IBean {

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

	public M setLeaveId(java.lang.Integer leaveId) {
		set("leave_id", leaveId);
		return (M)this;
	}
	
	public java.lang.Integer getLeaveId() {
		return getInt("leave_id");
	}

	public M setStartTime(java.util.Date startTime) {
		set("start_time", startTime);
		return (M)this;
	}
	
	public java.util.Date getStartTime() {
		return get("start_time");
	}

	public M setEndTime(java.util.Date endTime) {
		set("end_time", endTime);
		return (M)this;
	}
	
	public java.util.Date getEndTime() {
		return get("end_time");
	}

}
