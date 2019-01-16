if ($(window).width() > 991) {
	var total = window.innerHeight;
	document.getElementById("title").style.height = total * 0.1 + "px";
	document.getElementById("main").style.height = total * 0.9 + "px";
	title = document.getElementById("title");
	main = document.getElementById("main");

	box02 = document.getElementById("box02");
	box03 = document.getElementById("box03");
	box04 = document.getElementById("box04");
	title_h = title.offsetHeight;
	main_h = main.offsetHeight;

	box02_h = box02.offsetHeight;
	box03_h = box03.offsetHeight;
	box04_h = box04.offsetHeight;
	document.getElementById("box01").style.height = main_h * 0.65 + "px";
	document.getElementById("box8-box").style.height = main_h * 0.3 + "px";
	box02.style.height = main_h * 0.62 + "px";
	document.getElementById("box9-box").style.height = main_h * 0.3 + "px";
	document.getElementById("box9").style.height = main_h * 0.27 + "px";
	box03.style.height = main_h * 0.475 + "px";
	box04.style.height = main_h * 0.475 + "px";
	box01 = document.getElementById("box01");
	box01_h = box01.offsetHeight;
	document.getElementById("total-mn1").style.height = box01_h * 0.02 + "px";
	document.getElementById("total-mn2").style.height = box01_h * 0.02 + "px";
	// document.getElementById("live-box").style.height = box01_h * 0.05 + "px";
	document.getElementById("ym-menu").style.height = box03_h * 0.1 + "px";
};

$(function() {
	// 点击跳转
	$('#chart_map').click(function() {
		window.location.href = '/page';
	});
	$('.t_btn0').click(function() {
		window.location.href = "/page?id=0";
	});
	$('.t_btn1').click(function() {
		window.location.href = "/page?id=1";
	});
	$('.t_btn2').click(function() {
		window.location.href = "/page?id=2";
	});
	$('.t_btn3').click(function() {
		window.location.href = "/page?id=3";
	});
	$('.t_btn4').click(function() {
		window.location.href = "/page?id=4";
	});
	$('.t_btn5').click(function() {
		window.location.href = "/page?id=5";
	});
	$('.t_btn6').click(function() {
		window.location.href = "/page?id=6";
	});
	$('.t_btn7').click(function() {
		window.location.href = "/page?id=7";
	});
	$('.t_btn8').click(function() {
		window.location.href = "/page?id=8";
	});
	$('.t_btn9').click(function() {
		window.location.href = "/page?id=9";
	});
});

