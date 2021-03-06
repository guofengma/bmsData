﻿//时趋line
incoming_lineChart = {
		title:null,
		storeName:null,
		typeNames:null,
		url:null,
		startDate:null,
		endDate:null,
		data:null,
		keys:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.title = obj.title;
			this.storeName = obj.storeName;
			this.typeNames = obj.typeNames;
			this.url = obj.url;
			this.startDate = obj.startDate;
			this.endDate = obj.endDate;
			this.keys = obj.keys;
			this.data = {"type":obj.type, "value":obj.value, "begin":obj.startDate, "end":obj.endDate};
		
			this.div = div;
			this.chart = echarts.init(this.div);
			var lineChartObject = this;
			this.option =  {
					    title: {
					        text: '',
					       // x: 'center',
				            textStyle: { color: '#008acd' }
					    },
					    tooltip: {
				        	 trigger: 'axis',
				             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				                 type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
				             }
				        },
					    legend: {
					        data:[]
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    toolbox: {
				            show: true,
				            feature: {
				                mark: { show: true },
				                dataView: { show: true, readOnly: false },
				                magicType: { show: true, type: ['line', 'bar'] },
				                restore: { show: true },
				                saveAsImage: { show: true }
				            }
				        },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: []
					    },
					    yAxis: {
					        type: 'value'
					    },
					    dataZoom: [
					        {   // 这个dataZoom组件，默认控制x轴。
					            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
					            start: 0,      // 左边在 0% 的位置。
					            end: 35         // 右边在 35% 的位置。
					        }
					    ],
					    series: []
					};	
					
			incoming_lineChart.interval(lineChartObject);
// 					for(var i = 0; i < typeNames.length; i++){
// 						$.post(urls[i], data, function(response){
// 			 				console.log(response);
			 				
// 			 				lineChart.process(lineChart, response, startDate, endDate, typeName);
// 						});
// 					}
					
				},
				interval:function(lineChartObject){
					var i=0;
					ref = setInterval(function(){
						lineChartObject.data.key = lineChartObject.keys[i];
						lineChartObject.invokingAjax(lineChartObject, lineChartObject.url, lineChartObject.data, lineChartObject.typeNames[i], lineChartObject.startDate, lineChartObject.endDate, lineChartObject.storeName, lineChartObject.title);
						
						i++;
						if(i > lineChartObject.keys.length - 1){
							clearInterval(ref);
						}
					},1000);
					
// 					for(var i = 0; i<lineChartObject.urls.length; i++){
// 						lineChartObject.invokingAjax(lineChart, lineChart.urls[i], lineChart.data, lineChart.typeNames[i], lineChart.startDate, lineChart.endDate);
// 					}
					
					
				},
				invokingAjax:function (lineChartObject,url, data, typeName, startDate, endDate, storeName, title){
					$.post(url, data, function(response){
						if(response.show_res_code == "0"){
							incoming_lineChart.process(lineChartObject,response, startDate, endDate, typeName, storeName, title);
		 				}
		 				if(response.show_res_code == "-1"){
		 					$.messager.alert('提示',response.show_res_error,'info');
		 				}
					});

				},
				process:function (lineChartObject,response, startDate, endDate, typeName, storeName, title){	

					lineChartObject.option.title.text = "\n"+startDate+"至"+endDate+storeName+title;
					lineChartObject.option.xAxis.data = response.show_res_body.month;
					lineChartObject.option.legend.data.push(typeName);
					
					var obj = {
							name : typeName,
							type : 'line',
							label : {
								show : true,
								position : 'top',
								//formatter : '{b}\n{c}'
							},
							data : response.show_res_body.data
						};
					lineChartObject.option.series.push(obj);
// 					Array.prototype.push.apply(chartObject.option.xAxis.data, json.columns);
// 					Array.prototype.push.apply(chartObject.option.series[0].data,json.datas);
					lineChartObject.view();
				},
				view:function(){
					this.chart.setOption(this.option, true);
				},
				resize:function(){
					if(this.chart != null){
						this.chart.resize();
					}
				},
				getChart:function(){
					if(this.chart != null){
						return this.chart;
					}else{
						return null;
					}
				}
			};
