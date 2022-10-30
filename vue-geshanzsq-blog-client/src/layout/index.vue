<template>
  <section class="layout-container">
    <!-- 头部 -->
    <navbar />

    <!-- 中间 -->
    <el-row :gutter="24" :class="isMobile ? 'app-main-mobile' : 'app-main'">
      <!-- 必须添加margin头部或底部距离，否则会出现移动端页面的一闪而过 -->
      <el-col :xs="24" :md="18" :class="isMobile ? 'col-mobile' : ''">
        <!-- 正文内容 -->
        <router-view v-slot="{ Component, route }">
          <transition name="fade-transform" mode="out-in">
            <keep-alive>
              <component :is="Component" :key="route.path" />
            </keep-alive>
          </transition>
        </router-view>
      </el-col>
      <el-col :xs="0" :md="6">
        <!-- 右侧 -->
        <sidebar />
      </el-col>
    </el-row>

    <!-- 底部 -->
    <footer-bottom />

    <!-- 右下角回到顶部 -->
    <el-backtop :right="10" :bottom="20"> </el-backtop>
  </section>
</template>
<script setup>
import { useWindowSize } from '@vueuse/core'
import { computed, ref, watchEffect } from 'vue'
import { useStore } from 'vuex'

import Navbar from './components/Navbar'
import Sidebar from './components/Sidebar'
import FooterBottom from './components/FooterBottom'

const store = useStore()

const { width } = useWindowSize()

const isMobile = ref(
  computed(() => {
    return store.getters['app/isMobile']
  })
)

// 参考 Bootstrap 的响应式设计
const defaultWidth = 768

watchEffect(() => {
  if (width.value - 1 < defaultWidth) {
    store.dispatch('app/toggleDevice', 'mobile')
  } else {
    store.dispatch('app/toggleDevice', 'desktop')
  }
})
</script>
<style lang="scss" scoped>
.layout-container {
  background-color: #f6f6f6;

  .app-main {
    padding: 20px 80px;
    margin: auto 0 !important;
  }
  .app-main-mobile {
    padding: 20px 6px 0px 6px;
    margin: auto 0 !important;
  }
}
</style>