var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
	$http({
		method : 'get',
		url : '/boss'
	}).then(
			function(res) {
				$scope.listHead = res.data.listHead; // 数据列表-头
				$scope.listData1 = res.data.listData1; // 数据列表

				$.get("/boss/currentData", {
					prov : ''
				}, function(result) {
					$scope.listContent = result;// 数据列表
					$scope.$apply();
				});

				var today_money = document.getElementById('today_money');
				today_money.innerHTML = res.data.todayMoney;
				var week_money = document.getElementById('week_money');
				week_money.innerHTML = res.data.weekMoney;

				$.get("/boss/use", function(r) {
					// 产品分布
					var worldMapContainer8 = document.getElementById('box8');
					box8_box = document.getElementById("box8-box");
					box8_box_h = box8_box.offsetHeight;
					box8_box_w = box8_box.offsetWidth;
					// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
					var resizeWorldMapContainer8 = function() {
						worldMapContainer8.style.width = box8_box_w * 0.96
								+ 'px';
						worldMapContainer8.style.height = box8_box_h * 0.87
								+ 'px';
					};
					// 设置容器高宽
					resizeWorldMapContainer8();
					// 基于准备好的dom，初始化echarts实例
					var myChart = echarts.init(worldMapContainer8);
					// 指定图表的配置项和数据
					var option = {

						tooltip : {
							trigger : 'item',
							formatter : "{a} <br/>{b} : {c}%"
						},

						legend : {
							data : r.uset1,
							x : 'center',
							y : 'bottom',
							textStyle : {
								color : '#ccc'
							}
						},
						calculable : true,
						series : [ {
							name : '产品',
							type : 'funnel',
							left : '10%',
							top : '5%',
							// x2: 80,
							bottom : '17%',
							width : '80%',
							// height: {totalHeight} - y - y2,
							min : 0,
							max : 100,
							minSize : '0%',
							maxSize : '100%',
							sort : 'descending',
							gap : 2,
							label : {
								normal : {
									show : true,
									position : 'inside'
								},
								emphasis : {
									textStyle : {
										fontSize : 24
									}
								}
							},
							labelLine : {
								normal : {
									length : 10,
									lineStyle : {
										width : 1,
										type : 'solid'
									}
								}
							},
							itemStyle : {
								normal : {
									borderColor : '#ccc',
									borderWidth : 1
								}
							},
							color : [ '#c7353a', '#f5b91e', '#2455d0',
									'#ff7d4e', '#049cfa' ],
							data : function() {
								var serie = [];
								for (var i = 0; i < r.uset1.length; i++) {
									var item = {
										name : r.uset1[i],
										value : r.used1[i]
									};
									serie.push(item);
								}
								return serie;
							}()

						} ]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);

					// 用于使chart自适应高度和宽度
					window.onresize = function() {
						// 重置容器高宽
						resizeWorldMapContainer8();
						myChart.resize();
					};
				});

				$.get("/boss/loan",
						function(r) {
							var worldMapContainer2 = document
									.getElementById('box2');
							var box01 = document.getElementById("box01");
							var box01_h = box01.offsetHeight;
							var box01_w = box01.offsetWidth;
							// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
							var resizeWorldMapContainer2 = function() {
								worldMapContainer2.style.width = box01_w * 0.96
										+ 'px';
								worldMapContainer2.style.height = box01_h
										* 0.38 + 'px';
							};
							// 设置容器高宽
							resizeWorldMapContainer2();
							// 基于准备好的dom，初始化echarts实例
							var myChart = echarts.init(worldMapContainer2);
							var option = {
								color : [ '#38b3f1' ],
								tooltip : {
									trigger : 'axis',
									axisPointer : { // 坐标轴指示器，坐标轴触发有效
										type : 'shadow' // 默认为直线，可选为：'line' |
									// 'shadow'
									}
								},
								textStyle : {
									color : '#ccc'
								},
								grid : {
									top : '10%',
									left : '3%',
									right : '3%',
									bottom : '6%',
									containLabel : true
								},
								xAxis : [ {
									type : 'category',
									data : r.t,
									axisTick : {
										alignWithLabel : true
									}
								} ],
								yAxis : [ {
									type : 'value'
								} ],
								series : [ {
									name : '金额',
									type : 'bar',
									barWidth : '60%',
									data : r.d
								} ]
							};
							myChart.setOption(option);

							// 用于使chart自适应高度和宽度
							window.onresize = function() {
								// 重置容器高宽
								resizeWorldMapContainer2();
								myChart.resize();
							};
						});

				$.get("/boss/loanNum",
						function(r) {
							var worldMapContainer4 = document
									.getElementById('box4');
							// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
							var box01 = document.getElementById("box01");
							var box01_h = box01.offsetHeight;
							var box01_w = box01.offsetWidth;
							var resizeWorldMapContainer4 = function() {
								worldMapContainer4.style.width = box01_w * 0.90
										+ 'px';
								worldMapContainer4.style.height = box01_h
										* 0.45 + 'px';
							};
							// 设置容器高宽
							resizeWorldMapContainer4();
							// 基于准备好的dom，初始化echarts实例
							var myChart = echarts.init(worldMapContainer4);
							// 指定图表的配置项和数据

							var option = {
								tooltip : {
									trigger : 'item',
									formatter : "{a} <br/>{b} : {c} ({d}%)"
								},
								legend : {
									textStyle : {
										color : '#ccc'
									},
									orient : 'vertical',
									left : 'left',
									data : r.t2
								},
								series : [ {
									color : [ '#7627cb', '#259fd2', '#e26021',
										'#c7353a', '#f5b91e' ],
									name : '成交额',
									type : 'pie',
									radius : '55%',
									center : [ '50%', '60%' ],
									data :function() {
										var serie = [];
										for (var i = 0; i < r.t2.length; i++) {
											var item = {
												name : r.t2[i],
												value : r.d2[i]
											};
											serie.push(item);
										}
										return serie;
									}()
									,
									itemStyle : {
										emphasis : {
											shadowBlur : 10,
											shadowOffsetX : 0,
											shadowColor : 'rgba(0, 0, 0, 0.5)'
										}
									}
								} ]
							};

							// 使用刚指定的配置项和数据显示图表。
							myChart.setOption(option);

							// 用于使chart自适应高度和宽度
							window.onresize = function() {
								// 重置容器高宽
								resizeWorldMapContainer4();
								myChart.resize();
							};
						});

				// 剩余本金
				$.get("/boss/spules",
						function(r) {
							var worldMapContainer5 = document
									.getElementById('box5');
							box04 = document.getElementById("box04");
							box04_h = box04.offsetHeight;
							box04_w = box04.offsetWidth;
							// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
							var resizeWorldMapContainer5 = function() {
								worldMapContainer5.style.width = box04_w * 0.96
										+ 'px';
								worldMapContainer5.style.height = box04_h
										* 0.96 + 'px';
							};
							// 设置容器高宽
							resizeWorldMapContainer5();
							// 基于准备好的dom，初始化echarts实例
							var myChart = echarts.init(worldMapContainer5);

							// 指定图表的配置项和数据

							var option = {
								// title : {
								// text: '某地区蒸发量和降水量',
								// subtext: '纯属虚构'
								// },
								color : [ '#38b5f4', '#ff7d4e' ],
								tooltip : {
									trigger : 'axis',

								},
								legend : {
									data : r.l5,
									textStyle : {
										color : '#ccc'
									}
								},
								grid : {
									top : '10%',
									left : '3%',
									right : '3%',
									bottom : '6%',
									containLabel : true
								},
								calculable : true,
								textStyle : {
									color : '#ccc'
								},
								xAxis : [ {
									type : 'category',
									data : r.t5,
								} ],
								yAxis : [ {
									type : 'value'
								} ],
								series : function() {
									var serie = [];
									for (var i = 0; i < r.l5.length; i++) {
										var item = {
											name : r.l5[i],
											type : 'bar',
											data : r.d5[i]
										};
										serie.push(item);
									}
									return serie;
								}()

							};

							// 使用刚指定的配置项和数据显示图表。
							myChart.setOption(option);

							// 用于使chart自适应高度和宽度
							window.onresize = function() {
								// 重置容器高宽
								resizeWorldMapContainer5();
								myChart.resize();
							};
						});

				// 趋势图
				$.get("/boss/lateLine",
						function(r) {

							var worldMapContainer = document
									.getElementById('boxes3');
							box03 = document.getElementById("box03");
							box03_h = box03.offsetHeight;
							box03_w = box04.offsetWidth;
							// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
							var resizeWorldMapContainer = function() {
								worldMapContainer.style.width = box03_w * 1
										+ 'px';
								worldMapContainer.style.height = box03_h * 0.8
										+ 'px';
							};
							// 设置容器高宽
							resizeWorldMapContainer();
							// 基于准备好的dom，初始化echarts实例
							var myChart = echarts.init(worldMapContainer);
							var option = {
								textStyle : {
									color : '#ccc'
								},
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									data : [ 'M1', 'M2', 'M3', 'M4', 'M5',
											'M6', 'M7' ],
									textStyle : {
										color : '#ccc'
									},
									color : [ '#7627cb', '#259fd2', '#e26021',
											'#c7353a', '#f5b91e', '#76sd82',
											'#d343d3' ]
								},
								grid : {
									left : '3%',
									right : '4%',
									bottom : '3%',
									containLabel : true
								},
								toolbox : {
									feature : {
										saveAsImage : {}
									}
								},
								xAxis : {
									type : 'category',
									boundaryGap : false,
									data : r.data
								},
								yAxis : {
									type : 'value'
								},
								series : [ {
									name : 'M1',
									type : 'line',
									stack : '总量',
									data : r.data1
								}, {
									name : 'M2',
									type : 'line',
									stack : '总量',
									data : r.data2
								}, {
									name : 'M3',
									type : 'line',
									stack : '总量',
									data : r.data3
								}, {
									name : 'M4',
									type : 'line',
									stack : '总量',
									data : r.data4
								}, {
									name : 'M5',
									type : 'line',
									stack : '总量',
									data : r.data5
								}, {
									name : 'M6',
									type : 'line',
									stack : '总量',
									data : r.data6
								}, {
									name : 'M7',
									type : 'line',
									stack : '总量',
									data : r.data7
								} ]
							};

							// 使用刚指定的配置项和数据显示图表。
							myChart.setOption(option);

							// 用于使chart自适应高度和宽度
							window.onresize = function() {
								// 重置容器高宽
								resizeWorldMapContainer();
								myChart.resize();
							};

						});

				// 拼图
				$.get("/boss/late", function(r) {
					// 资产占比和资金占比
					var worldMapContainer = document.getElementById('box3');
					box03 = document.getElementById("box03");
					box03_h = box03.offsetHeight;
					box03_w = box04.offsetWidth;
					// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
					var resizeWorldMapContainer = function() {
						worldMapContainer.style.width = box03_w * 1 + 'px';
						worldMapContainer.style.height = box03_h * 0.8 + 'px';
					};
					// 设置容器高宽
					resizeWorldMapContainer();
					// 基于准备好的dom，初始化echarts实例
					var myChart = echarts.init(worldMapContainer);

					// 指定图表的配置项和数据
					var option = {
						tooltip : {
							trigger : 'item',
							formatter : "{a} <br/>{b}: {c} ({d}%)"
						},
						grid : {
							height : '40%',
							y : '5%',
							x : '14%'
						},
						legend : {
							x : 'center',
							y : 'bottom',
							textStyle : {
								color : '#ccc'
							},
							data : r.l3
						},
						series : [
								{
									color : [ '#7627cb', '#259fd2', '#e26021',
											'#c7353a', '#f5b91e' ],
									name : '逾期金额',
									type : 'pie',
									selectedMode : 'single',
									radius : '40%',
									center : [ '50%', '40%' ],

									label : {
										normal : {
											position : 'inner'
										}
									},
									labelLine : {
										normal : {
											show : true
										}
									},
									data : function() {
										var serie = [];
										for (var i = 0; i < r.t3.length; i++) {
											var item = {
												name : r.t3[i],
												value : r.d3[i]
											};
											serie.push(item);
										}
										return serie;
									}()

								},
								{
									name : '逾期份数',
									type : 'pie',
									center : [ '50%', '40%' ],
									radius : [ '50%', '65%' ],
									color : [ '#d9a503', '#2551bb', '#81b740',
											'#da70d6', '#ff7f50' ],
									data : function() {
										var serie = [];
										for (var i = 0; i < r.t3.length; i++) {
											var item = {
												name : r.t3[i],
												value : r.d13[i]
											};
											serie.push(item);
										}
										return serie;
									}()

								} ]
					};

					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);

					// 用于使chart自适应高度和宽度
					window.onresize = function() {
						// 重置容器高宽
						resizeWorldMapContainer();
						myChart.resize();
					};
				});

				// 地图
				$.get("/boss/mapData",
						function(r) {

							// 业务数据分布
							var worldMapContainer1 = document
									.getElementById('box1');
							box02 = document.getElementById("box02");
							box02_h = box02.offsetHeight;
							box02_w = box02.offsetWidth;
							// 用于使chart自适应高度和宽度,通过窗体高宽计算容器高宽
							var resizeWorldMapContainer1 = function() {
								worldMapContainer1.style.width = box02_w * 0.9
										+ 'px';
								worldMapContainer1.style.height = box02_h
										* 0.82 + 'px';
							};
							// 设置容器高宽
							resizeWorldMapContainer1();
							// 基于准备好的dom，初始化echarts实例
							var myChart = echarts.init(worldMapContainer1);
							// 指定图表的配置项和数据
							function randomData() {
								return Math.round(Math.random() * 3000);
							}
							var option = {
								tooltip : {
									trigger : 'item'
								},
								legend : {
									orient : 'vertical',
									x : 'left',
									y : 'bottom',
									data : [ '成交额', '进件额' ],
									textStyle : {
										color : '#ccc'
									}
								},
								visualMap : {
									min : 0,
									max : 100000,
									left : 'right',
									top : 'bottom',
									text : [ '高', '低' ], // 文本，默认为数值文本
									calculable : true,
									// color: ['#26cfe4', '#f2b600', '#ec5845'],
									textStyle : {
										color : '#fff'
									}
								},
								series : [ {
									name : '成交额',
									type : 'map',
									aspectScale : 0.75,
									zoom : 1.2,
									mapType : 'china',
									roam : false,
									label : {
										normal : {
											show : false
										},
										emphasis : {
											show : false
										}
									},
									data : function() {
										var serie = [];
										for (var i = 0; i < r.t7.length; i++) {
											var item = {
												name : r.t7[i],
												value : r.d7[i]
											};
											serie.push(item);
										}
										return serie;
									}()

								}, {
									name : '进件额',
									type : 'map',
									mapType : 'china',
									label : {
										normal : {
											show : true
										},
										emphasis : {
											show : true
										}
									},
									data : function() {
										var serie = [];
										for (var i = 0; i < r.t7.length; i++) {
											var item = {
												name : r.t7[i],
												value : r.d8[i]
											};
											serie.push(item);
										}
										return serie;
									}()

								} ]
							};

							// 使用刚指定的配置项和数据显示图表。
							myChart.setOption(option);

							// 用于使chart自适应高度和宽度
							window.onresize = function() {
								// 重置容器高宽
								resizeWorldMapContainer1();
								myChart.resize();
							};

							myChart.on('click', function(params) {
								// 控制台打印数据的名称
								$.get("/boss/currentData", {
									prov : params.name
								}, function(result) {
									$scope.listContent = result;
									$scope.$apply();
								});

							});

						});

			});
});