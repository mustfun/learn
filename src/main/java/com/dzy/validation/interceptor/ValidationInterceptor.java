package com.dzy.validation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dzy.model.ReturnBase;
import com.dzy.validation.exception.ValidationException;
import com.dzy.validation.handle.ValidationHandler;

/** 
 * 参数校验拦截器
 * 
 * @author dengzhiyuan
 *
 */
public class ValidationInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String param = request.getParameter("request");
		//System.out.println(this.getClass().getName()+"拦截器已经进入！！！");
		//这种拦截器可以注入service，然后进行记录日志！
		/*ReturnBase returnBase = ValidationHandler.getInstance().handler(param,
				null);
		if (!returnBase.getFlag().equals("1")) {
			System.out.println("出错啦");
			throw new ValidationException(1001, returnBase.getMsg());
		}*/
		return true;
	}

}
