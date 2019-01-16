package com.jyd.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;

public class DemoController extends Controller {
	public void index() {
		render("/demo/demo.html");
	}

	public void getdata() {
		int data = Integer.parseInt(getPara("data"));
		DemoInfo demoInfo = new DemoInfo();
		demoInfo.key = data;

		demoInfo.Columns = new ArrayList<String>();
		demoInfo.Datas = new ArrayList<Integer>();

		demoInfo.Columns.add("Mon");
		demoInfo.Columns.add("Tue");
		demoInfo.Columns.add("Wed");
		demoInfo.Columns.add("Thu");
		demoInfo.Columns.add("Fri");
		demoInfo.Columns.add("Sat");
		demoInfo.Columns.add("Sun");

		demoInfo.Datas.add(820);
		demoInfo.Datas.add(932);
		demoInfo.Datas.add(901);
		demoInfo.Datas.add(934);
		demoInfo.Datas.add(1290);
		demoInfo.Datas.add(1330);
		demoInfo.Datas.add(1320);
		renderJson(demoInfo);
	}

	public void getdata2() {
		int data = Integer.parseInt(getPara("data"));
		DemoInfo demoInfo = new DemoInfo();
		demoInfo.key = data + 100;

		demoInfo.Columns = new ArrayList<String>();
		demoInfo.Datas = new ArrayList<Integer>();

		demoInfo.Columns.add("Mon");
		demoInfo.Columns.add("Tue");
		demoInfo.Columns.add("Wed");
		demoInfo.Columns.add("Thu");
		demoInfo.Columns.add("Fri");
		demoInfo.Columns.add("Sat");
		demoInfo.Columns.add("Sun");

		demoInfo.Datas.add(820);
		demoInfo.Datas.add(932);
		demoInfo.Datas.add(901);
		demoInfo.Datas.add(934);
		demoInfo.Datas.add(1290);
		demoInfo.Datas.add(1330);
		demoInfo.Datas.add(1320);
		renderJson(demoInfo);
	}
	
	public class DemoInfo {
		private int key;
		private List<String> Columns;
		private List<Integer> Datas;

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public List<String> getColumns() {
			return Columns;
		}

		public void setColumns(List<String> columns) {
			Columns = columns;
		}

		public List<Integer> getDatas() {
			return Datas;
		}

		public void setDatas(List<Integer> datas) {
			Datas = datas;
		}

	}
}
