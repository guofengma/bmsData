package com.jyd.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.jyd.common.model._MappingKit;
import com.jyd.controller.IndexController;
import com.jyd.controller.api.IndexApiController;
import com.jyd.routes.TypeRouters;
import com.jyd.task.CronDay;

public class Config extends JFinalConfig {

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/", 5);
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/api", IndexApiController.class);// API JOSN
		me.add(new TypeRouters());// 各大数据类型总路由
	}

	public void configEngine(Engine me) {
		me.addSharedFunction("/common/_layout.html");
		me.addSharedFunction("/common/_paginate.html");
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		Cron4jPlugin cp = new Cron4jPlugin();
		cp.addTask("00 23 * * *", new CronDay());
		me.add(cp);
		// 配置 druid 数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.user"),
				PropKit.get("jdbc.password").trim());
		me.add(druidPlugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.user"), PropKit.get("jdbc.password").trim());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
	}
}
