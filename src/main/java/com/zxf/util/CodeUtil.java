package com.zxf.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxf
 * @date 2018/9/21 10:51
 */
public class CodeUtil {

    /**
     * 判断验证码是否符合预期
     * */
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExcepted = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
        return !(verifyCodeActual == null || !verifyCodeActual.equalsIgnoreCase(verifyCodeExcepted));
    }
}
