/**
 * Created by wcx on 2017/8/30.
 */

$(function () {
    $('#myTabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
});

new Vue({
    el: "#getUser_content",
    data: {
        userId: "5328",
        userInfo: {},
        loading: false,
        editabled: false
    },
    computed: {
        genderText() {
            //0表示未定义，1表示男性，2表示女性
            const dict = {
                "0": "未定义",
                "1": "男",
                "2": "女"
            };
            return dict[this.userInfo.gender];
        },
        statusText() {
            const dict = {
                "1": "已激活",
                "2": "已禁用",
                "4": "未激活"
            };
            return dict[this.userInfo.status];
        },
        retJsonStr() {
            return JSON.stringify(this.userInfo);
        }
    },
    methods: {
        edit() {
            this.editabled = true;
        },
        submitEdit() {
            this.editabled = false;
        },
        cancelEdit() {
            this.editabled = false;
        },
        query() {
            this.loading = true;
            axios.get("winning/wework/getUser.sdo?userId=" + this.userId).then(response => {
                this.loading = false;
                this.userInfo = response.data;
                this.userInfo.genderText = this.genderText(this.userInfo.gender);
                this.userInfo.statusText = this.statusText(this.userInfo.status);
            }).catch(error => {
                this.loading = false;
                console.log(error);
            });
        }

    }
});