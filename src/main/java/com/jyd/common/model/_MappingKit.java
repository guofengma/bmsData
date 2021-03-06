package com.jyd.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("att_daily_attend", "id", AttDailyAttend.class);
		arp.addMapping("att_emp_date", "id", AttEmpDate.class);
		arp.addMapping("att_emp_month", "id", AttEmpMonth.class);
		arp.addMapping("att_evection", "id", AttEvection.class);
		arp.addMapping("att_evection_type", "id", AttEvectionType.class);
		arp.addMapping("att_leave", "id", AttLeave.class);
		arp.addMapping("att_leave_type", "id", AttLeaveType.class);
		arp.addMapping("att_monthly_attend", "id", AttMonthlyAttend.class);
		arp.addMapping("att_overtime", "id", AttOvertime.class);
		arp.addMapping("att_overtime_type", "id", AttOvertimeType.class);
		arp.addMapping("att_punch", "id", AttPunch.class);
		arp.addMapping("att_rest_time", "id", AttRestTime.class);
		arp.addMapping("att_scheduling", "id", AttScheduling.class);
		arp.addMapping("att_shift", "id", AttShift.class);
		arp.addMapping("att_shift_worktime", "id", AttShiftWorktime.class);
		arp.addMapping("att_work_time", "id", AttWorkTime.class);
		arp.addMapping("ba_assets_type", "id", BaAssetsType.class);
		arp.addMapping("ba_business_source", "id", BaBusinessSource.class);
		arp.addMapping("ba_calc_stage_version", "id", BaCalcStageVersion.class);
		arp.addMapping("ba_contract_manage_type", "id", BaContractManageType.class);
		arp.addMapping("ba_contract_version", "id", BaContractVersion.class);
		arp.addMapping("ba_cost_type", "id", BaCostType.class);
		arp.addMapping("ba_department_type", "id", BaDepartmentType.class);
		arp.addMapping("ba_duty_type", "id", BaDutyType.class);
		arp.addMapping("ba_education_type", "id", BaEducationType.class);
		arp.addMapping("ba_employee_properties", "id", BaEmployeeProperties.class);
		arp.addMapping("ba_gps_cost_type", "id", BaGpsCostType.class);
		arp.addMapping("ba_insurance_type", "id", BaInsuranceType.class);
		arp.addMapping("ba_lender", "id", BaLender.class);
		arp.addMapping("ba_loan_type", "id", BaLoanType.class);
		arp.addMapping("ba_mortgager", "id", BaMortgager.class);
		arp.addMapping("ba_parking_fee", "id", BaParkingFee.class);
		arp.addMapping("ba_position_type", "id", BaPositionType.class);
		arp.addMapping("ba_process_type", "id", BaProcessType.class);
		arp.addMapping("ba_product_category", "id", BaProductCategory.class);
		arp.addMapping("ba_product_classify", "id", BaProductClassify.class);
		arp.addMapping("ba_product_parameter", "id", BaProductParameter.class);
		arp.addMapping("ba_product_type", "id", BaProductType.class);
		arp.addMapping("ba_property_type", "id", BaPropertyType.class);
		arp.addMapping("ba_repayment_account", "id", BaRepaymentAccount.class);
		arp.addMapping("ba_repayment_stage", "id", BaRepaymentStage.class);
		arp.addMapping("ba_repayment_type", "id", BaRepaymentType.class);
		arp.addMapping("ba_salary_level", "id", BaSalaryLevel.class);
		arp.addMapping("ba_schedule_type", "id", BaScheduleType.class);
		arp.addMapping("ba_settlement_type", "id", BaSettlementType.class);
		arp.addMapping("ba_store", "id", BaStore.class);
		arp.addMapping("ba_thirdpart_company", "id", BaThirdpartCompany.class);
		arp.addMapping("ba_thirdpart_query_type", "id", BaThirdpartQueryType.class);
		arp.addMapping("ba_upload_file_type", "id", BaUploadFileType.class);
		arp.addMapping("ba_vehicle_type", "id", BaVehicleType.class);
		arp.addMapping("contract_file", "id", ContractFile.class);
		arp.addMapping("cus_borrower", "id", CusBorrower.class);
		arp.addMapping("cus_borrower_credit_information_table", "id", CusBorrowerCreditInformationTable.class);
		arp.addMapping("cus_car_assess", "id", CusCarAssess.class);
		arp.addMapping("cus_contract", "id", CusContract.class);
		arp.addMapping("cus_contract_blacklist", "id", CusContractBlacklist.class);
		arp.addMapping("cus_contract_cost", "id", CusContractCost.class);
		arp.addMapping("cus_contract_face_trial", "id", CusContractFaceTrial.class);
		arp.addMapping("cus_contract_face_trial_v1", "id", CusContractFaceTrialV1.class);
		arp.addMapping("cus_contract_follow_employee_history", "id", CusContractFollowEmployeeHistory.class);
		arp.addMapping("cus_contract_gps_late_fee", "id", CusContractGpsLateFee.class);
		arp.addMapping("cus_contract_late_fee", "id", CusContractLateFee.class);
		arp.addMapping("cus_contract_lender", "id", CusContractLender.class);
		arp.addMapping("cus_contract_month", "id", CusContractMonth.class);
		arp.addMapping("cus_contract_month_detailed", "id", CusContractMonthDetailed.class);
		arp.addMapping("cus_contract_overdue_amount", "id", CusContractOverdueAmount.class);
		arp.addMapping("cus_contract_para", "id", CusContractPara.class);
		arp.addMapping("cus_contract_print_file", "id", CusContractPrintFile.class);
		arp.addMapping("cus_contract_renewal", "id", CusContractRenewal.class);
		arp.addMapping("cus_contract_repayment", "id", CusContractRepayment.class);
		arp.addMapping("cus_contract_repayment_other_fee", "id", CusContractRepaymentOtherFee.class);
		arp.addMapping("cus_contract_schedule_track", "id", CusContractScheduleTrack.class);
		arp.addMapping("cus_contract_stage", "id", CusContractStage.class);
		arp.addMapping("cus_contract_stage_fee", "id", CusContractStageFee.class);
		arp.addMapping("cus_contract_stage_fee_repayment", "id", CusContractStageFeeRepayment.class);
		arp.addMapping("cus_contract_uploadfile", "id", CusContractUploadfile.class);
		arp.addMapping("cus_equipment", "id", CusEquipment.class);
		arp.addMapping("cus_goout_homevisit", "id", CusGooutHomevisit.class);
		arp.addMapping("cus_gpsinstall", "id", CusGpsinstall.class);
		arp.addMapping("cus_personal_info", "id", CusPersonalInfo.class);
		arp.addMapping("cus_secondary_contact_info", "id", CusSecondaryContactInfo.class);
		arp.addMapping("cus_thirdpart_bairong_customer_credit", "id", CusThirdpartBairongCustomerCredit.class);
		arp.addMapping("cus_thirdpart_bairong_query_log", "id", CusThirdpartBairongQueryLog.class);
		arp.addMapping("cus_thirdpart_bairong_query_type", "id", CusThirdpartBairongQueryType.class);
		arp.addMapping("cus_thirdpart_customer_credit", "id", CusThirdpartCustomerCredit.class);
		arp.addMapping("cus_thirdpart_gps_vehicle_base_data", "cus_thirdpart_gps_vehicle_base_data_id", CusThirdpartGpsVehicleBaseData.class);
		arp.addMapping("cus_thirdpart_query_log", "id", CusThirdpartQueryLog.class);
		arp.addMapping("cus_thirdpart_tongdun_customer_credit", "id", CusThirdpartTongdunCustomerCredit.class);
		arp.addMapping("cus_thirdpart_tongdun_query_log", "id", CusThirdpartTongdunQueryLog.class);
		arp.addMapping("cus_thirdpart_tongdun_query_type", "id", CusThirdpartTongdunQueryType.class);
		arp.addMapping("cus_vehicle_info", "id", CusVehicleInfo.class);
		arp.addMapping("employee_salary_item", "id", EmployeeSalaryItem.class);
		arp.addMapping("fund", "id", Fund.class);
		arp.addMapping("fund_contract", "id", FundContract.class);
		arp.addMapping("fund_contract_cost", "id", FundContractCost.class);
		arp.addMapping("fund_contract_stage", "id", FundContractStage.class);
		arp.addMapping("fund_contract_stage_fee", "id", FundContractStageFee.class);
		arp.addMapping("fund_contract_stage_repayment", "id", FundContractStageRepayment.class);
		arp.addMapping("fund_product", "id", FundProduct.class);
		arp.addMapping("fund_product_para", "id", FundProductPara.class);
		arp.addMapping("hbase_map_type", "id", HbaseMapType.class);
		arp.addMapping("hr_contract", "id", HrContract.class);
		arp.addMapping("hr_department", "id", HrDepartment.class);
		arp.addMapping("hr_department_monthly_statement", "id", HrDepartmentMonthlyStatement.class);
		arp.addMapping("hr_department_weekly_statement", "id", HrDepartmentWeeklyStatement.class);
		arp.addMapping("hr_emergence_contact", "id", HrEmergenceContact.class);
		arp.addMapping("hr_emp_duty", "id", HrEmpDuty.class);
		arp.addMapping("hr_emp_experience", "id", HrEmpExperience.class);
		arp.addMapping("hr_employee", "id", HrEmployee.class);
		arp.addMapping("hr_parent_department", "id", HrParentDepartment.class);
		arp.addMapping("hr_secondee", "id", HrSecondee.class);
		arp.addMapping("hr_son_department", "id", HrSonDepartment.class);
		arp.addMapping("hr_staff", "id", HrStaff.class);
		arp.addMapping("hr_to_loan", "id", HrToLoan.class);
		arp.addMapping("hr_work_experience", "id", HrWorkExperience.class);
		arp.addMapping("message", "id", Message.class);
		arp.addMapping("message_data", "id", MessageData.class);
		arp.addMapping("message_type", "id", MessageType.class);
		arp.addMapping("pro_business_source", "id", ProBusinessSource.class);
		arp.addMapping("pro_cost", "id", ProCost.class);
		arp.addMapping("pro_parameter", "id", ProParameter.class);
		arp.addMapping("pro_repayment_stage", "id", ProRepaymentStage.class);
		arp.addMapping("pro_store", "id", ProStore.class);
		arp.addMapping("pro_upload_file", "id", ProUploadFile.class);
		arp.addMapping("product", "id", Product.class);
		arp.addMapping("sal_base_salary_item", "id", SalBaseSalaryItem.class);
		arp.addMapping("sal_employee_base_salary", "id", SalEmployeeBaseSalary.class);
		arp.addMapping("sal_employee_base_salary_history", "id", SalEmployeeBaseSalaryHistory.class);
		arp.addMapping("sal_employee_salary", "id", SalEmployeeSalary.class);
		arp.addMapping("sal_employee_salary_history", "id", SalEmployeeSalaryHistory.class);
		arp.addMapping("sal_insurance", "id", SalInsurance.class);
		arp.addMapping("sal_lock_month_salary", "id", SalLockMonthSalary.class);
		arp.addMapping("sal_month_salary", "id", SalMonthSalary.class);
		arp.addMapping("sal_month_salary_expand", "id", SalMonthSalaryExpand.class);
		arp.addMapping("sal_salary_item", "id", SalSalaryItem.class);
		arp.addMapping("sal_salary_item_type", "id", SalSalaryItemType.class);
		arp.addMapping("sal_salary_structure", "id", SalSalaryStructure.class);
		arp.addMapping("sal_salary_structure_base_salary_item", "id", SalSalaryStructureBaseSalaryItem.class);
		arp.addMapping("sch_periodic", "id", SchPeriodic.class);
		arp.addMapping("sch_task_log", "id", SchTaskLog.class);
		arp.addMapping("sto_lender", "id", StoLender.class);
		arp.addMapping("sto_mortgager", "id", StoMortgager.class);
		arp.addMapping("sto_repayment_account", "id", StoRepaymentAccount.class);
		arp.addMapping("sys_error_log", "id", SysErrorLog.class);
		arp.addMapping("sys_group", "id", SysGroup.class);
		arp.addMapping("sys_group_menu", "id", SysGroupMenu.class);
		arp.addMapping("sys_group_user", "id", SysGroupUser.class);
		arp.addMapping("sys_job", "job_id", SysJob.class);
		arp.addMapping("sys_menu", "id", SysMenu.class);
		arp.addMapping("sys_page_use_log", "id", SysPageUseLog.class);
		arp.addMapping("sys_user", "id", SysUser.class);
		arp.addMapping("sys_user_menu", "id", SysUserMenu.class);
		arp.addMapping("temp_cus_thirdpart_customer_credit", "id", TempCusThirdpartCustomerCredit.class);
		arp.addMapping("temp_cus_thirdpart_query_log", "id", TempCusThirdpartQueryLog.class);
		arp.addMapping("test", "id", Test.class);
		arp.addMapping("test_batch_insert", "cus_thirdpart_gps_vehicle_base_data_id", TestBatchInsert.class);
		arp.addMapping("wf_agent", "id", WfAgent.class);
		arp.addMapping("wf_flow", "id", WfFlow.class);
		arp.addMapping("wf_form", "id", WfForm.class);
		arp.addMapping("wf_form_adjust_salary_v1", "id", WfFormAdjustSalaryV1.class);
		arp.addMapping("wf_form_assembly_v1", "data_key", WfFormAssemblyV1.class);
		arp.addMapping("wf_form_car_customer_v1", "id", WfFormCarCustomerV1.class);
		arp.addMapping("wf_form_contract_face_trial_v2", "id", WfFormContractFaceTrialV2.class);
		arp.addMapping("wf_form_contract_face_trial_v3", "id", WfFormContractFaceTrialV3.class);
		arp.addMapping("wf_form_contract_print_v1", "id", WfFormContractPrintV1.class);
		arp.addMapping("wf_form_contract_print_v2", "id", WfFormContractPrintV2.class);
		arp.addMapping("wf_form_cus_borrower", "id", WfFormCusBorrower.class);
		arp.addMapping("wf_form_cus_car_assess", "data_key", WfFormCusCarAssess.class);
		arp.addMapping("wf_form_cus_contract_cost", "id", WfFormCusContractCost.class);
		arp.addMapping("wf_form_cus_contract_lender", "id", WfFormCusContractLender.class);
		arp.addMapping("wf_form_cus_contract_para", "id", WfFormCusContractPara.class);
		arp.addMapping("wf_form_cus_contract_uploadfile", "id", WfFormCusContractUploadfile.class);
		arp.addMapping("wf_form_cus_equipment", "id", WfFormCusEquipment.class);
		arp.addMapping("wf_form_cus_secondary_contact_info", "id", WfFormCusSecondaryContactInfo.class);
		arp.addMapping("wf_form_cus_service_contract", "data_key", WfFormCusServiceContract.class);
		arp.addMapping("wf_form_employee_base_salary_v1", "id", WfFormEmployeeBaseSalaryV1.class);
		arp.addMapping("wf_form_employee_contact_v1", "id", WfFormEmployeeContactV1.class);
		arp.addMapping("wf_form_employee_contract_v1", "id", WfFormEmployeeContractV1.class);
		arp.addMapping("wf_form_employee_duty_v1", "id", WfFormEmployeeDutyV1.class);
		arp.addMapping("wf_form_employee_position_v1", "id", WfFormEmployeePositionV1.class);
		arp.addMapping("wf_form_employee_salary_v1", "id", WfFormEmployeeSalaryV1.class);
		arp.addMapping("wf_form_employee_v1", "data_key", WfFormEmployeeV1.class);
		arp.addMapping("wf_form_formal_apply_v1", "id", WfFormFormalApplyV1.class);
		arp.addMapping("wf_form_goout_homevisit_v1", "id", WfFormGooutHomevisitV1.class);
		arp.addMapping("wf_form_gps_install_v1", "id", WfFormGpsInstallV1.class);
		arp.addMapping("wf_form_leave_employee_v1", "data_key", WfFormLeaveEmployeeV1.class);
		arp.addMapping("wf_form_personal_info_v2", "id", WfFormPersonalInfoV2.class);
		arp.addMapping("wf_form_record_person", "id", WfFormRecordPerson.class);
		arp.addMapping("wf_form_salary_detail_v1", "id", WfFormSalaryDetailV1.class);
		arp.addMapping("wf_form_set_salary_v1", "id", WfFormSetSalaryV1.class);
		arp.addMapping("wf_form_to_loan_v1", "date_key", WfFormToLoanV1.class);
		arp.addMapping("wf_form_work_experience_v1", "id", WfFormWorkExperienceV1.class);
		arp.addMapping("wf_work_flow", "id", WfWorkFlow.class);
		arp.addMapping("wf_work_flow_data", "id", WfWorkFlowData.class);
		arp.addMapping("wf_work_flow_optional_processor", "id", WfWorkFlowOptionalProcessor.class);
		arp.addMapping("wf_work_flow_status", "id", WfWorkFlowStatus.class);
		arp.addMapping("wf_work_flow_type", "id", WfWorkFlowType.class);
		arp.addMapping("wf_work_flow_type_flow", "id", WfWorkFlowTypeFlow.class);
		arp.addMapping("yxd_contract", "id", YxdContract.class);
		arp.addMapping("yxd_customer_contact", "id", YxdCustomerContact.class);
		arp.addMapping("yxd_customer_info", "id", YxdCustomerInfo.class);
	}
}

