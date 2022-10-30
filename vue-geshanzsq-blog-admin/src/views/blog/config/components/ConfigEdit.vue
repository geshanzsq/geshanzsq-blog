<template>
  <el-dialog
    v-model="visible"
    :title="title"
    width="600px"
    append-to-body
    :before-close="close"
  >
    <el-form
      :model="editForm"
      ref="editFormRef"
      label-width="100px"
      :rules="rules"
    >
      <el-form-item label="名称" prop="thirdName">
        <el-input v-model="editForm.thirdName" placeholder="请输入第三方名称" />
      </el-form-item>
      <el-form-item label="来源" prop="source">
        <el-input v-model="editForm.source" placeholder="请输入第三方来源" />
      </el-form-item>
      <el-form-item label="第三方 id" prop="clientId">
        <el-input v-model="editForm.clientId" placeholder="请输入第三方 id" />
      </el-form-item>
      <el-form-item label="第三方密钥" prop="clientSecret">
        <el-input
          v-model="editForm.clientSecret"
          placeholder="请输入第三方密钥"
        />
      </el-form-item>
      <el-form-item label="登录开启" prop="hasOpenLogin">
        <el-radio-group v-model="editForm.hasOpenLogin">
          <dictionary-radio code="yesNo" />
        </el-radio-group>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'

import { getById, add, update } from '@/api/blog/config'

const { proxy } = getCurrentInstance()

const visible = ref(false)
const title = ref('')
const rules = ref({
  thirdName: [
    { required: true, message: '第三方名称不能为空', trigger: 'blur' }
  ],
  source: [{ required: true, message: '第三方来源不能为空', trigger: 'blur' }],
  clientId: [
    { required: true, message: '第三方 id 不能为空', trigger: 'blur' }
  ],
  clientSecret: [
    { required: true, message: '第三方密钥不能为空', trigger: 'blur' }
  ],
  hasOpenLogin: [
    { required: true, message: '请选择登录状态是否开启', trigger: 'blur' }
  ]
})
const editForm = ref({
  hasOpenLogin: '1'
})

/**
 * 显示弹窗
 */
async function show(id) {
  visible.value = true
  if (id) {
    title.value = '修改配置'
    // 获取信息
    const { data } = await getById(id)
    editForm.value = data
    editForm.value.hasOpenLogin = editForm.value.hasOpenLogin + ''
  } else {
    title.value = '新增配置'
    editForm.value.id = undefined
  }
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

/**
 * 提交数据
 */
const emit = defineEmits(null)
function handleSave() {
  proxy.$refs.editFormRef.validate(async (valid) => {
    if (valid) {
      // 修改
      if (editForm.value.id) {
        await update(editForm.value)
        proxy.$modal.msgSuccess('修改配置成功')
      } else {
        // 新增
        await add(editForm.value)
        proxy.$modal.msgSuccess('新增配置成功')
      }
      emit('fetch-data')
      close()
    }
  })
}

defineExpose({
  show
})
</script>
