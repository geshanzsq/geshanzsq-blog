<template>
  <el-form :model="editForm" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="name" disabled />
    </el-form-item>
    <el-form-item label="昵称" prop="nickName">
      <el-input v-model="editForm.nickName" />
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="editForm.email" />
    </el-form-item>
    <el-form-item label="手机号码" prop="mobilePhone">
      <el-input v-model="editForm.mobilePhone" />
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-radio-group v-model="editForm.sex">
        <el-radio label="1" border>保密</el-radio>
        <el-radio label="2" border>男</el-radio>
        <el-radio label="3" border>女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="简介" prop="remark">
      <el-input
        type="textarea"
        :rows="5"
        placeholder="自我介绍，不超过 500 字"
        v-model="editForm.remark"
      >
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="handleSave" :loading="loading"
        >保存</el-button
      >
    </el-form-item>
  </el-form>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'

import { getUserInfo, update } from '@/api/auth/user'

const { proxy } = getCurrentInstance()

const name = ref(undefined)
const editForm = ref({})
const loading = ref(false)

/**
 * 获取数据
 */
async function getData() {
  const {
    data: { username, nickName, email, mobilePhone, sex, remark }
  } = await getUserInfo()
  name.value = username
  editForm.value.nickName = nickName
  editForm.value.email = email
  editForm.value.mobilePhone = mobilePhone
  editForm.value.sex = sex + ''
  editForm.value.remark = remark
}

/**
 * 保存
 */
async function handleSave() {
  loading.value = true
  await update(editForm.value)
  proxy.$modal.msgSuccess('保存成功')
  loading.value = false
}

getData()
</script>
<style lang="scss" scoped></style>
