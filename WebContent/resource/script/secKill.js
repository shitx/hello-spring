//放置主要的交互逻辑 模块化
var secKill = {
		path : "",
		//封装秒杀相关的ajax url
		URL : {
			now : function() {
				return secKill.path + '/seckill/time/now';
			},
			exposer : function (secKillId) {
				return secKill.path + '/seckill/' + secKillId + '/exposer'
			},
			execution : function(secKillId, md5){
				return secKill.path + '/seckill/' + secKillId + '/' + md5 + '/exucution'
			}
		},
		
		handleSecKill: function (secKillId, node) {
			//获取秒杀地址，控制实现逻辑，执行秒杀
			node.hide().html('<button class="btn btn-primary btn-lg" id ="killBtn">开始秒杀</button>');
			$.post(secKill.URL.exposer(secKillId),{},function(result){
				//在回调函数中执行交互
				if(result && result['success']){
					var exposer = result['data'];
					if(exposer['expose']){
						//开启秒杀
						//获取秒杀地址
						var md5 = exposer['md5']
						var killUrl = secKill.URL.execution(secKillId, md5)
						//只绑定一次点击事件
						$('#killBtn').one('click',function(){
							//执行秒杀请求
							//1禁用按钮 变灰
							$(this).addClass('disabled');
							//2 发送秒杀请求,点击后发送请求，分析返回的result结果
							console.log("killUrl="+killUrl)
							$.post(killUrl,{},function(result){
								var killResult = result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								var success = result['success'];
								
								if(!!success){
									//3 显示秒杀结果
									node.html('<span class="label label-success">' + stateInfo + '</span>');
								}else{
									console.log("success="+success)
									console.log("stateInfo="+stateInfo)
									node.html('<span class="label label-warning">' + stateInfo + '</span>');
								}
							})
						});
						node.show(); //执行完毕后才会重新显示
					}else{
						//未开启秒杀
						var now = exposer['now'];
						var start = exposer['start'];
						var end = exposer['end'];
						//重新开始计时逻辑
						secKill.countdown(secKillId, now, start, end);
					}
				}else{
					console.log("result="+result);
				}
			});
		},
		
		//验证手机号
		validatePhone: function(phone){
			if(phone && phone.length == 11 && ! isNaN(phone)){
				return true;
			}else{
				return false;
			}
		},
		countdown : function(secKillId, nowTime, startTime, endTime){
			var secKillBox = $('#secKill-box');
			if(nowTime > endTime){
				//秒杀结束
				secKillBox.html("秒杀结束！");
			}else if(nowTime < startTime){
				//秒杀未开始，记时
				var killTime = new Date(startTime + 1000);
				secKillBox.countdown(killTime, function(event){//每次时间变化都会重新运行回调
					var format = event.strftime('秒杀计时： %D天 %H时 %M分 %S秒');
					secKillBox.html(format);
					//时间到时的回调
				}).on('finish.countdonw',function(){
					//获取秒杀地址，控制实现逻辑，执行秒杀
					secKill.handleSecKill(secKillId,secKillBox);
				});
			}else{
				//秒杀开始
				secKill.handleSecKill(secKillId,secKillBox);
			}
		},
		//详情页秒杀逻辑
		detail : {
			init : function(params){
				//手机验证和登录， 记时交互
				//规划交互流程
				//没有后端登录，所以登录信息放在cookie中
				//在cookie中查手机号码
				var killPhone = $.cookie('killPhone');
				secKill.path = params['path'];
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var secKillId = params['secKillId'];
				//验证手机号
				if(!secKill.validatePhone(killPhone)){
					//绑定phone  获取jsp中killPhoneModal节点
					//控制输出
					var killPhoneModal = $('#killPhoneModal');
					//显示了弹出层
					killPhoneModal.modal({
						show:true, //将fade修改为show
						backdrop:'static', //禁止位置关闭
						keyboard:false //关闭键盘事件
					});
					$('#killPhoneBtn').click(function(){
						//用户点击时判断用户手机号输入是否对
						var inputPhone = $('#killPhoneKey').val();
						if(secKill.validatePhone(inputPhone)){
							console.log(inputPhone); //TODO
							//电话写入cookie
							$.cookie('killPhone',inputPhone,{expires:7});
							//刷新页面
							window.location.reload();
						}else{
							$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号码错误！</label>').show(300);
						}
					});
				}
				//已登录  记时交互
				$.get(secKill.URL.now(), {}, function(result){
					if(result && result['success']){
						var nowTime = result['data'];
						//时间判断
						secKill.countdown(secKillId, nowTime, startTime, endTime)
					}else{
						console.log("result=" + result)
					}
				});
			}
		}
}