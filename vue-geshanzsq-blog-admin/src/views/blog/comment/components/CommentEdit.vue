<template>
  <el-dialog
    v-model="visible"
    title="评论审核"
    width="600px"
    append-to-body
    :before-close="close"
  >
    <el-form
      :model="editForm"
      ref="editFormRef"
      label-width="80px"
      :rules="rules"
    >
      <el-form-item label="评论内容" prop="commentContent">
        <div v-html="comment.commentContent"></div>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="comment.username" disabled />
      </el-form-item>
      <el-form-item label="昵称" prop="nickName">
        <el-input v-model="comment.nickName" disabled />
      </el-form-item>

      <el-form-item label="备注" prop="remark">
        <el-input v-model="editForm.remark" placeholder="请输入审核备注" />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="warning" @click="handleAudit(3)">驳 回</el-button>
        <el-button type="primary" @click="handleAudit(2)">通 过</el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'

import { getById, audit } from '@/api/blog/comment'

const { proxy } = getCurrentInstance()

const visible = ref(false)
const rules = ref({})
const comment = ref({})
const editForm = ref({
  id: undefined,
  status: undefined,
  remark: undefined
})

/**
 * 显示弹窗
 */
async function show(id) {
  visible.value = true
  editForm.value.id = id
  const { data } = await getById(id)
  comment.value = data
  editForm.value.remark = data.remark
}

/**
 * 审核
 */
const emit = defineEmits(null)
async function handleAudit(status) {
  editForm.value.status = status
  await audit(editForm.value)
  proxy.$modal.msgSuccess('评论审核成功')
  emit('fetch-data')
  close()
}

/**
 * 关闭
 */
function close() {
  visible.value = false
  reset()
}

/**
 * 重置
 */
function reset() {
  proxy.resetForm('editFormRef')
}

defineExpose({
  show
})
</script>
