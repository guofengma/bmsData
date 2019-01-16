package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAttWorkTime<M extends BaseAttWorkTime<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setShiftId(java.lang.Integer shiftId) {
		set("shift_id", shiftId);
		return (M)this;
	}
	
	public java.lang.Integer getShiftId() {
		return getInt("shift_id");
	}

	public M setStartTime(java.lang.String startTime) {
		set("start_time", startTime);
		return (M)this;
	}
	
	public java.lang.String getStartTime() {
		return getStr("start_time");
	}

	public M setEndTime(java.lang.String endTime) {
		set("end_time", endTime);
		return (M)this;
	}
	
	public java.lang.String getEndTime() {
		return getStr("end_time");
	}

	public M setHourCount(java.lang.Integer hourCount) {
		set("hour_count", hourCount);
		return (M)this;
	}
	
	public java.lang.Integer getHourCount() {
		return getInt("hour_count");
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