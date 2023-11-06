<template>
  <el-form
    style="width: 50%; margin: 0 auto"
    label-position="right"
    label-width="80px"
    :model="formLabelAlign"
  >
    <el-form-item label="账号">
      <el-input v-model="formLabelAlign.account" disabled></el-input>
    </el-form-item>
    <el-form-item label="名称">
      <el-input v-model="formLabelAlign.name" disabled></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <el-input
        v-if="!formLabelAlign.sex"
        v-model="defaultValue"
        disabled
      ></el-input>
      <el-input v-else v-model="formLabelAlign.sex" disabled></el-input>
    </el-form-item>
    <el-form-item label="出生日期">
      <el-input
        v-if="!formLabelAlign.birth"
        v-model="defaultValue"
        disabled
      ></el-input>
      <el-input v-else v-model="formLabelAlign.birth" disabled></el-input>
    </el-form-item>
    <el-form-item label="电子邮箱">
      <el-input
        v-if="!formLabelAlign.mail"
        v-model="defaultValue"
        disabled
      ></el-input>
      <el-input v-else v-model="formLabelAlign.mail" disabled></el-input>
    </el-form-item>
    <el-form-item label="手机号码">
      <el-input
        v-if="!formLabelAlign.phone"
        v-model="defaultValue"
        disabled
      ></el-input>
      <el-input v-else v-model="formLabelAlign.phone" disabled></el-input>
    </el-form-item>
    <el-form-item label="送货地址">
      <el-input
        v-if="!formLabelAlign.site"
        v-model="defaultValue"
        disabled
      ></el-input>
      <el-input v-else v-model="formLabelAlign.site" disabled></el-input>
    </el-form-item>
    <el-form-item label="创建时间">
      <el-input v-model="formLabelAlign.createTime" disabled></el-input>
    </el-form-item>
    <el-form-item label="修改时间">
      <el-input v-model="formLabelAlign.updateTime" disabled></el-input>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      formLabelAlign: {},
      defaultValue: "--",
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      let id = sessionStorage.getItem("user-id");
      this.$requests.get(`/user/${id}`).then((data) => {
        if (data.success) {
          this.formLabelAlign = data.data;
        }
      });
    },
  },
};
</script>

<style>
</style>