import com.alibaba.fastjson.JSONObject;
import com.winning.wework.WeWorkConfig;
import com.winning.wework.service.*;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wcx on 2017/7/14.
 */
public class WeWorkDemo {
    WeWorkClient weWorkClient;
    WeWorkUserService weWorkUserService;
    WeWorkDepartmentService weWorkDepartmentService;
    WeWorkMediaService weWorkMediaService;
    WeWorkAsyncService weWorkAsyncService;
    WeWorkTagService weWorkTagService;

    @Before
    public void init() {
//        SpringUtil.context = (ApplicationContext) new ClassPathXmlApplicationContext("spring/applicationContext_test.xml");
        weWorkClient = new WeWorkClient();
        // todo for test
        weWorkClient.setAccessToken(WeWorkConfig.APP_NAME_CONTACT, "xCmOfEXIVNi1ENXe2YoCvPy015jBn-t7HPtnDQbnTHzE0lX-yl-N1pI0ABrLm7glj28jdGP5adzvk8IIkaXA9ATWO_OJSL7myQfP_sS5shdJ4r4lhTunTZx9Rp-fOiE01RHjWQYrElBf-M_Omb2-85mQjr-mhmF-Vk3CzVXkxqIyfI3nnJy2Wj1eX0hbDAsKMWuqkx7I7zq1-ypmDcsVwBiEfCyYkW3n0C2QsjCoAMThGAbg_E8q928uKfIAB3i_xbOY0UH-dPYOaEMY6wHrkdWrfisCJhMRPVz6y9tvDA0");
        //weWorkClient.setAccessToken(WeWorkConfig.APP_NAME_PMIS, "");

        weWorkUserService = new WeWorkUserServiceImpl();

        //weWorkUserService = (WeWorkUserService)SpringUtil.getBean("weWorkUserService");
        weWorkDepartmentService = new WeWorkDepartmentServiceImpl();
        weWorkMediaService = new WeWorkMediaServiceImpl();
        weWorkAsyncService = new WeWorkAsyncServiceImpl();
        weWorkTagService = new WeWorkTagServiceImpl();
    }

    @Test
    public void testGetAccessToken () {
        System.out.println(weWorkClient.getAccessToken(WeWorkConfig.APP_NAME_CONTACT, true));
    }
    @Test
    public void testGetUser () {
        System.out.println(weWorkUserService.getUser("5328"));
    }
    @Test
    public void testCreatUser () {
        String userInfo = "{" +
                "   \"userid\": \"4095\"," +
                "   \"name\": \"魏成煊test(4095)\"," +
                "   \"english_name\": \"weichengxuan\"," +
                "   \"mobile\": \"\"," +
                "   \"department\": [271]," +
                //"   \"order\":[10,40]," +
                "   \"position\": \"高级开发工程师\"," +
                "   \"gender\": \"1\"," +
                "   \"email\": \"wcx.8@163.com\"," +
                "   \"isleader\": 0," +
                "   \"enable\":1," +
                //"   \"avatar_mediaid\": \"\"," +
                "   \"telephone\": \"\"" +
                //"   \"extattr\": {\"attrs\":[{\"name\":\"爱好222\",\"value\":\"旅游\"},{\"name\":\"卡号\",\"value\":\"5328\"}]}" +
                "}";

        System.out.println(weWorkUserService.createUser(userInfo));
    }
    @Test
    public void testUpdateUser () {
        String userInfo = "{" +
                "   \"userid\": \"5328\"," +
                "   \"name\": \"魏成煊(5328)\"," +
                "   \"english_name\": \"weichengxuan\"," +
                "   \"mobile\": \"\"," +
                "   \"department\": [271]," +
                //"   \"order\":[10,40]," +
                "   \"position\": \"高级开发工程师\"," +
                "   \"gender\": \"1\"," +
                "   \"email\": \"wcx@winning.com.cn\"," +
                "   \"isleader\": 0," +
                "   \"enable\":1," +
                //"   \"avatar_mediaid\": \"\"," +
                "   \"telephone\": \"\"" +
                //"   \"extattr\": {\"attrs\":[{\"name\":\"爱好222\",\"value\":\"旅游\"},{\"name\":\"卡号\",\"value\":\"5328\"}]}" +
                "}";

//        {"avatar":"","department":[271],"email":"wcx@winning.com.cn","enable":1,"english_name":"","errcode":0,
//                "errmsg":"ok","extattr":{"attrs":[]},"gender":"1","hide_mobile":0,"isleader":0,
//                "mobile":"15205012586","name":"魏成煊(5328)","order":[0],"position":"高级开发工程师",
//                "status":4,"telephone":"","userid":"5328"}


        System.out.println(weWorkUserService.updateUser(userInfo));
    }
    @Test
    public void testDeleteUser () {
        String[] userIdList = new String[]{
                "3172",
                "993"
        };
        for (String userId : userIdList) {
            System.out.println(weWorkUserService.deleteUser(userId));
        }
    }
    @Test
    public void testCreatDepartment () {
        String departmentInfo = ("{" +
                "   \"name\": \"福州研发中心\"," +
                "   \"parentid\": 2," +
                "   \"order\": 4," +
                "   \"id\": 4" +
                "}");

        System.out.println(weWorkDepartmentService.createDepartment(departmentInfo));
    }
    @Test
    public void testUpdateDepartment () {
        String departmentInfo = ("{" +
                "   \"name\": \"福州研发中心222\"," +
                "   \"parentid\": 2," +
                "   \"order\": 4," +
                "   \"id\": 4" +
                "}");

        System.out.println(weWorkDepartmentService.updateDepartment(departmentInfo));
    }
    @Test
    public void testDeleteDepartmentList () {
        System.out.println(weWorkDepartmentService.deleteDepartment("4"));
    }
    @Test
    public void testGetDepartmentList () {
        System.out.println(weWorkDepartmentService.getDepartmentList(null));
        System.out.println(weWorkDepartmentService.getDepartmentList(""));
        System.out.println(weWorkDepartmentService.getDepartmentList("1"));
    }


