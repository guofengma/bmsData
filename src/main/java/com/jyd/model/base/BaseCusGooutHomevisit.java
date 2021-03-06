package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseCusGooutHomevisit<M extends BaseCusGooutHomevisit<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setGooutTheReason(java.lang.String gooutTheReason) {
		set("goout_the_reason", gooutTheReason);
		return (M)this;
	}
	
	public java.lang.String getGooutTheReason() {
		return getStr("goout_the_reason");
	}

	public M setGooutStartdate(java.util.Date gooutStartdate) {
		set("goout_startdate", gooutStartdate);
		return (M)this;
	}
	
	public java.util.Date getGooutStartdate() {
		return get("goout_startdate");
	}

	public M setGooutEnddate(java.util.Date gooutEnddate) {
		set("goout_enddate", gooutEnddate);
		return (M)this;
	}
	
	public java.util.Date getGooutEnddate() {
		return get("goout_enddate");
	}

	public M setPresentAddress(java.lang.String presentAddress) {
		set("present_address", presentAddress);
		return (M)this;
	}
	
	public java.lang.String getPresentAddress() {
		return getStr("present_address");
	}

	public M setPropertyAddress(java.lang.String propertyAddress) {
		set("property_address", propertyAddress);
		return (M)this;
	}
	
	public java.lang.String getPropertyAddress() {
		return getStr("property_address");
	}

	public M setManagetPhony(java.lang.String managetPhony) {
		set("managet_phony", managetPhony);
		return (M)this;
	}
	
	public java.lang.String getManagetPhony() {
		return getStr("managet_phony");
	}

	public M setCompanyName(java.lang.String companyName) {
		set("company_name", companyName);
		return (M)this;
	}
	
	public java.lang.String getCompanyName() {
		return getStr("company_name");
	}

	public M setCompanyAddress(java.lang.String companyAddress) {
		set("company_address", companyAddress);
		return (M)this;
	}
	
	public java.lang.String getCompanyAddress() {
		return getStr("company_address");
	}

	public M setCompanyManagerphony(java.lang.String companyManagerphony) {
		set("company_managerphony", companyManagerphony);
		return (M)this;
	}
	
	public java.lang.String getCompanyManagerphony() {
		return getStr("company_managerphony");
	}

	public M setCompanyStatus(java.lang.String companyStatus) {
		set("company_status", companyStatus);
		return (M)this;
	}
	
	public java.lang.String getCompanyStatus() {
		return getStr("company_status");
	}

	public M setComprehensiveEvaluation(java.lang.String comprehensiveEvaluation) {
		set("comprehensive_evaluation", comprehensiveEvaluation);
		return (M)this;
	}
	
	public java.lang.String getComprehensiveEvaluation() {
		return getStr("comprehensive_evaluation");
	}

	public M setAssessmentLevel(java.lang.String assessmentLevel) {
		set("assessment_level", assessmentLevel);
		return (M)this;
	}
	
	public java.lang.String getAssessmentLevel() {
		return getStr("assessment_level");
	}

	public M setCreateUser(java.lang.String createUser) {
		set("create_user", createUser);
		return (M)this;
	}
	
	public java.lang.String getCreateUser() {
		return getStr("create_user");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setUpdateUser(java.lang.String updateUser) {
		set("update_user", updateUser);
		return (M)this;
	}
	
	public java.lang.String getUpdateUser() {
		return getStr("update_user");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public M setContractId(java.lang.Integer contractId) {
		set("contract_id", contractId);
		return (M)this;
	}
	
	public java.lang.Integer getContractId() {
		return getInt("contract_id");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

}