incoming_line2Chart = {
		title:null,
		storeName:null,
		typeNames:null,
		url:null,
		startDate:null,
		endDate:null,
		data:null,
		keys:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.title = obj.title;
			this.storeName = obj.storeName;
			this.typeNames = obj.typeNames;
			this.url = obj.url;
			this.startDate = obj.startDate;
			this.endDate = obj.endDate;
			this.keys = obj.keys;
			this.data = {"type":obj.type, "value":obj.value, "begin":obj.startDate, "end":obj.endDate};
		
			this.div = div;
			this.chart = echarts.init(this.div);
			var lineChartObject = this;
			this.option =  {
					    title: {
					        text: '',
					       // x: 'center',
				            textStyle: { color: '#008acd' }
					    },
					    tooltip: {
				        	 trigger: 'axis',
				             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				                 type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
				             }
				        },
					    legend: {
					        data:[]
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    toolbox: {
				            show: true,
				            feature: {
				                mark: { show: true },
				                dataView: { show: true, readOnly: false },
				                magicType: { show: true, type: ['line', 'bar'] },
				                restore: { show: true },
				                saveAsImage: { show: true }
				            }
				        },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: []
					    },
					    yAxis: {
					        type: 'value'
					    },
					    dataZoom: [
					        {   // 这个dataZoom组件，默认控制x轴。
					            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
					            start: 0,      // 左边在 0% 的位置。
					            end: 35         // 右边在 35% 的位置。
					        }
					    ],
					    series: []
					};	
					
			incoming_line2Chart.interval(lineChartObject);
// 					for(var i = 0; i < typeNames.length; i++){
// 						$.post(urls[i], data, function(response){
// 			 				console.log(response);
			 				
// 			 				lineChart.process(lineChart, response, startDate, endDate, typeName);
// 						});
// 					}
					
				},
				interval:function(lineChartObject){
					var i=0;
					ref = setInterval(function(){
						lineChartObject.data.key = lineChartObject.keys[i];
						lineChartObject.invokingAjax(lineChartObject, lineChartObject.url, lineChartObject.data, lineChartObject.typeNames[i], lineChartObject.startDate, lineChartObject.endDate, lineChartObject.storeName, lineChartObject.title);
						
						i++;
						if(i > lineChartObject.keys.length - 2){
							clearInterval(ref);
						}
					},1000);
					
// 					for(var i = 0; i<lineChartObject.urls.length; i++){
// 						lineChartObject.invokingAjax(lineChart, lineChart.urls[i], lineChart.data, lineChart.typeNames[i], lineChart.startDate, lineChart.endDate);
// 					}
					
					
				},
				invokingAjax:function (lineChartObject,url, data, typeName, startDate, endDate, storeName, title){
					$.post(url, data, function(response){
						if(response.show_res_code == "0"){
							incoming_line2Chart.process(lineChartObject,response, startDate, endDate, typeName, storeName, title);
		 				}
		 				if(response.show_res_code == "-1"){
		 					$.messager.alert('提示',response.show_res_error,'info');
		 				}
					});

				},
				process:function (lineChartObject,response, startDate, endDate, typeName, storeName, title){	

					lineChartObject.option.title.text = "\n"+startDate+"至"+endDate+storeName+title;
					lineChartObject.option.xAxis.data = response.show_res_body.month;
					lineChartObject.option.legend.data.push(typeName);
					
					var obj = {
							name : typeName,
							type : 'line',
							label : {
								show : true,
								position : 'top',
								//formatter : '{b}\n{c}'
							},
							data : response.show_res_body.data
						};
					lineChartObject.option.series.push(obj);
// 					Array.prototype.push.apply(chartObject.option.xAxis.data, json.columns);
// 					Array.prototype.push.apply(chartObject.option.series[0].data,json.datas);
					
					//件均值
					if(lineChartObject.option.series.length == lineChartObject.keys.length -1){
						var chart1 = incoming_lineChart.getChart();
						if(chart1 != null){
							var o1 = chart1.getOption();
							var arr1 =  o1.series[0].data;
							var arr2 = lineChartObject.option.series[1].data;
							if(arr1.length == arr2.length){
								var averArr = new Array();
								for(var i=0; i<arr1.length; i++){
									if(arr1[0] != 0){
										averArr.push(parseFloat(arr2[i]/arr1[i]).toFixed(2));
									}else{
										averArr.push(0);
									}
								}
								lineChartObject.option.legend.data.push(lineChartObject.typeNames[3]);
								var obj = {
										name : lineChartObject.typeNames[3],
										type : 'line',
										label : {
											show : true,
											position : 'top',
											//formatter : '{b}\n{c}'
										},
										data : averArr
									};
								lineChartObject.option.series.push(obj);
							}
						}
					}
					
					lineChartObject.view();
				},
				view:function(){
					this.chart.setOption(this.option, true);
				},
				resize:function(){
					if(this.chart != null){
						this.chart.resize();
					}
				},
				getChart:function(){
					if(this.chart != null){
						return this.chart;
					}else{
						return null;
					}
				}
			};
