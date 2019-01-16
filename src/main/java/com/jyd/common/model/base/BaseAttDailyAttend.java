package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseAttDailyAttend<M extends BaseAttDailyAttend<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setEmployeeDateId(java.lang.Integer employeeDateId) {
		set("employee_date_id", employeeDateId);
		return (M)this;
	}
	
	public java.lang.Integer getEmployeeDateId() {
		return getInt("employee_date_id");
	}

	public M setAttendType(java.lang.Integer attendType) {
		set("attend_type", attendType);
		return (M)this;
	}
	
	public java.lang.Integer getAttendType() {
		return getInt("attend_type");
	}

	public M setType(java.lang.Integer type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public M setValue(java.math.BigDecimal value) {
		set("value", value);
		return (M)this;
	}
	
	public java.math.BigDecimal getValue() {
		return get("value");
	}

}