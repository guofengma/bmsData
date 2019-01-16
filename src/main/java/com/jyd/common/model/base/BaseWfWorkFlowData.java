package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfWorkFlowData<M extends BaseWfWorkFlowData<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setDataKey(java.lang.String dataKey) {
		set("data_key", dataKey);
		return (M)this;
	}
	
	public java.lang.String getDataKey() {
		return getStr("data_key");
	}

	public M setDataValue(java.lang.String dataValue) {
		set("data_value", dataValue);
		return (M)this;
	}
	
	public java.lang.String getDataValue() {
		return getStr("data_value");
	}

}
