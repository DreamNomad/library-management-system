<template>
  <div>
    <el-form
      ref="loginForm"
      :model="form"
      :rules="rules"
      label-width="80px"
      class="login-box"
    >
      <h3
        class="login-title"
        v-text="this.isLogin ? '欢迎登录图书管理系统' : '注册'"
      ></h3>
      <el-form-item label="账号" prop="account">
        <el-input type="text" placeholder="请输入账号" v-model="form.account" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          placeholder="请输入密码"
          v-model="form.password"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="onSubmit('loginForm')">{{
          this.isLogin ? "登录" : "去登录"
        }}</el-button>
        <el-button type="info" v-on:click="register('loginForm')">{{
          this.isLogin ? "去注册" : "注册"
        }}</el-button>
      </el-form-item>
      <el-alert :closable="false" type="warning" title="新建的用户默认密码是123456！！！管理员账号：admin 密码：123456"> </el-alert>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Content",
  data() {
    return {
      isLogin: true,
      form: {
        account: "",
        password: "",
      },

      // 表单验证，需要在 el-form-item 元素中增加 prop 属性
      rules: {
        account: [{ required: true, message: "账号不可为空", trigger: "blur" }],
        password: [
          { required: true, message: "密码不可为空", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    onSubmit(formName) {
      if (!this.isLogin) {
        this.isLogin = !this.isLogin;
        return;
      }
      // 为表单绑定验证功能
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$requests.post("/login", this.form).then((data) => {
            if (data.success) {
              localStorage.setItem("token", data.data);
              this.$router.push({ name: "Home" });
            }
          });
        }
      });
    },
    register(formName) {
      if (this.isLogin) {
        this.isLogin = !this.isLogin;
        return;
      }
      // 为表单绑定验证功能
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$requests.post("/register", this.form).then((data) => {
            if (data.success) {
              this.isLogin = true;
            }
          });
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.login-box {
  border: 1px solid #dcdfe6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>