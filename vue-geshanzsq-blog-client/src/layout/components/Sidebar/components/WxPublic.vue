<template>
  <el-card class="wx-public">
    <el-row>
      <el-col :span="10">
        <el-image fit="cover" class="wx-public-code" :src="wxPublicCode" />
      </el-col>
      <el-col :span="14">
        <p class="title">关注公众号</p>
        <p class="description">不错过每篇技术文章、实用工具、资源干货！</p>
      </el-col>
    </el-row>
    <el-link :underline="false" :href="wxCode" target="_blank">
      <span class="add-wx">点击添加我的微信，加入技术讨论群</span>
    </el-link>
  </el-card>
</template>
<script setup>
import { ref } from 'vue'

const wxPublicCode = ref(require('@/assets/images/code/wxPublicCode.jpg'))
const wxCode = ref(require('@/assets/images/code/wxCode.jpg'))
</script>
<style lang="scss" scoped>
.wx-public {
  background-color: #009688;
  .wx-public-code {
    width: 90%;
    height: 90%;
  }
  .title {
    font-size: 18px;
    font-weight: bold;
    color: white;
  }
  .description {
    font-size: 12px;
    color: white;
  }
  .add-wx {
    color: orange;
    font-size: 14px;
  }
}
</style>
