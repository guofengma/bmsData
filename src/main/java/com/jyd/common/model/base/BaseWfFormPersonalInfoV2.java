package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormPersonalInfoV2<M extends BaseWfFormPersonalInfoV2<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setWorkType(java.lang.Integer workType) {
		set("work_type", workType);
		return (M)this;
	}
	
	public java.lang.Integer getWorkType() {
		return getInt("work_type");
	}

	public M setExploanAmount(java.math.BigDecimal exploanAmount) {
		set("exploan_amount", exploanAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getExploanAmount() {
		return get("exploan_amount");
	}

	public M setCompanyAddress(java.lang.String companyAddress) {
		set("company_address", companyAddress);
		return (M)this;
	}
	
	public java.lang.String getCompanyAddress() {
		return getStr("company_address");
	}

	public M setSex(java.lang.Integer sex) {
		set("sex", sex);
		return (M)this;
	}
	
	public java.lang.Integer getSex() {
		return getInt("sex");
	}

	public M setBirthday(java.util.Date birthday) {
		set("birthday", birthday);
		return (M)this;
	}
	
	public java.util.Date getBirthday() {
		return get("birthday");
	}

	public M setMaritalStatus(java.lang.Integer maritalStatus) {
		set("marital_status", maritalStatus);
		return (M)this;
	}
	
	public java.lang.Integer getMaritalStatus() {
		return getInt("marital_status");
	}

	public M setMobilePhone(java.lang.String mobilePhone) {
		set("mobile_phone", mobilePhone);
		return (M)this;
	}
	
	public java.lang.String getMobilePhone() {
		return getStr("mobile_phone");
	}

	public M setIdNum(java.lang.String idNum) {
		set("id_num", idNum);
		return (M)this;
	}
	
	public java.lang.String getIdNum() {
		return getStr("id_num");
	}

	public M setHouseholdRegistrationAdress(java.lang.String householdRegistrationAdress) {
		set("household_registration_adress", householdRegistrationAdress);
		return (M)this;
	}
	
	public java.lang.String getHouseholdRegistrationAdress() {
		return getStr("household_registration_adress");
	}

	public M setEducationalBackground(java.lang.Integer educationalBackground) {
		set("educational_background", educationalBackground);
		return (M)this;
	}
	
	public java.lang.Integer getEducationalBackground() {
		return getInt("educational_background");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setQq(java.lang.String qq) {
		set("qq", qq);
		return (M)this;
	}
	
	public java.lang.String getQq() {
		return getStr("qq");
	}

	public M setWechat(java.lang.String wechat) {
		set("wechat", wechat);
		return (M)this;
	}
	
	public java.lang.String getWechat() {
		return getStr("wechat");
	}

	public M setPresentAddress(java.lang.String presentAddress) {
		set("present_address", presentAddress);
		return (M)this;
	}
	
	public java.lang.String getPresentAddress() {
		return getStr("present_address");
	}

	public M setPresentAddressPhone(java.lang.String presentAddressPhone) {
		set("present_address_phone", presentAddressPhone);
		return (M)this;
	}
	
	public java.lang.String getPresentAddressPhone() {
		return getStr("present_address_phone");
	}

	public M setInitialResidenceTimeYear(java.lang.Integer initialResidenceTimeYear) {
		set("initial_residence_time_year", initialResidenceTimeYear);
		return (M)this;
	}
	
	public java.lang.Integer getInitialResidenceTimeYear() {
		return getInt("initial_residence_time_year");
	}

	public M setInitialResidenceTimeMonth(java.lang.Integer initialResidenceTimeMonth) {
		set("initial_residence_time_month", initialResidenceTimeMonth);
		return (M)this;
	}
	
	public java.lang.Integer getInitialResidenceTimeMonth() {
		return getInt("initial_residence_time_month");
	}

	public M setMovedIntoYear(java.lang.Integer movedIntoYear) {
		set("moved_into_year", movedIntoYear);
		return (M)this;
	}
	
	public java.lang.Integer getMovedIntoYear() {
		return getInt("moved_into_year");
	}

	public M setNumOfDepentdent(java.lang.Integer numOfDepentdent) {
		set("num_of_depentdent", numOfDepentdent);
		return (M)this;
	}
	
	public java.lang.Integer getNumOfDepentdent() {
		return getInt("num_of_depentdent");
	}

	public M setEstateCategory(java.lang.String estateCategory) {
		set("estate_category", estateCategory);
		return (M)this;
	}
	
	public java.lang.String getEstateCategory() {
		return getStr("estate_category");
	}

	public M setSpouseName(java.lang.String spouseName) {
		set("spouse_name", spouseName);
		return (M)this;
	}
	
	public java.lang.String getSpouseName() {
		return getStr("spouse_name");
	}

	public M setSpousePhone(java.lang.String spousePhone) {
		set("spouse_phone", spousePhone);
		return (M)this;
	}
	
	public java.lang.String getSpousePhone() {
		return getStr("spouse_phone");
	}

	public M setSpouseIdNum(java.lang.String spouseIdNum) {
		set("spouse_id_num", spouseIdNum);
		return (M)this;
	}
	
	public java.lang.String getSpouseIdNum() {
		return getStr("spouse_id_num");
	}

	public M setRent(java.math.BigDecimal rent) {
		set("rent", rent);
		return (M)this;
	}
	
	public java.math.BigDecimal getRent() {
		return get("rent");
	}

	public M setWorkUnit(java.lang.String workUnit) {
		set("work_unit", workUnit);
		return (M)this;
	}
	
	public java.lang.String getWorkUnit() {
		return getStr("work_unit");
	}

	public M setUnitNature(java.lang.String unitNature) {
		set("unit_nature", unitNature);
		return (M)this;
	}
	
	public java.lang.String getUnitNature() {
		return getStr("unit_nature");
	}

	public M setIndustry(java.lang.String industry) {
		set("industry", industry);
		return (M)this;
	}
	
	public java.lang.String getIndustry() {
		return getStr("industry");
	}

	public M setPosition(java.lang.String position) {
		set("position", position);
		return (M)this;
	}
	
	public java.lang.String getPosition() {
		return getStr("position");
	}

	public M setInitialServiceYear(java.lang.Integer initialServiceYear) {
		set("initial_service_year", initialServiceYear);
		return (M)this;
	}
	
	public java.lang.Integer getInitialServiceYear() {
		return getInt("initial_service_year");
	}

	public M setUnitAddress(java.lang.String unitAddress) {
		set("unit_address", unitAddress);
		return (M)this;
	}
	
	public java.lang.String getUnitAddress() {
		return getStr("unit_address");
	}

	public M setUnitPhone(java.lang.String unitPhone) {
		set("unit_phone", unitPhone);
		return (M)this;
	}
	
	public java.lang.String getUnitPhone() {
		return getStr("unit_phone");
	}

	public M setSalary(java.math.BigDecimal salary) {
		set("salary", salary);
		return (M)this;
	}
	
	public java.math.BigDecimal getSalary() {
		return get("salary");
	}

	public M setOtherIncome(java.math.BigDecimal otherIncome) {
		set("other_income", otherIncome);
		return (M)this;
	}
	
	public java.math.BigDecimal getOtherIncome() {
		return get("other_income");
	}

	public M setMonthTotalIncome(java.math.BigDecimal monthTotalIncome) {
		set("month_total_income", monthTotalIncome);
		return (M)this;
	}
	
	public java.math.BigDecimal getMonthTotalIncome() {
		return get("month_total_income");
	}

	public M setUnitForm(java.lang.String unitForm) {
		set("unit_form", unitForm);
		return (M)this;
	}
	
	public java.lang.String getUnitForm() {
		return getStr("unit_form");
	}

	public M setLegarRepresentative(java.lang.String legarRepresentative) {
		set("legar_representative", legarRepresentative);
		return (M)this;
	}
	
	public java.lang.String getLegarRepresentative() {
		return getStr("legar_representative");
	}

	public M setShares(java.math.BigDecimal shares) {
		set("shares", shares);
		return (M)this;
	}
	
	public java.math.BigDecimal getShares() {
		return get("shares");
	}

	public M setSetUpTime(java.util.Date setUpTime) {
		set("set_up_time", setUpTime);
		return (M)this;
	}
	
	public java.util.Date getSetUpTime() {
		return get("set_up_time");
	}

	public M setHolidayLeaveTheProvince(java.lang.String holidayLeaveTheProvince) {
		set("holiday_leave_the_province", holidayLeaveTheProvince);
		return (M)this;
	}
	
	public java.lang.String getHolidayLeaveTheProvince() {
		return getStr("holiday_leave_the_province");
	}

	public M setFamilyKnowTheLoan(java.lang.Integer familyKnowTheLoan) {
		set("family_know_the_loan", familyKnowTheLoan);
		return (M)this;
	}
	
	public java.lang.Integer getFamilyKnowTheLoan() {
		return getInt("family_know_the_loan");
	}

	public M setCusRemark(java.lang.String cusRemark) {
		set("cus_remark", cusRemark);
		return (M)this;
	}
	
	public java.lang.String getCusRemark() {
		return getStr("cus_remark");
	}

	public M setCustomerInfo(java.lang.Integer customerInfo) {
		set("customer_info", customerInfo);
		return (M)this;
	}
	
	public java.lang.Integer getCustomerInfo() {
		return getInt("customer_info");
	}

	public M setContractPurpose(java.lang.String contractPurpose) {
		set("contract_purpose", contractPurpose);
		return (M)this;
	}
	
	public java.lang.String getContractPurpose() {
		return getStr("contract_purpose");
	}

	public M setContractNum(java.lang.String contractNum) {
		set("contract_num", contractNum);
		return (M)this;
	}
	
	public java.lang.String getContractNum() {
		return getStr("contract_num");
	}

	public M setStore(java.lang.Integer store) {
		set("store", store);
		return (M)this;
	}
	
	public java.lang.Integer getStore() {
		return getInt("store");
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

	public M setEducationId(java.lang.Integer educationId) {
		set("education_id", educationId);
		return (M)this;
	}
	
	public java.lang.Integer getEducationId() {
		return getInt("education_id");
	}

	public M setPresentAdress(java.lang.String presentAdress) {
		set("present_adress", presentAdress);
		return (M)this;
	}
	
	public java.lang.String getPresentAdress() {
		return getStr("present_adress");
	}

	public M setPresentAdressPhone(java.lang.String presentAdressPhone) {
		set("present_adress_phone", presentAdressPhone);
		return (M)this;
	}
	
	public java.lang.String getPresentAdressPhone() {
		return getStr("present_adress_phone");
	}

}
