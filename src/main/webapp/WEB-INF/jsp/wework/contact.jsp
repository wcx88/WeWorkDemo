<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/include/page_init.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>通讯录</title>
    <jsp:include page="/include/head_init.jsp"></jsp:include>

    <jsp:include page="/include/inc_resources.jsp"></jsp:include>


    <style type="text/css">
        .container .result-area {
            min-height: 60px;
            border-radius: 6px;
            padding: 20px 25px 20px 12px;
            background-color: #eee;
            word-break: break-all;
        }

        .container .btn-copy {
            position: absolute;
            top: 5px;
            right: 20px;
        }

        .el-tree .el-tree-node__label {
            width: 150px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>

<div class="container" id="app">
    <div class="panel panel-primary">
        <div class="panel-heading">
            通讯录
        </div>
        <div class="panel-body" v-loading="loading" element-loading-text="正在查询中...">
            <el-row>
                <el-col :span="6">
                    <el-input
                            placeholder="搜索成员、部门"
                            v-model="filterText">
                    </el-input>

                    <el-tree
                            class="filter-tree h500 overflow_a"
                            :data="departmentTree"
                            :props="defaultProps"
                            default-expand-all
                            :filter-node-method="filterNode"
                            @node-click="clickDepartmentTree"
                            :highlight-current="true"
                            :expand-on-click-node="false"
                            ref="tree">
                    </el-tree>
                </el-col>
                <el-col :span="18">
                    <div class="grid-content"  v-loading="loadingUserList" element-loading-text="正在查询中...">
                        <div v-show="showType == 1">
                            <div class="p15" role="alert" v-text="departmentName">...</div>
                            <el-table
                                :data="userList"
                                class="w h500">
                            <el-table-column
                                    prop="name"
                                    label="姓名"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    prop="position"
                                    label="职务"
                                    width="140">
                            </el-table-column>
                            <el-table-column
                                    prop="departmentName"
                                    label="部门"
                                    width="150">
                            </el-table-column>
                            <el-table-column
                                    prop="mobile"
                                    label="手机"
                                    width="140">
                            </el-table-column>
                            <el-table-column
                                    prop="email"
                                    label="邮箱">
                            </el-table-column>
                            <el-table-column
                                    prop=""
                                    label="操作"
                                    width="100">
                                <template scope="scope">
                                <el-button icon="edit"
                                        @click.native.prevent="editRow(scope.$index, userList)"
                                        type="text"
                                        size="small">
                                </el-button>
                                <el-button icon="delete"
                                        @click.native.prevent="deleteRow(scope.$index, userList)"
                                        type="text"
                                        size="small">
                                </el-button>
                                <el-button icon="view"
                                        @click.native.prevent="viewRow(scope.$index, userList)"
                                        type="text"
                                        size="small">
                                </el-button>
                            </template>
                            </el-table-column>
                        </el-table>
                        </div>
                        <div v-show="showType == 2">
                            <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <el-button icon="d-arrow-left" @click="backFromViewUser">返回</el-button>
                                    <button type="button" @click="editUser" class="btn btn-default">编辑</button>
                                    <button type="button" @click="submitEditUser" class="btn btn-default">确定</button>
                                    <button type="button" @click="cancelEditUser" class="btn btn-default">取消</button>
                                </div>
                            </div>
                            <user-component :user-info="userInfo" :editabled="editabled"></user-component>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <el-button icon="d-arrow-left" @click="backFromViewUser">返回</el-button>
                                    <button type="button" @click="editUser" class="btn btn-default">编辑</button>
                                    <button type="button" @click="submitEditUser" class="btn btn-default">确定</button>
                                    <button type="button" @click="cancelEditUser" class="btn btn-default">取消</button>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</div>

</body>
<script src="${contextPath}/js/wework/user_component.js?v=1.2"></script>
<script src="${contextPath}/js/wework/contact.js?v=1.3"></script>
</html>
