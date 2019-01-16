package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseHrDepartmentWeeklyStatement<M extends BaseHrDepartmentWeeklyStatement<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setDepartmentId(java.lang.Integer departmentId) {
		set("department_id", departmentId);
		return (M)this;
	}
	
	public java.lang.Integer getDepartmentId() {
		return getInt("department_id");
	}

	public M setStoreId(java.lang.Integer storeId) {
		set("store_id", storeId);
		return (M)this;
	}
	
	public java.lang.Integer getStoreId() {
		return getInt("store_id");
	}

	public M setYear(java.lang.Integer year) {
		set("year", year);
		return (M)this;
	}
	
	public java.lang.Integer getYear() {
		return getInt("year");
	}

	public M setWeekNumber(java.lang.Integer weekNumber) {
		set("week_number", weekNumber);
		return (M)this;
	}
	
	public java.lang.Integer getWeekNumber() {
		return getInt("week_number");
	}

	public M setBeginDate(java.util.Date beginDate) {
		set("begin_date", beginDate);
		return (M)this;
	}
	
	public java.util.Date getBeginDate() {
		return get("begin_date");
	}

	public M setEndDate(java.util.Date endDate) {
		set("end_date", endDate);
		return (M)this;
	}
	
	public java.util.Date getEndDate() {
		return get("end_date");
	}

	public M setTotalEmployee(java.lang.Integer totalEmployee) {
		set("total_employee", totalEmployee);
		return (M)this;
	}
	
	public java.lang.Integer getTotalEmployee() {
		return getInt("total_employee");
	}

	public M setTotalStaff(java.lang.Integer totalStaff) {
		set("total_staff", totalStaff);
		return (M)this;
	}
	
	public java.lang.Integer getTotalStaff() {
		return getInt("total_staff");
	}

	public M setTotalJoin(java.lang.Integer totalJoin) {
		set("total_join", totalJoin);
		return (M)this;
	}
	
	public java.lang.Integer getTotalJoin() {
		return getInt("total_join");
	}

	public M setTotalLeave(java.lang.Integer totalLeave) {
		set("total_leave", totalLeave);
		return (M)this;
	}
	
	public java.lang.Integer getTotalLeave() {
		return getInt("total_leave");
	}

	public M setTotalPromotion(java.lang.Integer totalPromotion) {
		set("total_promotion", totalPromotion);
		return (M)this;
	}
	
	public java.lang.Integer getTotalPromotion() {
		return getInt("total_promotion");
	}

	public M setTotalTuningIn(java.lang.Integer totalTuningIn) {
		set("total_tuning_in", totalTuningIn);
		return (M)this;
	}
	
	public java.lang.Integer getTotalTuningIn() {
		return getInt("total_tuning_in");
	}

	public M setTotalTuningOut(java.lang.Integer totalTuningOut) {
		set("total_tuning_out", totalTuningOut);
		return (M)this;
	}
	
	public java.lang.Integer getTotalTuningOut() {
		return getInt("total_tuning_out");
	}

	public M setTotalRaiseASalary(java.lang.Integer totalRaiseASalary) {
		set("total_raise_a_salary", totalRaiseASalary);
		return (M)this;
	}
	
	public java.lang.Integer getTotalRaiseASalary() {
		return getInt("total_raise_a_salary");
	}

}
