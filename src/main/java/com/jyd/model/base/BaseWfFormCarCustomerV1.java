package com.jyd.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseWfFormCarCustomerV1<M extends BaseWfFormCarCustomerV1<M>> extends Model<M> implements IBean {

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

	public M setVehicleInfoId(java.lang.Integer vehicleInfoId) {
		set("vehicle_info_id", vehicleInfoId);
		return (M)this;
	}
	
	public java.lang.Integer getVehicleInfoId() {
		return getInt("vehicle_info_id");
	}

	public M setBrand(java.lang.String brand) {
		set("brand", brand);
		return (M)this;
	}
	
	public java.lang.String getBrand() {
		return getStr("brand");
	}

	public M setColor(java.lang.String color) {
		set("color", color);
		return (M)this;
	}
	
	public java.lang.String getColor() {
		return getStr("color");
	}

	public M setInitialDateOfRegistration(java.util.Date initialDateOfRegistration) {
		set("initial_date_of_registration", initialDateOfRegistration);
		return (M)this;
	}
	
	public java.util.Date getInitialDateOfRegistration() {
		return get("initial_date_of_registration");
	}

	public M setPlate(java.lang.String plate) {
		set("plate", plate);
		return (M)this;
	}
	
	public java.lang.String getPlate() {
		return getStr("plate");
	}

	public M setVin(java.lang.String vin) {
		set("vin", vin);
		return (M)this;
	}
	
	public java.lang.String getVin() {
		return getStr("vin");
	}

	public M setPurchasePrice(java.lang.Double purchasePrice) {
		set("purchase_price", purchasePrice);
		return (M)this;
	}
	
	public java.lang.Double getPurchasePrice() {
		return getDouble("purchase_price");
	}

	public M setValuationPrice(java.lang.Double valuationPrice) {
		set("valuation_price", valuationPrice);
		return (M)this;
	}
	
	public java.lang.Double getValuationPrice() {
		return getDouble("valuation_price");
	}

	public M setMilesOfTraveled(java.lang.Integer milesOfTraveled) {
		set("miles_of_traveled", milesOfTraveled);
		return (M)this;
	}
	
	public java.lang.Integer getMilesOfTraveled() {
		return getInt("miles_of_traveled");
	}

	public M setInsuranceExpirationDate(java.util.Date insuranceExpirationDate) {
		set("insurance_expiration_date", insuranceExpirationDate);
		return (M)this;
	}
	
	public java.util.Date getInsuranceExpirationDate() {
		return get("insurance_expiration_date");
	}

	public M setAnnualRiskDueDate(java.util.Date annualRiskDueDate) {
		set("annual_risk_due_date", annualRiskDueDate);
		return (M)this;
	}
	
	public java.util.Date getAnnualRiskDueDate() {
		return get("annual_risk_due_date");
	}

	public M setEngineNo(java.lang.String engineNo) {
		set("engine_no", engineNo);
		return (M)this;
	}
	
	public java.lang.String getEngineNo() {
		return getStr("engine_no");
	}

	public M setCustomerId(java.lang.Integer customerId) {
		set("customer_id", customerId);
		return (M)this;
	}
	
	public java.lang.Integer getCustomerId() {
		return getInt("customer_id");
	}

	public M setVehicleTypeId(java.lang.Integer vehicleTypeId) {
		set("vehicle_type_id", vehicleTypeId);
		return (M)this;
	}
	
	public java.lang.Integer getVehicleTypeId() {
		return getInt("vehicle_type_id");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public M setReleaseDate(java.util.Date releaseDate) {
		set("release_date", releaseDate);
		return (M)this;
	}
	
	public java.util.Date getReleaseDate() {
		return get("release_date");
	}

	public M setGearbox(java.lang.String gearbox) {
		set("gearbox", gearbox);
		return (M)this;
	}
	
	public java.lang.String getGearbox() {
		return getStr("gearbox");
	}

	public M setDisplacement(java.lang.String displacement) {
		set("displacement", displacement);
		return (M)this;
	}
	
	public java.lang.String getDisplacement() {
		return getStr("displacement");
	}

	public M setEmissions(java.lang.String emissions) {
		set("emissions", emissions);
		return (M)this;
	}
	
	public java.lang.String getEmissions() {
		return getStr("emissions");
	}

	public M setVehicleLevel(java.lang.String vehicleLevel) {
		set("vehicle_level", vehicleLevel);
		return (M)this;
	}
	
	public java.lang.String getVehicleLevel() {
		return getStr("vehicle_level");
	}

	public M setDoor(java.lang.String door) {
		set("door", door);
		return (M)this;
	}
	
	public java.lang.String getDoor() {
		return getStr("door");
	}

	public M setOil(java.lang.String oil) {
		set("oil", oil);
		return (M)this;
	}
	
	public java.lang.String getOil() {
		return getStr("oil");
	}

	public M setDriveMode(java.lang.String driveMode) {
		set("drive_mode", driveMode);
		return (M)this;
	}
	
	public java.lang.String getDriveMode() {
		return getStr("drive_mode");
	}

	public M setTimeToMarket(java.util.Date timeToMarket) {
		set("time_to_market", timeToMarket);
		return (M)this;
	}
	
	public java.util.Date getTimeToMarket() {
		return get("time_to_market");
	}

	public M setAlloction(java.lang.String alloction) {
		set("alloction", alloction);
		return (M)this;
	}
	
	public java.lang.String getAlloction() {
		return getStr("alloction");
	}

	public M setModel(java.lang.String model) {
		set("model", model);
		return (M)this;
	}
	
	public java.lang.String getModel() {
		return getStr("model");
	}

	public M setTrimEvluate(java.lang.String trimEvluate) {
		set("trim_evluate", trimEvluate);
		return (M)this;
	}
	
	public java.lang.String getTrimEvluate() {
		return getStr("trim_evluate");
	}

	public M setSeat(java.lang.String seat) {
		set("seat", seat);
		return (M)this;
	}
	
	public java.lang.String getSeat() {
		return getStr("seat");
	}

	public M setTrim(java.lang.String trim) {
		set("trim", trim);
		return (M)this;
	}
	
	public java.lang.String getTrim() {
		return getStr("trim");
	}

	public M setCarBodyEvluate(java.lang.String carBodyEvluate) {
		set("car_body_evluate", carBodyEvluate);
		return (M)this;
	}
	
	public java.lang.String getCarBodyEvluate() {
		return getStr("car_body_evluate");
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

	public M setLouver(java.lang.Boolean louver) {
		set("louver", louver);
		return (M)this;
	}
	
	public java.lang.Boolean getLouver() {
		return get("louver");
	}

	public M setSeatz(java.lang.Boolean seatz) {
		set("seatz", seatz);
		return (M)this;
	}
	
	public java.lang.Boolean getSeatz() {
		return get("seatz");
	}

	public M setLaunch(java.lang.Boolean launch) {
		set("launch", launch);
		return (M)this;
	}
	
	public java.lang.Boolean getLaunch() {
		return get("launch");
	}

	public M setPowertSeat(java.lang.Boolean powertSeat) {
		set("powert_seat", powertSeat);
		return (M)this;
	}
	
	public java.lang.Boolean getPowertSeat() {
		return get("powert_seat");
	}

	public M setPowerBoot(java.lang.Boolean powerBoot) {
		set("power_boot", powerBoot);
		return (M)this;
	}
	
	public java.lang.Boolean getPowerBoot() {
		return get("power_boot");
	}

	public M setSeatHeating(java.lang.Boolean seatHeating) {
		set("seat_heating", seatHeating);
		return (M)this;
	}
	
	public java.lang.Boolean getSeatHeating() {
		return get("seat_heating");
	}

	public M setGps(java.lang.Boolean gps) {
		set("gps", gps);
		return (M)this;
	}
	
	public java.lang.Boolean getGps() {
		return get("gps");
	}

	public M setElectronicSuspension(java.lang.Boolean electronicSuspension) {
		set("electronic_suspension", electronicSuspension);
		return (M)this;
	}
	
	public java.lang.Boolean getElectronicSuspension() {
		return get("electronic_suspension");
	}

	public M setLookedUpAndPrompt(java.lang.Boolean lookedUpAndPrompt) {
		set("looked_up_and_prompt", lookedUpAndPrompt);
		return (M)this;
	}
	
	public java.lang.Boolean getLookedUpAndPrompt() {
		return get("looked_up_and_prompt");
	}

	public M setSeatNum(java.lang.Integer seatNum) {
		set("seat_num", seatNum);
		return (M)this;
	}
	
	public java.lang.Integer getSeatNum() {
		return getInt("seat_num");
	}

	public M setCarAccessory(java.lang.String carAccessory) {
		set("car_accessory", carAccessory);
		return (M)this;
	}
	
	public java.lang.String getCarAccessory() {
		return getStr("car_accessory");
	}

	public M setCarVeer(java.lang.String carVeer) {
		set("car_veer", carVeer);
		return (M)this;
	}
	
	public java.lang.String getCarVeer() {
		return getStr("car_veer");
	}

	public M setEngine(java.lang.String engine) {
		set("engine", engine);
		return (M)this;
	}
	
	public java.lang.String getEngine() {
		return getStr("engine");
	}

	public M setSpeedChangingBox(java.lang.String speedChangingBox) {
		set("speed_changing_box", speedChangingBox);
		return (M)this;
	}
	
	public java.lang.String getSpeedChangingBox() {
		return getStr("speed_changing_box");
	}

	public M setAbsorber(java.lang.String absorber) {
		set("absorber", absorber);
		return (M)this;
	}
	
	public java.lang.String getAbsorber() {
		return getStr("absorber");
	}

	public M setExhaustPipe(java.lang.String exhaustPipe) {
		set("exhaust_pipe", exhaustPipe);
		return (M)this;
	}
	
	public java.lang.String getExhaustPipe() {
		return getStr("exhaust_pipe");
	}

	public M setAirConditioningEquipment(java.lang.String airConditioningEquipment) {
		set("air_conditioning_equipment", airConditioningEquipment);
		return (M)this;
	}
	
	public java.lang.String getAirConditioningEquipment() {
		return getStr("air_conditioning_equipment");
	}

	public M setCheckTheResult(java.lang.String checkTheResult) {
		set("check_the_result", checkTheResult);
		return (M)this;
	}
	
	public java.lang.String getCheckTheResult() {
		return getStr("check_the_result");
	}

	public M setCarCondition(java.lang.String carCondition) {
		set("car_condition", carCondition);
		return (M)this;
	}
	
	public java.lang.String getCarCondition() {
		return getStr("car_condition");
	}

	public M setFault(java.lang.String fault) {
		set("fault", fault);
		return (M)this;
	}
	
	public java.lang.String getFault() {
		return getStr("fault");
	}

	public M setRemarkz(java.lang.String remarkz) {
		set("remarkz", remarkz);
		return (M)this;
	}
	
	public java.lang.String getRemarkz() {
		return getStr("remarkz");
	}

	public M setCustomerContractId(java.lang.Integer customerContractId) {
		set("customer_contract_id", customerContractId);
		return (M)this;
	}
	
	public java.lang.Integer getCustomerContractId() {
		return getInt("customer_contract_id");
	}

}
