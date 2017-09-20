package com.winning.wework.action;

import com.alibaba.fastjson.JSONObject;
import com.winning.global.Constants;
import com.winning.global.support.action.BaseAction;
import com.winning.global.util.ParamUtil;
import com.winning.global.util.SpringUtil;
import com.winning.wework.WeWorkErrCode;
import com.winning.wework.service.WeWorkAsyncService;
import com.winning.wework.service.WeWorkMediaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by wcx on 2017/8/2.
 */
@Controller
@RequestMapping(Constants.DEFAULT_PRE_URL + "/wework")
public class WeWorkAsyncAction extends BaseAction {
    @RequestMapping("/uploadMedia.sdo")
    @ResponseBody
    public JSONObject uploadMedia(@RequestParam("csvFile") MultipartFile mFile) throws IOException {
        WeWorkMediaService weWorkMediaService = (WeWorkMediaService) SpringUtil.getBean("weWorkMediaService");
        JSONObject jsonRet =  weWorkMediaService.uploadMedia(mFile.getInputStream());
        if (jsonRet.getIntValue("errcode") != WeWorkErrCode.OK) {
            String errMsg = MessageFormat.format("上传csv文件流到微信失败! errcode={0} errmsg={1}",
                    jsonRet.get("errcode"), jsonRet.get("errmsg"));
            jsonRet.put("errmsg", errMsg);
        }
        return jsonRet;
    }

    @RequestMapping("/getAsyncJobResult.sdo")
    @ResponseBody
    public JSONObject getAsyncJobResult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String jobId = ParamUtil.getString(request, "jobId", "");

        WeWorkAsyncService weWorkAsyncService = (WeWorkAsyncService) SpringUtil.getBean("weWorkAsyncService");
        return weWorkAsyncService.getResult(jobId);
    }

    @RequestMapping("/syncUser.sdo")
    @ResponseBody
    public JSONObject syncUser(@RequestParam("csvFile") MultipartFile mFile) throws IOException {
        JSONObject jsonRet =  uploadMedia(mFile);

        if (jsonRet.getIntValue("errcode") == WeWorkErrCode.OK) {
            String mediaId = jsonRet.getString("media_id");

            WeWorkAsyncService weWorkAsyncService = (WeWorkAsyncService) SpringUtil.getBean("weWorkAsyncService");
            return weWorkAsyncService.syncUser(mediaId);
        }

        return jsonRet;
    }

    @RequestMapping("/replaceUser.sdo")
    @ResponseBody
    public JSONObject replaceUser(@RequestParam("csvFile") MultipartFile mFile) throws IOException {
        JSONObject jsonRet =  uploadMedia(mFile);

        if (jsonRet.getIntValue("errcode") == WeWorkErrCode.OK) {
            String mediaId = jsonRet.getString("media_id");

            WeWorkAsyncService weWorkAsyncService = (WeWorkAsyncService) SpringUtil.getBean("weWorkAsyncService");
            return weWorkAsyncService.syncUser(mediaId);
        }

        return jsonRet;
    }

    @RequestMapping("/replaceDepartment.sdo")
    @ResponseBody
    public JSONObject replaceDepartment(@RequestParam("csvFile") MultipartFile mFile) throws IOException {
        JSONObject jsonRet =  uploadMedia(mFile);

        if (jsonRet.getIntValue("errcode") == WeWorkErrCode.OK) {
            String mediaId = jsonRet.getString("media_id");

            WeWorkAsyncService weWorkAsyncService = (WeWorkAsyncService) SpringUtil.getBean("weWorkAsyncService");
            return weWorkAsyncService.replaceDepartment(mediaId);
        }

        return jsonRet;
    }

}
