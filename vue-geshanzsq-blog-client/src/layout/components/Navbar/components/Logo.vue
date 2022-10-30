<template>
  <div class="logo-title">
    <el-image
      v-if="showImage"
      class="logo-image"
      fit="cover"
      :src="logo"
    ></el-image>
    <el-link :underline="false" href="/" class="title">{{ title }}</el-link>
  </div>
</template>
<script setup>
import logo from '@/assets/logo/logo.png'
import { title } from '@/config/setting.config'

defineProps({
  showImage: {
    type: Boolean,
    default: true
  }
})
</script>
<style lang="scss" scoped>
.logo-title {
  .logo-image {
    height: 40px;
    line-height: 100%;
    border-radius: 50%;
    vertical-align: middle;
  }
  .title {
    margin-left: 10px;
    font-size: 22px;
    color: #00a7eb !important;
    font-weight: bold;
    vertical-align: middle;
  }
}
</style>
