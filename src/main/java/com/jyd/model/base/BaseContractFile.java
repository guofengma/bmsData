package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseContractFile<M extends BaseContractFile<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setContractId(java.lang.Integer contractId) {
		set("contract_id", contractId);
		return (M)this;
	}
	
	public java.lang.Integer getContractId() {
		return getInt("contract_id");
	}

	public M setType(java.lang.String type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}

	public M setFileType(java.lang.String fileType) {
		set("file_type", fileType);
		return (M)this;
	}
	
	public java.lang.String getFileType() {
		return getStr("file_type");
	}

	public M setFileName(java.lang.String fileName) {
		set("file_name", fileName);
		return (M)this;
	}
	
	public java.lang.String getFileName() {
		return getStr("file_name");
	}

	public M setRealName(java.lang.String realName) {
		set("real_name", realName);
		return (M)this;
	}
	
	public java.lang.String getRealName() {
		return getStr("real_name");
	}

	public M setRealPath(java.lang.String realPath) {
		set("real_path", realPath);
		return (M)this;
	}
	
	public java.lang.String getRealPath() {
		return getStr("real_path");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

}
