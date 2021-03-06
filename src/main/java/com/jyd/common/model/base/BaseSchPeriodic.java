package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSchPeriodic<M extends BaseSchPeriodic<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setPeriodic(java.lang.String periodic) {
		set("periodic", periodic);
		return (M)this;
	}
	
	public java.lang.String getPeriodic() {
		return getStr("periodic");
	}

	public M setTimeRules(java.util.Date timeRules) {
		set("time_rules", timeRules);
		return (M)this;
	}
	
	public java.util.Date getTimeRules() {
		return get("time_rules");
	}

}
