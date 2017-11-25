$(document).ready(function() {
	// 줄이기 버튼 리스너
	$("#btn_shorten").click(function() {
		$("#btn_result").css("display", "none");
		$("#txt_url").attr("disabled", true);
		$("#btn_shorten").attr("disabled", true);
		$("#btn_shorten").html("<img src='/assets/images/spinner.gif' style='width:15px; margin-bottom:2px;'> 처리중...");

		var url = $("#txt_url").val();

		if(url.length != 0) {
			if(url.substring(0, 4) != "http") {
				url = "http://" + url;
			}

			var data = {
				originalURL : url
			};

			$.ajax({
				type: "POST",
				url: "/doShort",
				timeout: 20000,
				cache: false,
				contentType: "application/json;charset=UTF-8",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(res) {
					var shorten_url = "http://" + window.location.hostname + ":" +window.location.port+"/" + res.data.encodedIdx;

					$("#btn_result").attr("data-clipboard-text", shorten_url);
					$("#btn_result").text(shorten_url);

					$("#txt_url").attr("disabled", false);
					$("#btn_shorten").html("<i class='icon-hand-right' style='margin:4px 5px 0px 0px;'></i>줄이기</button>");
					$("#btn_shorten").attr("disabled", false);
					$("#btn_result").fadeIn(500);
					var qrcode = new QRCode(document.getElementById("div_qrcode"), {
						text: shorten_url,
						width: 128,
						height: 128,
						colorDark : "#000000",
						colorLight : "#ffffff",
						correctLevel : QRCode.CorrectLevel.H
					});
				},
				error: function(request, status, error) {
					alert("처리 중 에러발생!");

					$("#txt_url").attr("disabled", false);
					$("#btn_shorten").attr("disabled", false);
				}
			});
		}

		else {
			alert("먼저, URL을 입력해주세요!");
			$("#txt_url").focus();
		}
	});

	// 결과 버튼 리스너
	$("#btn_result").click(function() {
		var shorten_url = $("#btn_result").text();

		if(shorten_url != "http://" + window.location.hostname) {
			var msg = "";

			if(jQuery.browser.mobile == true) {
				msg = "지금 바로 해당 사이트로 이동할까요?";
			}
	
			else {
				msg = "짧아진 URL이 클립보드에 복사되었습니다!\n\n지금 바로 해당 사이트로 이동할까요?";
			}
	
			if(confirm(msg)) {
				location.href = shorten_url;
			}
	
			else {
				location.reload();
			}
		}
	});

	// URL 입력 텍스트 필드 리스너
	$("#txt_url").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btn_shorten").click();
		}
	});

	var clipboard = new ZeroClipboard($("#btn_result"));
});
