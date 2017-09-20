package com.winning.wework.action;

import com.alibaba.fastjson.JSONObject;
import com.winning.global.Constants;
import com.winning.global.support.action.BaseAction;
import com.winning.global.util.ParamUtil;
import com.winning.wework.service.WeWorkUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wcx on 2017/8/2.
 */
@Controller
@RequestMapping(Constants.DEFAULT_PRE_URL + "/wework")
public class WeWorkUserAction extends BaseAction {

    @Resource
    private WeWorkUserService weWorkUserService;

    @RequestMapping("/user.sdo")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        return "wework/user";
    }

    @RequestMapping("/getUser.sdo")
    @ResponseBody
    public JSONObject getUser(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "userId");
        return weWorkUserService.getUser(p);
    }

    @RequestMapping("/createUser.sdo")
    @ResponseBody
    public JSONObject createUser(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "p");
        return weWorkUserService.createUser(p);
    }
    @RequestMapping("/updateUser.sdo")
    @ResponseBody
    public JSONObject updateUser(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "p");
        return weWorkUserService.updateUser(p);
    }
    @RequestMapping("/deleteUser.sdo")
    @ResponseBody
    public JSONObject deleteUser(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "userId");
        return weWorkUserService.deleteUser(p);
    }

    @RequestMapping("/getUserList.sdo")
    @ResponseBody
    public JSONObject getUserList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String departmentId = ParamUtil.getString(request, "departmentId");
        String fetchChild = ParamUtil.getString(request, "fetchChild", "1");
        return weWorkUserService.getUserList(departmentId, fetchChild);
    }


}
