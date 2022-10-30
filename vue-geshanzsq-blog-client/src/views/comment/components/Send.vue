<template>
  <div :class="parentId > 0 ? 'parent-send-comment' : ''">
    <el-input
      type="textarea"
      :rows="4"
      :placeholder="placeholder"
      v-model="editForm.commentContent"
    />
    <div class="operation-parent">
      <div class="operation">
        <el-button type="info" v-if="parentId > 0" @click="handleCancelPublish"
          >取消发表</el-button
        >
        <el-button type="primary" @click="handlePublish" :loading="loading">
          发表
        </el-button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'
import { useStore } from 'vuex'

import { add } from '@/api/blog/comment'

const props = defineProps({
  articleId: {
    type: Number || String,
    default: 0
  },
  parentId: {
    type: Number || String,
    default: 0
  },
  placeholder: {
    type: String,
    default: '既然来了，那就留些足迹吧！支持 Markdown 格式哦'
  }
})

const store = useStore()
const { proxy } = getCurrentInstance()
const emit = defineEmits(null)

const editForm = ref({
  articleId: props.articleId,
  parentId: props.parentId,
  commentContent: undefined
})
const loading = ref(false)
const isLogin = ref(store.getters['user/isLogin'])

/**
 * 取消发表
 */
function handleCancelPublish() {
  emit('cancel')
}

/**
 * 发表评论
 */
async function handlePublish() {
  if (!isLogin.value) {
    proxy.$modal.msgError('登录后才能发表评论哦')
    return
  }
  if (!editForm.value.commentContent) {
    proxy.$modal.msgError('请输入评论内容')
    return
  }
  loading.value = true
  await add(editForm.value)
  proxy.$modal.msgSuccess('发表成功')
  loading.value = false
  emit('success')
}
</script>
<style lang="scss" scoped>
.parent-send-comment {
  margin-top: 10px;
}

/* 清除高度坍塌 */
.operation-parent:after {
  content: '';
  display: block;
  clear: both;
  width: 0;
  height: 0;
}

.operation {
  margin-top: 10px;
  margin-bottom: 10px;
  float: right;
}
</style>
