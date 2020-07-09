/**
 * 
 */
$(function() {
	$(".fav").on("click", function(){
		var id = $(this).attr("value");
		$.ajax({
			url: "http://localhost:8080/addFav",
			dataType: "json",
			data: { 
				id:id
			},
			type: "POST",
			async: true
		}).done(function(data) {
			$("#favNum"+id).text(data.fav);
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("正しい結果を得られませんでした。");
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		}).always(function(data){
			
		});
	})
});