<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="maosheji.core.oscache.FrontCache"%>
<%@page import="maosheji.core.front.SystemManager"%>
<%@page import="java.net.URL"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="maosheji.core.ManageContainer"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
FrontCache frontCache = (FrontCache) app.getBean("frontCache");

String method = request.getParameter("method");
out.println("method="+method);

try{
	if(StringUtils.isBlank(method)){
		frontCache.loadAllCache();
		out.println("加载数据成功！");

	}else if(method.equals("activity")){
		
		out.println("加载数据成功！");
	}else if(method.equals("loadIndexImgs")){
		
		frontCache.loadIndexImgs();
		out.println("加载数据成功！");
	}else if(method.equals("loadAdvertList")){
		
		out.println("加载数据成功！");
	}else if(method.equals("loadNotifyTemplate")){
		
		out.println("加载数据成功！");
	}else if(method.equals("loadProductStock")){
		
		frontCache.loadProductStock();
		out.println("加载数据成功！");
	}else if(method.equals("hotquery")){
		
		frontCache.loadHotquery();
		out.println("加载数据成功！");
	}
}catch(Exception e){
	e.printStackTrace();
}

%>
