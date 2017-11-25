<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
	    <title>Shorten-URL</title>
	    
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    
	    <link rel="shortcut icon" href="/assets/images/favicon.ico" type="image/x-icon">
	    <link rel="icon" href="/assets/images/favicon.ico" type="image/x-icon">
	
	    <link rel="stylesheet" type="text/css" href="/assets/styles/bootstrap-ko.min.css">
	    <link rel="stylesheet" type="text/css" href="/assets/styles/bootstrap-responsive.min.css">
	
	    <script src="/assets/scripts/jquery-1.11.0.min.js" type="text/javascript"></script>
	    <script src="/assets/scripts/bootstrap.min.js" type="text/javascript"></script>
	</head>
	
	<body>
	    <div class="navbar navbar-fixed-top">
	        <div class="navbar-inner">
	            <a class="brand" href="/" style="padding:8px 40px 10px 40px;"><img src="/assets/images/favicon.ico" width="15px"> Shorten-URL</a>
	        </div>
	    </div>
	    <div class="container">
			<div class="row" style="margin-top:40px;">
				<div class="span3"></div>
				<div class="span6">
					<div class="page-header" style="text-align:center;">
						<h1>Shorten URL</h1>
				    </div>
				</div>
				<div class="span3">
				</div>
			</div>
			<div class="row">
				<div class="span3"></div>
				<div class="span6">
					<div class="alert alert-block">
						<center><h4>사용방법</h4></center><br>
						1. Shorten URL을 이용하여 짧게 바꿀 URL을 입력합니다.<br>
						2. '줄이기' 버튼을 클릭하고 잠시 기다립니다.<br>
						3. 하단의 버튼을 통해 짧아진 URL을 확인 할 수 있습니다.<br><br>
						* 하단의 버튼을 누르면 짧아진 URL이 클립보드에 복사됩니다.<br>
						* 버튼을 누른 이후 해당 페이지로 이동이 가능합니다.
			    	</div>
				</div>
				<div class="span3"></div>
			</div>
			<div class="row">
				<div class="span3"></div>
				<div class="span4">
					<input type="text" style="text-align:center; width:99.3%; padding:4px 0px 4px 0px;" id="txt_url" placeholder="URL을 입력해주세요"></input>
				</div>
				<div class="span2">
					<button class="btn" style="width:100%;"id="btn_shorten"><i class="icon-hand-right" style="margin:4px 5px 0px 0px;"></i>줄이기</button>
				</div>
				<div class="span3"></div>
			</div>
			<div class="row" style="margin-top:8px; margin-bottom:20px;">
				<div class="span3"></div>
				<div class="span6">
					<button class="btn btn-warning" style="width:100%; display:none;" id="btn_result">http://<?=$_SERVER['SERVER_NAME']?></button>
					<div id="div_qrcode"></div>
				</div>
				<div class="span3"></div>
			</div>
	    </div>
	    <script type="text/javascript" src="/assets/scripts/ZeroClipboard.min.js"></script>
		<script type="text/javascript" src="/assets/scripts/jquery-detect-mobile-browsers.min.js"></script>
		<script type="text/javascript" src="/assets/scripts/qrcode.min.js"></script>
		<script type="text/javascript" src="/assets/scripts/index.js"></script>
	</body>
</html>