    @Test
    public void testUploadMedia() {
        String str = "姓名,帐号,手机号,邮箱,所在部门,职位\n" +
                "张三,zhangsan,15913112120,zhangsan@gzmailteam.com,5,产品经理\n" +
                "李四,lisi,18913112121,lisi@gzmailteam.com,5,工程师\n";
        byte[] content = str.getBytes();

        System.out.println(weWorkMediaService.uploadMedia(content));
    }

    @Test
    public void testSyncUser() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("用户.csv");

        JSONObject jsonRet = weWorkMediaService.uploadMedia(is);
        System.out.println(jsonRet);
        if (jsonRet.getIntValue("errcode") != 0) {
            return;
        }

        System.out.println(weWorkAsyncService.syncUser(jsonRet.getString("media_id")));
    }
    @Test
    public void testReplaceUser() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("用户.csv");

        JSONObject jsonRet = weWorkMediaService.uploadMedia(is);
        System.out.println(jsonRet);
        if (jsonRet.getIntValue("errcode") != 0) {
            return;
        }

        System.out.println(weWorkAsyncService.replaceUser(jsonRet.getString("media_id")));
    }
    @Test
    public void testUploadMedia3() throws IOException {
        File f = File.createTempFile("用户_", ".csv");
        System.out.println(f.getAbsolutePath());
        String str = "姓名,帐号,手机号,邮箱,所在部门,职位\n" +
                "张三,zhangsan,15913112120,zhangsan@gzmailteam.com,5,产品经理\n" +
                "李四,lisi,18913112121,lisi@gzmailteam.com,5,工程师\n";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(null != fos) fos.close();
        }

        System.out.println(weWorkMediaService.uploadMedia(f));
    }
    @Test
    public void testDownloadMedia2() throws IOException {
        File f = File.createTempFile("用户download_", ".csv");
        FileOutputStream fos = null;
            fos = new FileOutputStream(f);

        System.out.println(weWorkMediaService.downloadMedia("3pAfcZ57-vKpJO_uhStx0jQMAhPKs5_X_UqmkkrFd2Ko", fos));
    }
    @Test
    public void testUploadMedia2() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("组织机构.csv");

        System.out.println(weWorkMediaService.uploadMedia(is));
    }
    @Test
    public void testGetResult() {
        System.out.println(weWorkAsyncService.getResult("3_1502182850_482710"));
    }
    @Test
    public void testReplaceDepartment() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("组织机构.csv");

        JSONObject jsonRet = weWorkMediaService.uploadMedia(is);
        System.out.println(jsonRet);
        if (jsonRet.getIntValue("errcode") != 0) {
            return;
        }

        System.out.println(weWorkAsyncService.replaceDepartment(jsonRet.getString("media_id")));
    }

    protected static Logger logger = Logger.getLogger(WeWorkDemo.class);

    @Test
    public void createTag() {
        System.out.println(weWorkTagService.createTag("{" +
                "   \"tagname\": \"标签1\"" +
                "   ,\"tagid\": 1" +
                "}"));
        getTagList();
    }
    @Test
    public void updateTag() {
        System.out.println(weWorkTagService.updateTag("{" +
                "   \"tagname\": \"标签12_2\"" +
                "   ,\"tagid\": 12" +
                "}"));
        getTagList();
    }
    @Test
    public void deleteTag() {
        System.out.println(weWorkTagService.deleteTag("1"));
        getTagList();
    }
    @Test
    public void getTagList() {
        System.out.println(weWorkTagService.getTagList());
    }

    @Test
    public void addTagUsers() {
        System.out.println(weWorkTagService.addTagUsers("1","[\"5170\", \"5328\", \"3630\"]", ""));
        //System.out.println(weWorkTagService.addTagUsers("1","[\"5170\", \"5328\", \"3630\"]", "[\"271\"]"));
        getTagUserList();
    }
    @Test
    public void deleteTagUsers() {
        System.out.println(weWorkTagService.deleteTagUsers("1","[\"5170\", \"5328\"]", "[\"271\"]"));
        getTagUserList();
    }
    @Test
    public void getTagUserList() {
        System.out.println(weWorkTagService.getTagUserList("1"));
    }

    @Test
    public void test() throws IOException {

        System.out.println(WeWorkConfig.CONTACT_SECRET);


    }
}
