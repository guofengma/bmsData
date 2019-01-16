package com.jyd.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.thrift.TException;

import com.jfinal.core.Controller;
import com.jyd.common.model.BaRepaymentStage;
import com.jyd.common.model.BaStore;
import com.jyd.common.model.CusContract;
import com.jyd.common.model.CusContractRepayment;
import com.jyd.common.model.CusContractStage;
import com.jyd.common.model.CusContractStageFee;
import com.jyd.service.CronDayService;

public class IndexController extends Controller {

	public void index() throws TException {
		render("index.html");
	}

	public void page() {
		render("page.html");
	}

	public void test() {

		StringBuilder hql = new StringBuilder();
		CusContract cdao = new CusContract().dao();
		CusContractStage sdao = new CusContractStage().dao();
		CusContractStageFee fdao = new CusContractStageFee().dao();
		BaRepaymentStage badao = new BaRepaymentStage().dao();
		CusContractRepayment rdao = new CusContractRepayment().dao();
		BaStore storedao = new BaStore().dao();

		List<BaStore> storeList = storedao.find("select * from ba_store");
		int count = 0;
		for (BaStore store : storeList) {
			hql.append(++count + "\t门店\t" + store.getShortName() + "\r\n");
			System.err.println(store.getShortName() + "---" + store.getId() + "");
			List<CusContract> csList = cdao
					.find("select * from cus_contract where state not in(1,5) and store_id=" + store.getId() + "");
			for (CusContract cs : csList) {
				BaRepaymentStage bas = badao
						.findFirst("select * from ba_repayment_stage where id=" + cs.getRepaymentStageId() + "");
				List<CusContractStage> ss = sdao
						.find("select * from cus_contract_stage where cus_contract_id=" + cs.getId() + "");
				if (ss == null || bas == null) {
					continue;
				}
				if (ss.size() - 1 != bas.getRepaymentStage()) {
					hql.append("不一致---当期" + cs.getCusName() + "合同表总期数" + bas.getRepaymentStage() + " 分期期数 " + ss.size()
							+ "\r\n");
				}

				int a = 0;
				for (CusContractStage s : ss) {
					if (s.getState() == 1) {
						String reps = "select * from cus_contract_repayment where cus_contract_stage_id =" + s.getId()
								+ "";
						List<CusContractRepayment> repsList = rdao.find(reps);
						BigDecimal pay = new BigDecimal(0);
						if (repsList.isEmpty()) {
							a = 1;
							// hql.append(cs.getState() + "\t" + "客户" + cs.getCusName() + "\t" +
							// cs.getCusContractNo()
							// + "\t--第" + s.getStage() + "期未录还款\r\n");
						} else {
							for (CusContractRepayment r : repsList) {
								pay = pay.add(r.getRepaymentFee() == null ? new BigDecimal(0) : r.getRepaymentFee());
							}
						}
						BigDecimal real = new BigDecimal(0);
						real.add(s.getCapital());
						real.add(s.getInterest());
						real.add(s.getExtraCharges());
						if (pay.doubleValue() < real.doubleValue() && s.getStage() != 0) {
							hql.append(
									"\t\t---当期还款金额可能有问题:应还" + real.doubleValue() + ",实还" + pay.doubleValue() + "\r\n");
						}
					}
				}
				if (a == 1) {
					System.err.println("\t" + cs.getCusName() + "\t" + cs.getCusContractNo() + "\t分期已还，还款未录入.");
					hql.append("\t" + cs.getCusName() + "\t" + cs.getCusContractNo() + "\t分期已还，还款未录入.");
				}
			}
		}

		renderText(hql.toString());
	}

	public void hands() {
		CronDayService.me.start();
		renderJson("{\"xiaomi say\":\"hello word\"}");
	}

}