//当天bar
incoming_barChart = {
		title:null,
		storeName:null,
		typeNames:null,
		data:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.title = obj.title;
			this.storeName = obj.storeName;
			this.typeNames = obj.typeNames;
			this.data = obj.data;
		
			this.div = div;
			this.chart = echarts.init(this.div);
			var barChartObject = this;
			this.option =  {
					 title: {
				            text: "",
				            // subtext: '纯属虚构',
				           // x: 'center',
				            textStyle: { color: '#008acd' }
				        },
				        tooltip: {
				        	 trigger: 'axis',
				             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				                 type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
				             }
				        },
				        legend: {
				            show: false,
				            data: []
				        },
				        toolbox: {
				            show: true,
				            feature: {
				                mark: { show: true },
				                dataView: { show: true, readOnly: false },
				                magicType: { show: true, type: ['line', 'bar'] },
				                restore: { show: true },
				                saveAsImage: { show: true }
				            }
				        },
				        calculable: true,
				        xAxis: [{
				            splitLine: true,
				            type: 'category',
				            data: []
				        }],
				        yAxis: [{
				            splitLine: true,
				            type: 'value',
				            //splitArea : {show : true}
				            show: true
				        }],
				        series: [{
//					            name:'未结清合同',
				            type: 'bar',
				            itemStyle: {
				                normal: {
				                    color: function(params) {
				                        // build a color map as your need.
				                        var colorList = [
				                            '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
				                            '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
				                            '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
				                            '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
				                        ];
				                        return colorList[params.dataIndex]
				                    },
				                    label: {
				                        show: true,
				                        position: 'top',
				                        //formatter: '{b}\n{c}'
				                    }
				                }
				            },
				            data: []
				        }]
					};
			
			incoming_barChart.process(barChartObject);
				},
				process:function (barChartObject){	
					var storeName = barChartObject.storeName;
					var typeNames = barChartObject.typeNames;
					var data = barChartObject.data;
					var title = barChartObject.title;

					barChartObject.option.title.text = storeName+title;
					barChartObject.option.xAxis[0].data = typeNames;
					barChartObject.option.series[0].data = data;
					
					barChartObject.view();
				},
				view:function(){
					this.chart.setOption(this.option, true);
				},
				resize:function(){
					if(this.chart != null){
						this.chart.resize();
					}
				}
				
			};
