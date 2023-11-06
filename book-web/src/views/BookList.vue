<template>
  <div>
    <el-row>
      <el-button
        type="primary"
        @click="
          dialogFormVisible = true;
          form = {};
          isAdd = true;
        "
        >新增</el-button
      >
    </el-row>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="创建日期" width="200px">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改日期" width="200px">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="图书分类">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.type }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="图书名称" width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>图书名称: {{ scope.row.title }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.title }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="图书价格">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.price? scope.row.price+"￥":"--" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图书介绍">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.desc || "--" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="新增图书" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="图书名称" :label-width="formLabelWidth">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图书价格" :label-width="formLabelWidth">
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图书介绍" :label-width="formLabelWidth">
          <el-input v-model="form.desc" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      dialogFormVisible: false,
      form: {},
      formLabelWidth: "120px",
      isAdd: true,
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    handleEdit(index, row) {
      this.form = this.tableData[index];
      this.dialogFormVisible = true;
      this.isAdd = false;
      console.log(index, row);
    },
    handleDelete(index, row) {
      let id = this.tableData[index].id;
      this.$requests.delete(`/book/${id}`).then((data) => {
        if (data.success) {
          this.getList();
          this.dialogFormVisible = false;
        }
      });
      console.log(index, row);
    },
    getList() {
      this.$requests.get("/book").then((data) => {
        this.tableData = data.data;
      });
    },
    add() {
      if (this.isAdd) {
        this.$requests.post("/book", this.form).then((data) => {
          if (data.success) {
            this.getList();
            this.dialogFormVisible = false;
          }
        });
        return;
      }
      this.$requests.put("/book", this.form).then((data) => {
        if (data.success) {
          this.getList();
          this.dialogFormVisible = false;
        }
      });
    },
  },
};
</script>

<style>
</style>