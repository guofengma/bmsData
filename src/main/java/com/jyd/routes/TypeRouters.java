package com.jyd.routes;

import com.jfinal.config.Routes;
import com.jyd.controller.CallInController;
import com.jyd.controller.CallOutController;
import com.jyd.controller.CurrentController;
import com.jyd.controller.DemoController;
import com.jyd.controller.EntryController;
import com.jyd.controller.FundPayController;
import com.jyd.controller.LateFeeController;
import com.jyd.controller.LeaveController;
import com.jyd.controller.RatioController;
import com.jyd.controller.api.BossController;

public class TypeRouters extends Routes {

	public void config() {
		// add("/noSettleContract", NoSettleContractController.class);//未结清合同
		// add("/noPayCapital", NoPayCapitalController.class);//未还本金利息
		// add("/incoming", IncomingController.class);//进件、已通过
		// add("/settleContract", SettleContractController.class);//应结清合同
		add("/lateFee", LateFeeController.class);// 逾期费用
		add("/entry", EntryController.class);// 入职
		add("/leave", LeaveController.class);// 离职
		add("/callin", CallInController.class);// 调入
		add("/callout", CallOutController.class);// 调出
		add("/current", CurrentController.class);// 上期、当前人数
		add("/fundPay", FundPayController.class);// 资金方应还金额
		add("/ratio", RatioController.class);// 比率
		add("/demo", DemoController.class);
		add("/boss", BossController.class);// 老板数据分析
	}

}
