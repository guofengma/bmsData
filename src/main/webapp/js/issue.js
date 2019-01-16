/* 
*/

$(function(){function show(){var mydate = new Date(); var str = "" + mydate.getFullYear() + "-"; str += (mydate.getMonth()+1); return str; } $(".Years").text(show()); });


var myChart2 = echarts.init(document.getElementById('Canvas2'));
option2 = {
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        // data:['Step Start', 'Step Middle', 'Step End']
        data:['出单件数', '出单人数']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        data: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'出单件数',
            type:'line',
            step: 'start',
            data:[220, 282, 201, 234, 290, 430, 410]
           
        },
        {
            name:'出单人数',
            type:'line',
            step: 'middle',
             data:[57, 50, 60, 52, 59, 53, 58]
        }
        
    ]
};
myChart2.setOption(option2);

