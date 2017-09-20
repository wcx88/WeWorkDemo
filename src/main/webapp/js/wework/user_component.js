Vue.component('user-component', {
    props: {userInfo: {}, editabled : false},
    template: `<div>
                <div class="form-group">
                    <div class="col-sm-2">
                        <a href="javascript:;" class="thumbnail fr w100" style="min-height:100px;">
                            <img :src="userInfo.avatar" alt="头像">
                        </a>
                    </div>
                    <div class="col-sm-10">
                        <div>
                            <input class="form-control" v-model="userInfo.name" v-if="editabled"/>
                            <p class="form-control-static f20 fb" v-text="userInfo.name" v-if="!editabled">&nbsp;</p>
                        </div>
                        
                        <div class="">
                            <span>英文名：</span>
                            <input class="form-control" v-model="userInfo.english_name" v-if="editabled"/>
                            <span class="form-control-static" v-text="userInfo.english_name" v-if="!editabled"></span>
                        </div>
                        <div class="">
                            <span>账&emsp;号：</span>
                            <input class="form-control" v-model="userInfo.userid" v-if="editabled"/>
                            <span class="form-control-static" v-text="userInfo.userid" v-if="!editabled"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.gender" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.genderText" v-if="!editabled"></p>
                    </div>
                </div>
                <div class="line mb10"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.mobile" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.mobile" v-if="!editabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">座机：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.telephone" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.telephone" v-if="!editabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.email" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.email" v-if="!editabled"/>
                    </div>
                </div>
                <div class="line mb10"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">部门：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.departmentName" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.departmentName" v-if="!editabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">职务：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.position" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.position" v-if="!editabled"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态：</label>
                    <div class="col-sm-10">
                        <input class="form-control" v-model="userInfo.status" v-if="editabled"/>
                        <p class="form-control-static" v-text="userInfo.statusText" v-if="!editabled"/>
                    </div>
                </div>
    </div>`
});