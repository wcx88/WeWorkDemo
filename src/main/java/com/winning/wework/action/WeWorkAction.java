package com.winning.wework.action;

import com.alibaba.fastjson.JSONObject;
import com.winning.global.Constants;
import com.winning.global.support.action.BaseAction;
import com.winning.global.util.ParamUtil;
import com.winning.global.util.SpringUtil;
import com.winning.wework.WeWorkConfig;
import com.winning.wework.WeWorkErrCode;
import com.winning.wework.service.WeWorkUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

/**
 * Created by wcx on 2017/8/2.
 */
@Controller
@RequestMapping(Constants.DEFAULT_PRE_URL + "/wework")
public class WeWorkAction extends BaseAction {

    @RequestMapping("/asyncTask.sdo")
    public String asyncTask(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        return "wework/asyncTask";
    }
    
    @RequestMapping("/tools.sdo")
    public String tools(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
    	return "wework/tools";
    }

    @RequestMapping("/contact.sdo")
    public String contact(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
    	return "wework/contact";
    }

    // 微信菜单页面跳转前处理
    @RequestMapping("/redirect.weworkService")
    public String redirect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        JSONObject allParam = ParamUtil.getAllParam(request);
        String appName = allParam.getString("appName");
        String action = allParam.getString("action");
        if(StringUtils.isBlank(appName) || StringUtils.isBlank(action)) {
            modelMap.put("errmsg", "非法参数");
            return "error/error";
        }

        // 优先从cookie中取用户名，如果不存在，重定向到微信授权页面取
        String userId = ParamUtil.getCookieValue(request,"UserID");
        if (StringUtils.isBlank(userId)) {
            modelMap.put("appid", WeWorkConfig.WEWORK_CORPID);
            modelMap.put("agentid", WeWorkConfig.getAppId(appName));
            String uuid = UUID.randomUUID().toString();
            modelMap.put("state", uuid);
            request.getSession().setAttribute("wework_callback_state", uuid);
            String paramBase64Str = new BASE64Encoder().encode(allParam.toString().getBytes());
            paramBase64Str = paramBase64Str.replaceAll("[\\s*\t\n\r]", "");
            modelMap.put("paramBase64Str", paramBase64Str);
            return "wework/redirect";
        } else {
            // todo login
//            UserService userService = (UserService)SpringUtil.getBean("userService");
//            userService.login(userId, request, response);
//            if("url".equals(action)) { // todo 暂时只支持跳转
//            }
            String url = allParam.getString("url");
            return "redirect:" + url;
        }
    }

    // 微信返回的code来查一次用户信息，并且重定向页
    @RequestMapping("/getUserInfoAndRedirect.weworkService")
    public String getUserInfoAndRedirect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
        String code = request.getParameter("code");// 微信返回的code，可以用来查一次用户信息
        String state = ParamUtil.getString(request, "state", "");
        if(!state.equals(request.getSession().getAttribute("wework_callback_state"))) {
            modelMap.put("errmsg", "非法访问");
            return "error/error";
        }

        String paramBase64Str = ParamUtil.getString(request, "p");
        String paramJsonStr = new String(new BASE64Decoder().decodeBuffer(paramBase64Str));
        JSONObject param = JSONObject.parseObject(paramJsonStr);
        String appName = param.getString("appName");

        WeWorkUserService weWorkUserService = (WeWorkUserService)SpringUtil.getBean("weWorkUserService");
        JSONObject userInfo = weWorkUserService.getUserByCode(appName, code);
        if(userInfo.getInteger("errcode") != WeWorkErrCode.OK) {
            modelMap.put("errmsg", MessageFormat.format("用微信返回的code查用户信息失败，code={0}, 返回json串={1}", code, userInfo));
            return "error/error";
        }

        String userId = userInfo.getString("UserId");
        // todo login
//        UserService userService = (UserService)SpringUtil.getBean("userService");
//        userService.login(userId, request, response);

        response.addCookie(new Cookie("UserID", userId));

        String action = param.getString("action");
        if("url".equals(action)) {// todo 暂时只支持跳转
            ;
        }
        String url = param.getString("url");
        return "redirect:" + url;
    }
}
