package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseCusThirdpartQueryLog<M extends BaseCusThirdpartQueryLog<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setThirdpartQueryTypeId(java.lang.Integer thirdpartQueryTypeId) {
		set("thirdpart_query_type_id", thirdpartQueryTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getThirdpartQueryTypeId() {
		return getInt("thirdpart_query_type_id");
	}

	public M setStartDate(java.util.Date startDate) {
		set("start_date", startDate);
		return (M)this;
	}
	
	public java.util.Date getStartDate() {
		return get("start_date");
	}

	public M setEndDate(java.util.Date endDate) {
		set("end_date", endDate);
		return (M)this;
	}
	
	public java.util.Date getEndDate() {
		return get("end_date");
	}

	public M setSendContent(java.lang.String sendContent) {
		set("send_content", sendContent);
		return (M)this;
	}
	
	public java.lang.String getSendContent() {
		return getStr("send_content");
	}

	public M setReceiveContent(java.lang.String receiveContent) {
		set("receive_content", receiveContent);
		return (M)this;
	}
	
	public java.lang.String getReceiveContent() {
		return getStr("receive_content");
	}

	public M setState(java.lang.String state) {
		set("state", state);
		return (M)this;
	}
	
	public java.lang.String getState() {
		return getStr("state");
	}

}