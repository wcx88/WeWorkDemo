/**
 * Created by wcx on 2017/8/30.
 */


new Vue({
    //el: "#app",
    data() {
        return {
            userList: [],
            departmentList: [],
            departmentId: null,

            filterText: '',
            departmentTree: [],
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            loading: false,
            loadingUserList: false,
            userInfo: {},
            editabled: false,
            showType: 1
        };
    },
    computed: {
        departmentMap() {
            let mapHash = {};
            const records = this.departmentList;
            const idField = "id";
            // 索引化数组，为后续取父节点做好准备
            for (let i = 0, len = records.length; i < len; i++) {
                mapHash[records[i][idField]] = records[i];
            }
            return mapHash;
        },
        departmentName() {
            const departmentInfo = this.departmentMap[this.departmentId];
            if(!departmentInfo) return "";
            return departmentInfo['name'];
        }
    },
    created() {
        // this.$nextTick(() => {
        // });
        this.loading = true;
        axios.get("winning/wework/getDepartmentList.sdo?departmentId=2").then(response => {
            this.loading = false;
            const retData = response.data;
            if (retData['errcode'] != 0) {
                console.error(retData);
                return;
            }

            this.departmentList = retData['department'];
            this.departmentTree = this.transTreeData(this.departmentList, 'id', 'parentid', 'children');
        }).catch(error => {
            this.loading = false;
            console.error(error);
        });
    },
    watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        }
    },

    methods: {
        /**
         * 过滤树结点
         * @param value
         * @param data
         * @returns {boolean}
         */
        filterNode(value, data) {
            if (!value) return true;
            return (data['name'].indexOf(value) > -1) || (data['name'].indexOf(value) > -1);
        },
        /**
         * 转树状结构
         * @param   records         源数组
         * @param   idField         id的字段名
         * @param   pidField        父id的字段名
         * @param   childrenField    children的字段名
         * @return  {Array}     数组
         */
        transTreeData(records, idField, pidField, childrenField) {
            let mapHash = {};
            // 索引化数组，为后续取父节点做好准备
            for (let i = 0, len = records.length; i < len; i++) {
                mapHash[records[i][idField]] = records[i];
            }

            let recordsTree = [];
            for (let i = 0, len = records.length; i < len; i++) {
                let node = records[i];
                let pid = node[pidField];

                // 取父节点
                let parentNode = mapHash[pid];

                // 如果不存在父节点则是它根节点，直接放入返回数组
                if (!parentNode) {
                    recordsTree.push(node);
                    continue;
                }

                // 如果存在父节点则是它根节点，放入父节点的children数组
                if (!parentNode[childrenField]) {
                    parentNode[childrenField] = [];
                }
                parentNode[childrenField].push(node);
            }
            return recordsTree;
        },
        genderText(gender) {
            //0表示未定义，1表示男性，2表示女性
            const dict = {
                "0": "未定义",
                "1": "男",
                "2": "女"
            };
            return dict[gender];
        },
        departmentText(department) {
            return this.departmentMap[department];
        },
        statusText(status) {
            const dict = {
                "1": "已激活",
                "2": "已禁用",
                "4": "未激活"
            };
            return dict[status];
        },
        transUserList(userList) {
            for (let i = 0, len = userList.length; i < len; i++) {
                let userInfo = userList[i];
                const arrTemp = userInfo['department'];
                const departmentId = arrTemp[arrTemp.length - 1];
                userInfo['departmentName'] = this.departmentMap[departmentId]['name'];
                userInfo['genderText'] = this.genderText(userInfo['gender']);
                userInfo['statusText'] = this.statusText(userInfo['status']);
            }
            return userList;
        },
        clickDepartmentTree(nodeData) {
            if(this.departmentId === nodeData['id']) return;
            this.showType = 1;
            this.departmentId = nodeData['id'];
            this.loadingUserList = true;
            axios.get("winning/wework/getUserList.sdo?departmentId=" + nodeData['id']).then(response => {
                this.loadingUserList = false;
                const retData = response.data;
                if (retData['errcode'] != 0) {
                    console.error(retData);
                    return;
                }

                this.userList = this.transUserList(retData['userlist']);
            }).catch(error => {
                this.loadingUserList = false;
                console.error(error);
            });
        },
        editUser() {
            this.editabled = true;
        },
        submitEditUser() {
            this.editabled = false;
        },
        cancelEditUser() {
            this.editabled = false;
        },
        backFromViewUser() {
            this.showType = 1;
        },
        viewRow(index, userList) {
            this.userInfo = userList[index];
            this.showType = 2;
        }
    }
}).$mount("#app");
