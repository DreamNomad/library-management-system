<template>
  <el-container style="height: 100%">
    <el-header height="6%">
      <el-radio-group v-model="isCollapse">
        <el-radio-button :label="false">展开</el-radio-button>
        <el-radio-button :label="true">收起</el-radio-button>
      </el-radio-group>
      <div>
        <el-button @click="logout" type="info">注销</el-button>
        <span style="color:white" v-text="`登录人：${isAdmin.name}`"></span>
      </div>
    </el-header>
    <el-container style="height: 94%">
      <el-aside width="auto">
        <el-menu
          default-active="Home"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          @select="routeJump"
          :collapse="isCollapse"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-menu-item index="Home">
            <i class="el-icon-menu"></i>
            <span slot="title">首页</span>
          </el-menu-item>
          <el-menu-item index="User" v-if="isAdmin.id === 0 || false">
            <i class="el-icon-menu"></i>
            <span slot="title">用户</span>
          </el-menu-item>
          <el-menu-item index="BookList">
            <i class="el-icon-document"></i>
            <span slot="title">图书</span>
          </el-menu-item>
          <el-menu-item index="UserInfo">
            <i class="el-icon-document"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      isCollapse: false,
      isAdmin: {}
    };
  },
  mounted() {
    this.loginUser();
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    routeJump(index, path) {
      this.$router.push({ name: index });
    },
    loginUser() {
      this.$requests.get("/get-user").then((data) => {
        this.isAdmin = data.data
        sessionStorage.setItem("user-id", this.isAdmin.id);
      });
    },
    logout() {
      let _this = this;
      this.$requests.get("/logout", {
        headers: {
          "delete-token": true
        },
      }).then((data)=>{
        if(data.success){
          this.$router.push({name: "Login"});
        }
      });
    },
  },
};
</script>

<style lang="less">
.el-radio-button__orig-radio:checked + .el-radio-button__inner {
  background-color: #6f7378;
  border-color: #6f7378;
  box-shadow: none;
}

#app > .el-container {
  .el-header {
    background-color: #545c64;
    color: #333;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-container .el-aside::-webkit-scrollbar {
    display: none;
  }

  .el-container .el-aside {
    -ms-overflow-style: none; /* IE 和 Edge */
    scrollbar-width: none; /* Firefox */

    .el-menu {
      height: 100%;
    }
  }
}

// .el-menu-vertical-demo {
//   height: 100%;
// }
</style>