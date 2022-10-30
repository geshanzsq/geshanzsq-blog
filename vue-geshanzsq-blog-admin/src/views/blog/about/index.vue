<template>
  <div class="app-container" v-loading="loading">
    <div>
      <el-button size="mini" type="info" plain @click="handleEditor()">
        <span v-if="!isMarkdownEditor">Markdown</span>
        <span v-else>富文本编辑器</span>
      </el-button>
      <el-button
        type="primary"
        class="picture-library-btn"
        @click="handleEditorPicture"
        >图片库</el-button
      >
    </div>
    <div>
      <markdown-editor
        v-if="isMarkdownEditor"
        ref="markdownEditorRef"
        v-model="editForm.content"
      />
      <wang-editor v-else ref="editorRef" v-model="editForm.content" />
    </div>

    <div class="operator-footer" v-permission="['blog:about:edit']">
      <el-button type="primary" @click="handleSave()">保 存</el-button>
    </div>

    <!-- 编辑器选择图片库 -->
    <picture-library
      title="选择图片"
      @change="handleChangeEditorPicture"
      ref="pictureLibrayEditorRef"
    />
  </div>
</template>
<script setup>
import { computed, getCurrentInstance, ref } from 'vue'

import MarkdownEditor from '@/components/MarkdownEditor'
import WangEditor from '@/components/WangEditor'
import PictureLibrary from '@/components/PictureLibrary'

import { getContent, edit } from '@/api/blog/about'

const { proxy } = getCurrentInstance()

const loading = ref(true)
const editForm = ref({
  editorType: 1,
  content: ''
})
const isMarkdownEditor = computed(() => {
  return editForm.value.editorType === 1
})

/**
 * 获取数据
 */
async function getData() {
  loading.value = true
  const { data } = await getContent()
  editForm.value = data
  if (!editForm.value.content) {
    editForm.value.content = ''
  }
  loading.value = false
}

/**
 * 切换编辑器点击事件
 */
function handleEditor() {
  if (editForm.value.editorType === 1) {
    editForm.value.editorType = 2
  } else {
    editForm.value.editorType = 1
  }
}

/**
 * 编辑器图片选择
 */
function handleEditorPicture() {
  proxy.$refs.pictureLibrayEditorRef.show()
}

/**
 * 编辑器图片选择回调事件
 */
function handleChangeEditorPicture(url) {
  if (!url) {
    return
  }
  if (isMarkdownEditor.value) {
    proxy.$refs.markdownEditorRef.insertPicture(url)
  } else {
    proxy.$refs.editorRef.insertPicture(url)
  }
}

/**
 * 保存
 */
async function handleSave() {
  if (!editForm.value.content) {
    proxy.$modal.msgWarning('请输入内容')
    return
  }
  const {
    data: { id }
  } = await edit(editForm.value)
  editForm.value.id = id
  proxy.$modal.msgSuccess('保存成功')
}

getData()
</script>
<style lang="scss" scoped>
.picture-library-btn {
  float: right;
}

.operator-footer {
  position: fixed;
  bottom: 10px;
  right: 20px;
  z-index: 99999;
}
</style>
