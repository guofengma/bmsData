package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTest<M extends BaseTest<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setTest(java.lang.String test) {
		set("test", test);
		return (M)this;
	}
	
	public java.lang.String getTest() {
		return getStr("test");
	}

	public M setDate(java.lang.String date) {
		set("date", date);
		return (M)this;
	}
	
	public java.lang.String getDate() {
		return getStr("date");
	}

}
