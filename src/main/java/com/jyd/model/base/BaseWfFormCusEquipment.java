package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormCusEquipment<M extends BaseWfFormCusEquipment<M>> extends Model<M> implements IBean {

	public M setDataKey(java.lang.String dataKey) {
		set("data_key", dataKey);
		return (M)this;
	}
	
	public java.lang.String getDataKey() {
		return getStr("data_key");
	}

	public M setEquipmentName(java.lang.String equipmentName) {
		set("equipment_name", equipmentName);
		return (M)this;
	}
	
	public java.lang.String getEquipmentName() {
		return getStr("equipment_name");
	}

	public M setEquipmentInstallLocation(java.lang.String equipmentInstallLocation) {
		set("equipment_install_location", equipmentInstallLocation);
		return (M)this;
	}
	
	public java.lang.String getEquipmentInstallLocation() {
		return getStr("equipment_install_location");
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

	public M setEquipmentType(java.lang.Integer equipmentType) {
		set("equipment_type", equipmentType);
		return (M)this;
	}
	
	public java.lang.Integer getEquipmentType() {
		return getInt("equipment_type");
	}

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

}
