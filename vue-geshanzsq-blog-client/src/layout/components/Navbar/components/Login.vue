<template>
  <el-dialog
    v-model="visible"
    :width="isMobile ? '100%' : '50%'"
    append-to-body
    :before-close="close"
  >
    <template #header>
      <div class="login-title">登录</div>
    </template>
    <div
      class="login-content"
      v-loading="loading"
      :element-loading-text="loadingText"
    >
      第三方账号登录：
      <el-tooltip
        v-for="config in dataList"
        :key="config.id"
        :content="config.thirdName"
        placement="bottom"
      >
        <el-button type="primary" round @click="handleLogin(config.source)">{{
          config.source
        }}</el-button>
      </el-tooltip>
    </div>
  </el-dialog>
</template>
<script setup>
import { ref } from 'vue'
import { useStore } from 'vuex'
import { list } from '@/api/blog/config'
import { getLoginUrl } from '@/api/auth/login'

const store = useStore()

const visible = ref(false)

const isMobile = store.getters['app/isMobile']
const loading = ref(true)
const loadingText = ref('')
const dataList = ref([])

/**
 * 显示弹窗
 */
async function show() {
  visible.value = true
  getDataList()
}

/**
 * 获取登录配置
 */
async function getDataList() {
  loading.value = true
  const { data } = await list()
  dataList.value = data
  loading.value = false
}

/**
 * 关闭
 */
function close() {
  visible.value = false
}

/**
 * 登录
 */
async function handleLogin(source) {
  loading.value = true
  loadingText.value = '正在登录跳转中，请稍等...'
  const {
    data: { url }
  } = await getLoginUrl(source)
  window.location.href = url
}

defineExpose({
  show
})
</script>
<style lang="scss" scoped>
.login-title {
  text-align: center;
  font-size: 23px;
  font-weight: bold;
  color: #555;
  border-bottom: 1px solid #dcdfe6;
  padding-bottom: 10px;
}

.login-content {
  width: 100%;
  text-align: center;

  .icon {
    font-size: 30px;
    cursor: pointer;
    margin: auto 5px;
  }
}
</style>