incoming_bar2Chart = {
		title:null,
		storeName:null,
		typeNames:null,
		data:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.title = obj.title;
			this.storeName = obj.storeName;
			this.typeNames = obj.typeNames;
			this.data = obj.data;
			
			this.div = div;
			this.chart = echarts.init(this.div);
			var barChartObject = this;
			this.option =  {
					title: {
						text: "",
						// subtext: '纯属虚构',
						// x: 'center',
						textStyle: { color: '#008acd' }
					},
					tooltip: {
						trigger: 'axis',
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					legend: {
						show: false,
						data: []
					},
					toolbox: {
						show: true,
						feature: {
							mark: { show: true },
							dataView: { show: true, readOnly: false },
							magicType: { show: true, type: ['line', 'bar'] },
							restore: { show: true },
							saveAsImage: { show: true }
						}
					},
					calculable: true,
					xAxis: [{
						splitLine: true,
						type: 'category',
						data: []
					}],
					yAxis: [{
						splitLine: true,
						type: 'value',
						//splitArea : {show : true}
						show: true
					}],
					series: [{
//					            name:'未结清合同',
						type: 'bar',
						itemStyle: {
							normal: {
								color: function(params) {
									// build a color map as your need.
									var colorList = [
										'#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
										'#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
										'#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
										'#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
										];
									return colorList[params.dataIndex]
								},
								label: {
									show: true,
									position: 'top',
									//formatter: '{b}\n{c}'
								}
							}
						},
						data: []
					}]
			};
			
			incoming_bar2Chart.process(barChartObject);
		},
		process:function (barChartObject){	
			var title = barChartObject.title;
			var storeName = barChartObject.storeName;
			var typeNames = barChartObject.typeNames;
			var data = barChartObject.data;
			
			barChartObject.option.title.text = storeName+title;
			barChartObject.option.xAxis[0].data = typeNames;
			barChartObject.option.series[0].data = data;
			
			barChartObject.view();
		},
		view:function(){
			this.chart.setOption(this.option, true);
		},
		resize:function(){
			if(this.chart != null){
				this.chart.resize();
			}
		}
		
};

