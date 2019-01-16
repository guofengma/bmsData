package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBaScheduleType<M extends BaseBaScheduleType<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setScheduleType(java.lang.String scheduleType) {
		set("schedule_type", scheduleType);
		return (M)this;
	}
	
	public java.lang.String getScheduleType() {
		return getStr("schedule_type");
	}

}
