package com.jyd.unit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jyd.common.model.CusContractRepayment;

public class ActiveRecordTest {
	private static String url = "jdbc:mysql://192.168.50.5/bms20181119?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	private static String aliyunUrl = "jdbc:mysql://192.168.50.5/bmsover20181211?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";

	public static void main(String[] args) throws IOException {
		// 事物
		Db.tx(() -> {
			Db.update("update t1 set f1 = ?", 123);
			Db.update("update t2 set f2 = ?", 456);
			return true;
		});

		DruidPlugin dp = new DruidPlugin(url, "root", "welcome");
		ActiveRecordPlugin arp = new ActiveRecordPlugin("gan", dp);
		arp.addMapping("cus_contract_repayment", CusContractRepayment.class);
		dp.start();
		arp.start();

		DruidPlugin aliyunDP = new DruidPlugin(aliyunUrl, "root", "welcome");
		ActiveRecordPlugin aliyunArp = new ActiveRecordPlugin("aliyun", aliyunDP);
		aliyunArp.addMapping("cus_contract_repayment", CusContractRepayment.class);
		aliyunDP.start();
		aliyunArp.start();

		CusContractRepayment gan = new CusContractRepayment().dao().use("gan");
		CusContractRepayment aliyun = new CusContractRepayment().dao().use("aliyun");
		// 通过上面简单的几行代码，即可立即开始使用
		StringBuilder str = new StringBuilder();
		List<CusContractRepayment> ganList = gan.find("select * from cus_contract_repayment");
		for (CusContractRepayment ganRepayment : ganList) {
			CusContractRepayment aliRepayment = aliyun.findById(ganRepayment.getId());
			if (aliRepayment != null && ganRepayment != null) {
				if (aliRepayment.getLessStill().intValue() != 0) {
					if (ganRepayment.getUpdateDate() == null) {
						if (ganRepayment.getUpdateDate() == aliRepayment.getUpdateDate()) {
							str.append("UPDATE `cus_contract_repayment` SET `less_still`='0' WHERE `id`="
									+ aliRepayment.getUpdateDate() + "; -- " + ganRepayment.getUpdateDate() + " "
									+ aliRepayment.getLessStill() + "\n");
							// ganRepayment.setLessStill(new BigDecimal("0")).update();
						}
					} else {
						if (ganRepayment.getUpdateDate().equals(aliRepayment.getUpdateDate())) {
							str.append("UPDATE `cus_contract_repayment` SET `less_still`='0' WHERE `id`="
									+ aliRepayment.getId() + "; -- " + aliRepayment.getUpdateDate() + " "
									+ ganRepayment.getUpdateDate() + "\n");
							// ganRepayment.setLessStill(new BigDecimal("0")).update();
						}
					}
				}

			}

		}
		File file = new File("/home/mjy/Desktop/1.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter writer = new FileWriter(file);
		writer.write(str.toString());
		writer.flush();
		writer.close();
	}
}
