<template>
  <div
    v-loading.fullscreen.lock="loading"
    element-loading-text="正在跳转登录中，请稍等..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  ></div>
</template>
<script setup>
import {
  getToken,
  setToken,
  getLoginRedirectPath,
  removeLoginRedirectPath
} from '@/utils/auth'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const loading = ref(true)

function init() {
  let token = getToken()
  if (token === undefined || token === '') {
    token = router.currentRoute.value.query.token
    setToken(token)
  }
  window.location.href = getLoginRedirectPath() || '/'
  removeLoginRedirectPath()
}

init()
</script>
<style lang="scss" scoped></style>
