//JSON 数据还原 防止JSON转换异常
var FastJson = {
		isArray : function(a) {
			return "object" == typeof a
			&& "[object array]" == Object.prototype.toString.call(a)
			.toLowerCase();
		},
		isObject : function(a) {
			return "object" == typeof a
			&& "[object object]" == Object.prototype.toString.call(a)
			.toLowerCase();
		},
		format : function(a) {
			if (null == a)
				return null;
			"string" == typeof a && (a = eval("(" + a + ")"));
			return this._format(a, a, null, null, null);
		},
		_randomId : function() {
			return "randomId_" + parseInt(1E9 * Math.random());
		},
		_getJsonValue : function(a, c) {
			var d = this._randomId(), b;
			b = "" + ("function " + d + "(root){") + ("return root." + c + ";");
			b += "}";
			b += "";
			var e = document.createElement("script");
			e.id = d;
			e.text = b;
			document.body.appendChild(e);
			d = window[d](a);
			e.parentNode.removeChild(e);
			return d;
		},
		_format : function(a, c, d, b, e) {
			d || (d = "");
			if (this.isObject(c)) {
				if (c.$ref) {
					var g = c.$ref;
					0 == g.indexOf("$.")
					&& (b[e] = this._getJsonValue(a, g.substring(2)));
					return
				}
				for ( var f in c)
					b = d, "" != b && (b += "."), g = c[f], b += f, this
					._format(a, g, b, c, f);
			} else if (this.isArray(c))
				for (f in c)
					b = d, g = c[f], b = b + "[" + f + "]", this._format(a, g,
							b, c, f);
			return a;
		}
};

// POST提交
function post(URL, PARAMS) {        
	var temp = document.createElement("form");        
	temp.action = URL;        
	temp.method = "post";        
	temp.style.display = "none";        
	for (var x in PARAMS) {        
		var opt = document.createElement("textarea");        
		opt.name = x;        
		opt.value = PARAMS[x];        
		temp.appendChild(opt);        
	}        
	document.body.appendChild(temp);        
	temp.submit();        
	return temp;        
} 
// 时间转换JS
function getDateTime(data) {
	if(data==undefined){
		return "";
	}
	var d = new Date(data);    // 根据时间戳生成的时间对象
	var date = (d.getFullYear()) + "-" + (d.getMonth() + 1) + "-" +(d.getDate());
	return date;
}

// 具体时间转换 精确到秒
function getDateTimeHours(data) {
	if(data==undefined){
		return "";
	}
	var d = new Date(data);    // 根据时间戳生成的时间对象
	var date = (d.getFullYear()) + "-" + (d.getMonth() + 1) + "-" +(d.getDate()) + " " + (d.getHours()) + ":" + (d.getMinutes())+ ":" +(d.getSeconds());
	return date;
}

// 具体时间 精确到分
function getDateTimeMinute(data) {
	if(data==undefined){
		return "";
	}
	var d = new Date(data);    // 根据时间戳生成的时间对象
	var date = (d.getFullYear()) + "-" + (d.getMonth() + 1) + "-" +(d.getDate()) + " " + (d.getHours()) + ":" + (d.getMinutes());
	return date;
}

// 时间范围筛选
function DatePicker(beginSelector,endSelector){
	// 仅选择日期
	$(beginSelector).datepicker(
			{
				language:  "zh-CN",
				autoclose: true,
				startView: 0,
				format: "yyyy-mm-dd",
				clearBtn:true,
				todayBtn:false,
				endDate:new Date()
			}).on('changeDate', function(ev){
				if(ev.date){
					$(endSelector).datepicker('setStartDate', new Date(ev.date.valueOf()));
					$(beginSelector).datepicker('hide');
					$(endSelector).datepicker('show');
				}else{
					$(endSelector).datepicker('setStartDate',null);
				}
			})
			$(endSelector).datepicker(
					{
						language:  "zh-CN",
						autoclose: true,
						startView:0,
						format: "yyyy-mm-dd",
						clearBtn:true,
						todayBtn:false,
						endDate:new Date()
					}).on('changeDate', function(ev){  
						if(ev.date){
							$(beginSelector).datepicker('setEndDate', new Date(ev.date.valueOf()))
						}else{
							$(beginSelector).datepicker('setEndDate',new Date());
						} 
					})
}
// 格式化金额 保留后2位小数
function formatCurrency(num) {
	if(num==undefined || num == ''){
		return 0.00;
	}
	num = num.toString().replace(/\$|\,/g,'');    
	if(isNaN(num))    
		num = "0";    
	sign = (num == (num = Math.abs(num)));    
	num = Math.floor(num*100+0.50000000001);    
	cents = num%100;    
	num = Math.floor(num/100).toString();    
	if(cents<10)    
		cents = "0" + cents;    
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)    
		num = num.substring(0,num.length-(4*i+3))+','+    
		num.substring(num.length-(4*i+3));    
	return (((sign)?'':'-') + num + '.' + cents);
} 

