package com.example.springbootdemoconsumer.filterTest;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class XssFilterWrapper extends HttpServletRequestWrapper {
    public XssFilterWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 对数组参数进行特殊字符过滤
     */
    @Override
    public String[] getParameterValues(String name) {
        //过滤掉不拦截的
        if ("articleData.content".equalsIgnoreCase(name)) {
            return super.getParameterValues(name);
        }

        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        String[] newValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = HtmlUtils.htmlEscape(values[i]);// spring的HtmlUtils进行转义
        }
        return newValues;
    }
}