////////////////////
entry_contract_num_barChart = {
		storeName:null,
		xAxisData:null,
		seriesData:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.storeName = obj.storeName;
			this.xAxisData = obj.xAxisData;
			this.seriesData = obj.seriesData;
			
			this.div = div;
			this.chart = echarts.init(this.div);
			var barChartObject = this;
			this.option =  {
					title: {
						text: "",
						// subtext: '纯属虚构',
						// x: 'center',
						textStyle: { color: '#008acd' }
					},
					color: ['#3398DB'],
					tooltip : {
						trigger: 'axis',
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					xAxis : [
						{
							type : 'category',
							data : [],
							axisTick: {
								alignWithLabel: true
							},
							axisLabel: {
	                            show: true,
	                            textStyle: {
	                                color: '#fff'
	                            }
		                    }
						}
						],
					yAxis : [
						{
							type : 'value',
							 axisLabel : {
		                            formatter: '{value}',
		                            textStyle: {
		                                color: '#fff'
		                            }
		                        }
						}
						],
					series : [
						{
//					            name:'直接访问',
							type:'bar',
							barWidth: '60%',
							data:[]
						}
						]
			};
			
			entry_contract_num_barChart.process(barChartObject);
		},
		process:function (barChartObject){	
			var storeName = barChartObject.storeName;
			var xAxisData = barChartObject.xAxisData;
			var seriesData = barChartObject.seriesData;
			
			barChartObject.option.title.text = storeName;
			barChartObject.option.xAxis[0].data = xAxisData;
			barChartObject.option.series[0].data = seriesData;
			
			barChartObject.view();
		},
		view:function(){
			this.chart.setOption(this.option, true);
		},
		resize:function(){
			if(this.chart != null){
				this.chart.resize();
			}
		}
		
};
pass_contract_num_barChart = {
		storeName:null,
		xAxisData:null,
		seriesData:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.storeName = obj.storeName;
			this.xAxisData = obj.xAxisData;
			this.seriesData = obj.seriesData;
			
			this.div = div;
			this.chart = echarts.init(this.div);
			var barChartObject = this;
			this.option =  {
					title: {
						text: "",
						// subtext: '纯属虚构',
						// x: 'center',
						textStyle: { color: '#008acd' }
					},
					color: ['#3398DB'],
					tooltip : {
						trigger: 'axis',
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					xAxis : [
						{
							type : 'category',
							data : [],
							axisTick: {
								alignWithLabel: true
							},
							axisLabel: {
	                            show: true,
	                            textStyle: {
	                                color: '#fff'
	                            }
		                    }
						}
						],
					yAxis : [
						{
							type : 'value',
							 axisLabel : {
		                            formatter: '{value}',
		                            textStyle: {
		                                color: '#fff'
		                            }
		                        }
						}
						],
					series : [
						{
//					            name:'直接访问',
							type:'bar',
							barWidth: '60%',
							data:[]
						}
						]
			};
			
			pass_contract_num_barChart.process(barChartObject);
		},
		process:function (barChartObject){	
			var storeName = barChartObject.storeName;
			var xAxisData = barChartObject.xAxisData;
			var seriesData = barChartObject.seriesData;
			
			barChartObject.option.title.text = storeName;
			barChartObject.option.xAxis[0].data = xAxisData;
			barChartObject.option.series[0].data = seriesData;
			
			barChartObject.view();
		},
		view:function(){
			this.chart.setOption(this.option, true);
		},
		resize:function(){
			if(this.chart != null){
				this.chart.resize();
			}
		}
		
};
incoming_amountAndPrincipal_barChart = {
		storeName:null,
		xAxisData:null,
		series:null,
		legendData:null,
		
		option:null,
		div:null,
		chart:null,
		initData:function(div,obj){
			this.storeName = obj.storeName;
			this.xAxisData = obj.xAxisData;
			this.legendData = obj.legendData;
			this.series = obj.series;
			
			this.div = div;
			this.chart = echarts.init(this.div);
			var barChartObject = this;
			this.option =  {
					title: {
						text: "",
						// subtext: '纯属虚构',
						// x: 'center',
						textStyle: { color: '#008acd' }
					},
					color: ['#3398DB', '#F0805A', '#546570','#ca8622'],
					tooltip : {
						trigger: 'axis',
						axisPointer : {            // 坐标轴指示器，坐标轴触发有效
							type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						}
					},
					grid: {
						left: '3%',
						right: '4%',
						bottom: '3%',
						containLabel: true
					},
					legend: {
						backgroundColor:'#afa',
				        data:[]
				    },
				    xAxis : [
						{
							type : 'category',
							data : [],
							axisTick: {
								alignWithLabel: true
							},
							axisLabel: {
	                            show: true,
	                            textStyle: {
	                                color: '#fff'
	                            }
		                    }
						}
						],
					yAxis : [
						{
							type : 'value',
							 axisLabel : {
		                            formatter: '{value}',
		                            textStyle: {
		                                color: '#fff'
		                            }
		                        }
						}
						],
				series : []
			};
			
			incoming_amountAndPrincipal_barChart.process(barChartObject);
		},
		process:function (barChartObject){	
			var storeName = barChartObject.storeName;
			var xAxisData = barChartObject.xAxisData;
			var legendData = barChartObject.legendData;
			var series = barChartObject.series;
			
			barChartObject.option.title.text = storeName;
			barChartObject.option.xAxis[0].data = xAxisData;
			barChartObject.option.legend.data = legendData;
			barChartObject.option.series = series;
			
			barChartObject.view();
		},
		view:function(){
			this.chart.setOption(this.option, true);
		},
		resize:function(){
			if(this.chart != null){
				this.chart.resize();
			}
		}
		
};
