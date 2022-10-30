<template>
  <div
    v-loading.fullscreen.lock="loading"
    element-loading-text="正在退出登录中，请稍等..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
    <el-dropdown @command="handleUserDropdown">
      <el-image
        :src="
          isLogin
            ? avatar
              ? getPictureShowUrl(avatar)
              : userDefaultAvatar
            : userNoLoginAvatar
        "
        class="user-avatar"
      >
        <template #error>
          <div class="image-slot">
            <el-image :src="userDefaultAvatar" class="user-avatar"> </el-image>
          </div>
        </template>
      </el-image>
      <template #dropdown>
        <el-dropdown-menu v-if="isLogin">
          <el-dropdown-item command="user" divided>个人中心</el-dropdown-item>
          <el-dropdown-item command="collect" divided
            >我的收藏</el-dropdown-item
          >
          <el-dropdown-item command="like" divided>我的点赞</el-dropdown-item>
          <el-dropdown-item command="footprint" divided
            >我的足迹</el-dropdown-item
          >
          <el-dropdown-item command="comment" divided
            >我的评论</el-dropdown-item
          >
          <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
        </el-dropdown-menu>
        <el-dropdown-menu v-else>
          <el-dropdown-item command="login">登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>

    <!-- 登录弹窗 -->
    <login ref="loginRef" />

    <!-- 用户信息抽屉 -->
    <user-drawer ref="userDrawerRef" />
  </div>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import Login from './Login'
import UserDrawer from '@/views/user'
import userDefaultAvatar from '@/assets/images/avatar/userDefaultAvatar.jpg'
import userNoLoginAvatar from '@/assets/images/avatar/userNoLoginAvatar.png'

const { proxy } = getCurrentInstance()
const store = useStore()
const router = useRouter()

const avatar = ref(store.getters['user/avatar'])
const isLogin = ref(store.getters['user/isLogin'])
const loading = ref(false)

/**
 * 用户下拉菜单的点击事件
 */
async function handleUserDropdown(command) {
  if (command === 'login') {
    // 登录
    proxy.$refs.loginRef.show()
  } else if (command === 'logout') {
    // 退出
    loading.value = true
    await store.dispatch('user/logout')
    loading.value = false
    window.location.href = router.currentRoute.value.fullPath
  } else {
    // 显示用户抽屉信息
    proxy.$refs.userDrawerRef.show(command)
  }
}

/**
 * 初始化
 */
async function init() {
  try {
    // 登录，获取用户信息
    if (isLogin.value) {
      await store.dispatch('user/getUserInfo')
      avatar.value = store.getters['user/avatar']
    }
  } catch (err) {
    await store.dispatch('user/logout')
  }
}

init()
</script>
<style lang="scss" scoped>
.user-avatar {
  width: 35px;
  height: 35px;
  border-radius: 50%;
}
</style>
