package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseFundContract<M extends BaseFundContract<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setCusContractId(java.lang.Integer cusContractId) {
		set("cus_contract_id", cusContractId);
		return (M)this;
	}
	
	public java.lang.Integer getCusContractId() {
		return getInt("cus_contract_id");
	}

	public M setFundId(java.lang.Integer fundId) {
		set("fund_id", fundId);
		return (M)this;
	}
	
	public java.lang.Integer getFundId() {
		return getInt("fund_id");
	}

	public M setFundProductId(java.lang.Integer fundProductId) {
		set("fund_product_id", fundProductId);
		return (M)this;
	}
	
	public java.lang.Integer getFundProductId() {
		return getInt("fund_product_id");
	}

	public M setBorrowingAmount(java.math.BigDecimal borrowingAmount) {
		set("borrowing_amount", borrowingAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getBorrowingAmount() {
		return get("borrowing_amount");
	}

	public M setPrincipal(java.math.BigDecimal principal) {
		set("principal", principal);
		return (M)this;
	}
	
	public java.math.BigDecimal getPrincipal() {
		return get("principal");
	}

	public M setSupplement(java.math.BigDecimal supplement) {
		set("supplement", supplement);
		return (M)this;
	}
	
	public java.math.BigDecimal getSupplement() {
		return get("supplement");
	}

	public M setReturnThePrincipal(java.math.BigDecimal returnThePrincipal) {
		set("return_the_principal", returnThePrincipal);
		return (M)this;
	}
	
	public java.math.BigDecimal getReturnThePrincipal() {
		return get("return_the_principal");
	}

	public M setReturnInterest(java.math.BigDecimal returnInterest) {
		set("return_interest", returnInterest);
		return (M)this;
	}
	
	public java.math.BigDecimal getReturnInterest() {
		return get("return_interest");
	}

	public M setReturnOfThePrincipal(java.math.BigDecimal returnOfThePrincipal) {
		set("return_of_the_principal", returnOfThePrincipal);
		return (M)this;
	}
	
	public java.math.BigDecimal getReturnOfThePrincipal() {
		return get("return_of_the_principal");
	}

	public M setWaittingOfThePrincipal(java.math.BigDecimal waittingOfThePrincipal) {
		set("waitting_of_the_principal", waittingOfThePrincipal);
		return (M)this;
	}
	
	public java.math.BigDecimal getWaittingOfThePrincipal() {
		return get("waitting_of_the_principal");
	}

	public M setSupplementDate(java.util.Date supplementDate) {
		set("supplement_date", supplementDate);
		return (M)this;
	}
	
	public java.util.Date getSupplementDate() {
		return get("supplement_date");
	}

	public M setRepaymentDate(java.util.Date repaymentDate) {
		set("repayment_date", repaymentDate);
		return (M)this;
	}
	
	public java.util.Date getRepaymentDate() {
		return get("repayment_date");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
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

	public M setState(java.lang.Integer state) {
		set("state", state);
		return (M)this;
	}
	
	public java.lang.Integer getState() {
		return getInt("state");
	}

	public M setReceiPriceDiff(java.math.BigDecimal receiPriceDiff) {
		set("recei_price_diff", receiPriceDiff);
		return (M)this;
	}
	
	public java.math.BigDecimal getReceiPriceDiff() {
		return get("recei_price_diff");
	}

	public M setReturnInterestStage(java.lang.Integer returnInterestStage) {
		set("return_interest_stage", returnInterestStage);
		return (M)this;
	}
	
	public java.lang.Integer getReturnInterestStage() {
		return getInt("return_interest_stage");
	}

	public M setStage(java.lang.Integer stage) {
		set("stage", stage);
		return (M)this;
	}
	
	public java.lang.Integer getStage() {
		return getInt("stage");
	}

}
