package com.jyd.task;

import java.util.TimerTask;

import com.jyd.service.CronDayService;

/**
 * 合同日结作业
 * 
 * @author mjy
 *
 */
public class CronDay extends TimerTask {
	public void run() {
		CronDayService.me.start();
	}
}
