<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/page/security/views/include.inc.jsp"%>
<script type="text/javascript">
<!--
jQuery(document).ready(function(){
	$("#captcha").click(function(){
		$(this).attr("src", "<%=basePath %>/Captcha.jpg");
		return false;
	});
});
//-->
</script>
<div class="pageContent">
	<form method="post" action="<%=basePath %>/login" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<%-- 指定为ajax请求 --%>
		<input type="hidden" name="ajax" value="true"/>
		<div class="pageFormContent" layoutH="58">
			<p>
				<label>用户名:</label>
				<input type="text" name="username" id="username" maxlength="20" class="required"/>
			</p>
			<p>
				<label>密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
				<input type="password" name="password" id="password" maxlength="20" class="required"/>
			</p>
			<%--
			<p>
				<label>验证码:</label>
				<input type="text" id="captcha_key" name="captcha_key" class="code validate[required,maxSize[6]]" size="6" />&nbsp;&nbsp;
				<span><img src="<%=basePath %>/Captcha.jpg" alt="点击刷新验证码" width="75" height="24" id="captcha"/></span>
			</p>
			 --%>			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">登录</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
