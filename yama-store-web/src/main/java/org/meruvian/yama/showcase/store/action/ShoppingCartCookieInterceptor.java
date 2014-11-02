package org.meruvian.yama.showcase.store.action;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class ShoppingCartCookieInterceptor extends AbstractInterceptor {
	private Map<String, String> cookies = new LinkedHashMap<String, String>();
	public static final String CART_ID = "SHOPPING_CART_ID";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext invocationContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) invocationContext.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocationContext.get(ServletActionContext.HTTP_RESPONSE);
		ValueStack stack = invocation.getStack();
		
		Cookie cookie = getCookieForName(CART_ID, request.getCookies());
		if (cookie == null) {
			String cartId = UUID.randomUUID().toString();
			cookie = new Cookie(CART_ID, cartId);
		}
		
		stack.setValue(CART_ID, cookie.getValue());
		
		String invoke = invocation.invoke();
		
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(cookie);
		
		return invoke;
	}
	
	private Cookie getCookieForName(String name, Cookie... cookies) {
		for (Cookie cookie : cookies) {
			if (StringUtils.equals(cookie.getName(), name)) {
				return cookie;
			}
		}
		
		return null;
	}
}
