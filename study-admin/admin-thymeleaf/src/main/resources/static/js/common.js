//导航选择器
$(document).ready(function() {
	$("#side-menu ul li").click(function(){
        var url = window.location.href;
        if (url.indexOf("main") > 0) {

        } else if (url.indexOf("news") > 0) {
            $("#news").addClass("active");
            $("#newsTypeManage").addClass("active");
        } else if (url.indexOf("chart") > 0) {
            $("#chart").addClass("active");
        } else if (url.indexOf("setting") > 0) {
            $("#chart").addClass("active");
        }
	});


});

// 提示条配置
/*
toastr.options = {
	"closeButton" : true,
	"debug" : false,
	"progressBar" : true,
	"preventDuplicates" : false,
	"positionClass" : "toast-top-right",
	"onclick" : null,
	"showDuration" : "400",
	"hideDuration" : "1000",
	"timeOut" : "7000",
	"extendedTimeOut" : "1000",
	"showEasing" : "swing",
	"hideEasing" : "linear",
	"showMethod" : "fadeIn",
	"hideMethod" : "fadeOut"
}*/