// 时间范围筛选 可以选中未来时间
function DatePicker2(beginSelector,endSelector){
	// 仅选择日期
	$(beginSelector).datepicker(
			{
				language:  "zh-CN",
				autoclose: true,
				startView: 0,
				format: "yyyy-mm-dd",
				clearBtn:true,
				todayBtn:false
			}).on('changeDate', function(ev){
				if(ev.date){
					$(endSelector).datepicker('setStartDate', new Date(ev.date.valueOf()));
					$(beginSelector).datepicker('hide');
					$(endSelector).datepicker('show');
				}else{
					$(endSelector).datepicker('setStartDate',null);
				}
			})
			$(endSelector).datepicker(
					{
						language:  "zh-CN",
						autoclose: true,
						startView:0,
						format: "yyyy-mm-dd",
						clearBtn:true,
						todayBtn:false
					}).on('changeDate', function(ev){  
						if(ev.date){
							$(beginSelector).datepicker('setEndDate', new Date(ev.date.valueOf()))
						}else{
							$(beginSelector).datepicker('setEndDate',new Date());
						} 
					})
}

// 时间范围筛选 今天开始 月份
function DatePicker3(endSelector){
	// 仅选择日期
	$(endSelector).datepicker( { 
		language: "zh-CN",
		autoclose: true,
		todayHighlight: true,
		format: 'yyyy-mm',
		startView: 3,
		minViewMode: 1,
		maxViewMode: 3,
		forceParse: 0,
	}).datepicker('setStartDate',new Date());
}

// 时间范围筛选 今天开始 日期
function DatePicker4(endSelector){
	// 仅选择日期
	$(endSelector).datepicker( { 
		language:  "zh-CN",
		autoclose: true,
		startView: 0,
		format: "yyyy-mm-dd",
		clearBtn:true,
		todayBtn:false
	}).datepicker('setStartDate',new Date());
}
function showdatepicker(endSelector) {  
	$(endSelector).datepicker({
		language:  "zh-CN",
		monthNamesShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],  // 区域化月名为中文
		prevText: '上月',         // 前选按钮提示
		nextText: '下月',         // 后选按钮提示
		changeYear: true,          // 年下拉菜单
		changeMonth: true,             // 月下拉菜单
		showButtonPanel: true,         // 显示按钮面板
		showMonthAfterYear: true,  // 月份显示在年后面
		currentText: "本月",         // 当前日期按钮提示文字
		closeText: "关闭",           // 关闭按钮提示文字
		dateFormat: "yy-mm",       // 日期格式
	});
}

// AJAX 同步
function sync(url,PARAMS,method) {
	return ajax_util(url,PARAMS,false,method);
}

// AJAX 异步
function async(url,PARAMS,method) {
	return ajax_util(url,PARAMS,true,method);
}

// AJAX工具JS
function ajax_util(url,PARAMS,is_a_s_ync,method) {
	var _data;
	$.ajax({
		cache: false,
		type: method,
		url: url,
		data:PARAMS,
		async: is_a_s_ync,
		error: function(request) {
			alert("Connection error");
		},
		success: function(data) {
			_data =  FastJson.format(data);
		},
		error : function() {
			alert("Ajax请求数据失败!");
		}
	});
	return _data;
}

// 计算天数差的函数，通用
function  DateDiff(sDate1,  sDate2){    // sDate1和sDate2是2006-12-18格式
	var  aDate,  oDate1,  oDate2,  iDays  
	aDate  =  sDate1.split("-")  
	oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]) // 转换为12-18-2006格式
	aDate  =  sDate2.split("-")  
	oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
	iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24) // 把相差的毫秒数转换为天数
	return  iDays  
}   

// 时间格式 包含 时分秒
function formatterDate(date) {
	var datetime = date.getFullYear()
	+ "-"// "年"
	+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1))
		+ "-"// "月"
		+ (date.getDate() < 10 ? "0" + date.getDate() : date
				.getDate());
	return datetime;
}

// 时间格式 包含 时分秒
function formatterDate2(date) {
	var datetime = date.getFullYear()
	+ "-"// "年"
	+ ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1))
		+ "-"// "月"
		+ (date.getDate() < 10 ? "0" + date.getDate() : date
				.getDate()) + " " + "00:00:00";
	return datetime;
}

function formatterDate3(date) {
	if(date==undefined){
		return "";
	}
	var dateTime = (date.getFullYear()) + "-" + (date.getMonth() + 1) + "-" +(date.getDate()) + " " + (date.getHours()) + ":" + (date.getMinutes())+ ":" +(date.getSeconds());
	return dateTime;
}

// 获取当前时间 前N天的时间
function getBeforeDate(n){
	var n = n;
	var d = new Date();
	var year = d.getFullYear();
	var mon=d.getMonth()+1;
	var day=d.getDate();
	if(day <= n){
		if(mon>1) {
			mon=mon-1;
		}
		else {
			year = year-1;
			mon = 12;
		}
	}
	d.setDate(d.getDate()-n);
	year = d.getFullYear();
	mon=d.getMonth()+1;
	day=d.getDate();
	s = year+"-"+(mon<10?('0'+mon):mon)+"-"+(day<10?('0'+day):day);
	return s;
}

