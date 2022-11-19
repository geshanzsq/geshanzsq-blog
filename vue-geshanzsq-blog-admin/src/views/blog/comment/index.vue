<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryFormRef" :inline="true">
      <el-form-item label="状态" prop="status">
        <el-select
          style="width: 240px"
          v-model="queryParams.status"
          placeholder="审核状态"
          @change="handleQuery"
          clearable
        >
          <dictionary-option code="blogCommentStatus" /> </el-select
      ></el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="handleResetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-permission="['blog:comment:delete']"
          >删除</el-button
        >
      </el-col>
    </el-row>

    <el-table
      border
      v-loading="loading"
      :data="dataList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" prop="id" width="180" align="center" />
      <el-table-column
        label="用户名"
        prop="username"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="昵称"
        prop="nickName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="评论内容"
        prop="commentContent"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="备注"
        prop="remark"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="状态"
        align="center"
        prop="userType"
        :show-overflow-tooltip="true"
      >
        <template #default="{ row }">
          <dictionary-tag
            code="blogCommentStatus"
            :value="row.status"
          ></dictionary-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="提交时间"
        align="center"
        prop="gmtCreate"
        width="160"
      >
        <template #default="{ row }">
          <span>{{ parseTime(row.gmtCreate) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="160"
      >
        <template #default="{ row }">
          <el-button
            type="text"
            icon="Edit"
            @click="handleAudit(row)"
            v-permission="['blog:comment:audit']"
            >审核</el-button
          >
          <el-button
            type="text"
            icon="Delete"
            @click="handleDelete(row)"
            v-permission="['blog:comment:delete']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="totalCount > 0"
      :total="totalCount"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getPage"
    />

    <!-- 审核弹窗 -->
    <edit @fetch-data="handleQuery" ref="editRef"></edit>
  </div>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'

import Edit from './components/CommentEdit'

import { page, remove } from '@/api/blog/comment'

const { proxy } = getCurrentInstance()

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  status: undefined
})
const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)

/**
 * 获取数据
 */
async function getPage() {
  loading.value = true
  const {
    data: { total, list }
  } = await page(queryParams.value)
  totalCount.value = parseInt(total)
  dataList.value = list
  loading.value = false
}

/**
 * 搜索
 */
function handleQuery() {
  getPage()
}

/**
 * 重置
 */
function handleResetQuery() {
  proxy.resetForm('queryFormRef')
  handleQuery()
}

/**
 * 选择条数
 */
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}

/**
 * 审核
 */
function handleAudit(row) {
  proxy.$refs.editRef.show(row.id)
}

/**
 * 删除按钮操作
 */
async function handleDelete(row) {
  const deleteIds = row.id || ids.value
  proxy.$modal
    .confirm('是否确认删除评论编号为【' + deleteIds + '】的数据项?')
    .then(async function () {
      await remove(deleteIds)
      proxy.$modal.msgSuccess('删除评论成功')
      handleQuery()
    })
}

getPage()
</script>
<style lang="scss" scoped></style>
