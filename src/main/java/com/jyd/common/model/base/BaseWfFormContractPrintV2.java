package com.jyd.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormContractPrintV2<M extends BaseWfFormContractPrintV2<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setDataId(java.lang.Integer dataId) {
		set("data_id", dataId);
		return (M)this;
	}
	
	public java.lang.Integer getDataId() {
		return getInt("data_id");
	}

	public M setGpsCostTypeId(java.lang.Integer gpsCostTypeId) {
		set("gps_cost_type_id", gpsCostTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getGpsCostTypeId() {
		return getInt("gps_cost_type_id");
	}

	public M setValue(java.math.BigDecimal value) {
		set("value", value);
		return (M)this;
	}
	
	public java.math.BigDecimal getValue() {
		return get("value");
	}

	public M setContractAmount(java.math.BigDecimal contractAmount) {
		set("contract_amount", contractAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getContractAmount() {
		return get("contract_amount");
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

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setContractId(java.lang.Integer contractId) {
		set("contract_id", contractId);
		return (M)this;
	}
	
	public java.lang.Integer getContractId() {
		return getInt("contract_id");
	}

	public M setContractStartDate(java.util.Date contractStartDate) {
		set("contract_start_date", contractStartDate);
		return (M)this;
	}
	
	public java.util.Date getContractStartDate() {
		return get("contract_start_date");
	}

	public M setContractEndDate(java.util.Date contractEndDate) {
		set("contract_end_date", contractEndDate);
		return (M)this;
	}
	
	public java.util.Date getContractEndDate() {
		return get("contract_end_date");
	}

	public M setRepaymentAccountId(java.lang.Integer repaymentAccountId) {
		set("repayment_account_id", repaymentAccountId);
		return (M)this;
	}
	
	public java.lang.Integer getRepaymentAccountId() {
		return getInt("repayment_account_id");
	}

	public M setProductId(java.lang.Integer productId) {
		set("product_id", productId);
		return (M)this;
	}
	
	public java.lang.Integer getProductId() {
		return getInt("product_id");
	}

	public M setMortgagerId(java.lang.Integer mortgagerId) {
		set("mortgager_id", mortgagerId);
		return (M)this;
	}
	
	public java.lang.Integer getMortgagerId() {
		return getInt("mortgager_id");
	}

	public M setRepaymentStageId(java.lang.Integer repaymentStageId) {
		set("repayment_stage_id", repaymentStageId);
		return (M)this;
	}
	
	public java.lang.Integer getRepaymentStageId() {
		return getInt("repayment_stage_id");
	}

	public M setBankName(java.lang.String bankName) {
		set("bank_name", bankName);
		return (M)this;
	}
	
	public java.lang.String getBankName() {
		return getStr("bank_name");
	}

	public M setBankCard(java.lang.String bankCard) {
		set("bank_card", bankCard);
		return (M)this;
	}
	
	public java.lang.String getBankCard() {
		return getStr("bank_card");
	}

	public M setParkingFeeId(java.lang.Integer parkingFeeId) {
		set("parking_fee_id", parkingFeeId);
		return (M)this;
	}
	
	public java.lang.Integer getParkingFeeId() {
		return getInt("parking_fee_id");
	}

	public M setVin(java.lang.String vin) {
		set("vin", vin);
		return (M)this;
	}
	
	public java.lang.String getVin() {
		return getStr("vin");
	}

	public M setPlate(java.lang.String plate) {
		set("plate", plate);
		return (M)this;
	}
	
	public java.lang.String getPlate() {
		return getStr("plate");
	}

	public M setBrand(java.lang.String brand) {
		set("brand", brand);
		return (M)this;
	}
	
	public java.lang.String getBrand() {
		return getStr("brand");
	}

	public M setPurchasePrice(java.math.BigDecimal purchasePrice) {
		set("purchase_price", purchasePrice);
		return (M)this;
	}
	
	public java.math.BigDecimal getPurchasePrice() {
		return get("purchase_price");
	}

	public M setEngineNo(java.lang.String engineNo) {
		set("engine_no", engineNo);
		return (M)this;
	}
	
	public java.lang.String getEngineNo() {
		return getStr("engine_no");
	}

	public M setModel(java.lang.String model) {
		set("model", model);
		return (M)this;
	}
	
	public java.lang.String getModel() {
		return getStr("model");
	}

	public M setValuationPrice(java.math.BigDecimal valuationPrice) {
		set("valuation_price", valuationPrice);
		return (M)this;
	}
	
	public java.math.BigDecimal getValuationPrice() {
		return get("valuation_price");
	}

	public M setContractManageTypeId(java.lang.Integer contractManageTypeId) {
		set("contract_manage_type_id", contractManageTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getContractManageTypeId() {
		return getInt("contract_manage_type_id");
	}

	public M setContractManageValue(java.math.BigDecimal contractManageValue) {
		set("contract_manage_value", contractManageValue);
		return (M)this;
	}
	
	public java.math.BigDecimal getContractManageValue() {
		return get("contract_manage_value");
	}

	public M setBidAmount(java.math.BigDecimal bidAmount) {
		set("bid_amount", bidAmount);
		return (M)this;
	}
	
	public java.math.BigDecimal getBidAmount() {
		return get("bid_amount");
	}

	public M setOldPlate(java.lang.String oldPlate) {
		set("old_plate", oldPlate);
		return (M)this;
	}
	
	public java.lang.String getOldPlate() {
		return getStr("old_plate");
	}

	public M setBaBusinessSourceId(java.lang.Integer baBusinessSourceId) {
		set("ba_business_source_id", baBusinessSourceId);
		return (M)this;
	}
	
	public java.lang.Integer getBaBusinessSourceId() {
		return getInt("ba_business_source_id");
	}

	public M setReturnPointNum(java.math.BigDecimal returnPointNum) {
		set("return_point_num", returnPointNum);
		return (M)this;
	}
	
	public java.math.BigDecimal getReturnPointNum() {
		return get("return_point_num");
	}

	public M setBidPlatformRemark(java.lang.String bidPlatformRemark) {
		set("bid_platform_remark", bidPlatformRemark);
		return (M)this;
	}
	
	public java.lang.String getBidPlatformRemark() {
		return getStr("bid_platform_remark");
	}

	public M setSummary(java.lang.String summary) {
		set("summary", summary);
		return (M)this;
	}
	
	public java.lang.String getSummary() {
		return getStr("summary");
	}

}
