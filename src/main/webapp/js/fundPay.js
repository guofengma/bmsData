fundPay_total_amount_barChart = {
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
			
				fundPay_total_amount_barChart.process(barChartObject);
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

principal_interest_barChart = {
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
					            axisLabel : {
		                            formatter: '{value}',
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
			
			principal_interest_barChart.process(barChartObject);
		},
		process:function (barChartObject){	
			var storeName = barChartObject.storeName;
			var xAxisData = barChartObject.xAxisData;
			var series = barChartObject.series;
			
			barChartObject.option.title.text = storeName;
			barChartObject.option.xAxis[0].data = xAxisData;
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