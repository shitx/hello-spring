<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
	<head>
		<title>搜索博客</title>
	</head>
	<style type="text/css">
		#searchDiv{
			position:absolute;
			left:40%;
			top:50%;
			margin-left:-100px;
			margin-top:-50px;
		}
		
		.mouseOver{
			background:#708090;
			color:#FFFAFA;
		}
		
		.mouseOut{
			background:#FFFAFA;
			color:#000000;
		}
		
	</style>
	<!-- <link href="../mycss/search.css" rel="stylesheet" type="text/css" /> -->
	
	<!-- java script -->
	<script type="text/javascript">
		var xmlHttp;	
		
		//获取XmlHttp对象
		function createXMLHttp(){
			if(xmlHttp != null){
				return xmlHttp;
			}
			var xmlHttp;
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest();
			}
			if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				if(!xmlHttp){
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				}
			}
			return xmlHttp;
		}
		
		//清空信息
		function clearTableContent(){
			document.getElementById("content_table_body").innerHTML="";
			document.getElementById("popDiv").style.border="none";
		}
		
		//监听点击事件并及时清空表格
		window.addEventListener('click', function(){
			var con = event.target.innerHTML;
			if(!!con && con.indexOf('searchDiv') >= 0){
				clearTableContent();
			}
		});
		
		// 处理服务器响应
		function onServerResponse(){
			//4表示完成
			if(xmlHttp.readyState == 4) {
				//200代表服务器响应成功,404not found,500服务器内部错误
				if(xmlHttp.status == 200){
					//交互成功，获得文本格式的响应数据[text,json]
					var result = xmlHttp.responseText;
					//解析获得数据
					var json = eval("("+result+")");
					//获得数据后动态显示到输入框下
					//alert(json);
					//显示数据
					setContent(json);
				}
			}
		}
		
		//获取输入内容的关联内容
		function getSearchKeyword(){
			//获取用户输入关键字
			var content = document.getElementById("keyword");
			//清除历史数据表
			clearTableContent();
			if(content.value == ""){
				return;
			}
			
			xmlHttp = createXMLHttp();
			//给服务器发送数据
			var url = "//localhost:8090/search?keyword=" + escape(content.value);
			//console.log(url);
			xmlHttp.open("GET",url,true); //true 表示在send方法之后，脚本会继续执行，不会等待服务器响应
			xmlHttp.onreadystatechange = onServerResponse; //绑定服务器响应的处理方法
			//alert(xmlHttp);
			xmlHttp.send(null);
		}
		
		//设置关联信息的现实位置，和输入框一致
		function setLocation(){
			var content = document.getElementById("keyword");
			var width = content.offsetWidth; //输入框的宽度
			var left = content["offsetLeft"]; //到左边框的距离
			var top = content["offsetTop"] + content.offsetHeight; //到顶部的距离
			//获取显示数据的div
			var popDiv = document.getElementById("popDiv");
			popDiv.style.border = "black 1px solid";
			popDiv.style.left = left + "px";
			popDiv.style.top = top + "px";
			popDiv.style.width = width;
			document.getElementById("content_table").style.width = width + "px";
		}
		
		//设置关联数据的展示
		function setContent(contents){
			setLocation();
			var size = contents.length;
			for(var i = 0; i < size; i ++){
				var nextNode = contents[i];
				var tr = document.createElement("tr");
				var td = document.createElement("td");
				td.setAttribute("border",0);
				td.setAttribute("bgcolor","#FFFAFA");
				td.onmouseover = function(){
					this.className = "mouseOver";
				};
				td.onmouseout = function(){
					this.className = "mouseOut";
				};
				td.onclick = function(self){
					//当鼠标点击该关联数据自动将输入框中数据替换为被选中的关联项
					console.log(self);
					document.getElementById('keyword').value =self.target.innerText;
					clearTableContent();
				};
				var text = document.createTextNode(nextNode);
				td.appendChild(text);
				tr.appendChild(td);
				document.getElementById("content_table_body").append(tr);
			}
		}
	</script>
	
	<body>
		<div id="searchDiv">
			<!-- onblur="clearTableContent()"  -->
			<input type="text" size=40 id="keyword" onkeyup="getSearchKeyword()" onfocus="getSearchKeyword()"/>
			<input type="button" width="50px" height="10px" value="搜索一下"/>
			<!-- 关联内容展示区域 -->
			<div id="popDiv">
				<table id="content_table" bgcolor="#FFFAFA" border="0" cellspacing="0" "cellpadding"="0">
					<tbody id="content_table_body">
						<!-- 动态显示关联内容 -->
						<!-- <tr><td>ajax1</td></tr>
						<tr><td>ajax2</td></tr> -->
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>