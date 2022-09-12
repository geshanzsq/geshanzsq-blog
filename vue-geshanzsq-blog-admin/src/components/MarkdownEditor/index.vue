<template>
  <v-md-editor
    v-model="content"
    :height="height"
    @upload-image="handleUploadImage"
  ></v-md-editor>
</template>
<script setup>
import { computed } from 'vue'

import { upload } from '@/api/blog/picture/picture'
import { getPictureShowUrl } from '@/utils/geshanzsq'

const props = defineProps({
  // 内容
  modelValue: {
    type: String,
    default: ''
  },
  // 高度
  height: {
    type: String,
    default: '450px'
  }
})
// 内容
const emit = defineEmits(null)
const content = computed({
  get() {
    return props.modelValue
  },
  set(value) {
    // 子组件传值给父组件
    emit('update:modelValue', value)
  }
})

// 图片上传
async function handleUploadImage(event, insertImage, files) {
  const formData = new FormData()
  formData.append('file', files)
  const {
    data: { fileName, filePath }
  } = await upload(formData)

  insertImage({
    url: getPictureShowUrl(filePath),
    desc: fileName,
    width: 'auto',
    height: 'auto'
  })
}

/**
 * 插入图片
 */
function insertPicture(url, fileName) {
  //   insertImage({
  //     url: getPictureShowUrl(url),
  //     desc: fileName,
  //     width: 'auto',
  //     height: 'auto'
  //   })
}

defineExpose({
  insertPicture
})
</script>
