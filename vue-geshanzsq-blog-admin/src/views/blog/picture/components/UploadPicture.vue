<template>
  <el-dialog
    v-model="visible"
    title="上传图片"
    width="500px"
    append-to-body
    :before-close="close"
  >
    <el-upload
      drag
      :action="uploadFileUrl + '?categoryId=' + catetoryId"
      :headers="headers"
      :on-error="uploadError"
      :on-success="uploadSuccess"
      ref="uploadPictureRef"
      accept=".jpg,.jpeg,.png,.gif,.ico"
      multiple
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">将文件拖到此处，或<em> 点击上传</em></div>
      <template #tip>
        <div class="el-upload__tip tip">
          只能上传图片格式的文件，且不超过 10Mb
        </div>
      </template>
    </el-upload>
  </el-dialog>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'
import { getToken } from '@/utils/auth'
import { tokenConfig } from '@/config/network.config'

const { proxy } = getCurrentInstance()

const visible = ref(false)
const catetoryId = ref(undefined)
// 图片上传地址
const uploadFileUrl = ref(process.env.VUE_APP_BASE_API + '/blog/picture/upload')
const headers = ref({
  Authorization: tokenConfig.prefix + getToken()
})
/**
 * 显示弹窗
 */
async function show(categoryId) {
  visible.value = true
  catetoryId.value = categoryId
}

/**
 * 关闭
 */
function close() {
  visible.value = false
  // 关闭时清空之前上传记录
  proxy.$refs.uploadPictureRef.clearFiles()
}

/**
 * 图片上传失败
 */
function uploadError() {
  proxy.$modal.msgError('图片上传失败')
}

/**
 * 图片上传成功
 */
const emit = defineEmits(null)
function uploadSuccess(response) {
  if (response.code !== 200) {
    const message = response.message || '图片上传失败'
    proxy.$modal.msgError(message)
  } else {
    proxy.$modal.msgSuccess('图片上传成功')
    emit('fetch-data')
    close()
  }
}

defineExpose({
  show
})
</script>
<style scoped>
.tip {
  text-align: center;
}
</style>
