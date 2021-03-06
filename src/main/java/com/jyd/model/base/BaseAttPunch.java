package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAttPunch<M extends BaseAttPunch<M>> extends Model<M> implements IBean {

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

	public M setPunchTime(java.util.Date punchTime) {
		set("punch_time", punchTime);
		return (M)this;
	}
	
	public java.util.Date getPunchTime() {
		return get("punch_time");
	}

	public M setAttendDate(java.util.Date attendDate) {
		set("attend_date", attendDate);
		return (M)this;
	}
	
	public java.util.Date getAttendDate() {
		return get("attend_date");
	}

}
