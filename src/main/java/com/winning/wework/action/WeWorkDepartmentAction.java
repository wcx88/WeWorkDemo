package com.winning.wework.action;

import com.alibaba.fastjson.JSONObject;
import com.winning.global.Constants;
import com.winning.global.support.action.BaseAction;
import com.winning.global.util.ParamUtil;
import com.winning.wework.service.WeWorkDepartmentService;
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
public class WeWorkDepartmentAction extends BaseAction {

    @Resource
    private WeWorkDepartmentService weWorkDepartmentService;

    @RequestMapping("/department.sdo")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        return "wework/department";
    }

    @RequestMapping("/getDepartmentList.sdo")
    @ResponseBody
    public JSONObject getDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "departmentId");
        return weWorkDepartmentService.getDepartmentList(p);
    }

    @RequestMapping("/createDepartment.sdo")
    @ResponseBody
    public JSONObject createDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "p");
        return weWorkDepartmentService.createDepartment(p);
    }
    @RequestMapping("/updateDepartment.sdo")
    @ResponseBody
    public JSONObject updateDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "p");
        return weWorkDepartmentService.updateDepartment(p);
    }
    @RequestMapping("/deleteDepartment.sdo")
    @ResponseBody
    public JSONObject deleteDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String p = ParamUtil.getString(request, "departmentId");
        return weWorkDepartmentService.deleteDepartment(p);
    }

}
