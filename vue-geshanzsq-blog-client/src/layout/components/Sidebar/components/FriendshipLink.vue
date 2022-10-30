<template>
  <el-card class="mt20" v-if="dataList.length > 0">
    <template #header>
      <span class="sidebar-card-title">友情链接</span>
    </template>
    <el-link
      class="friendship-link"
      v-for="(friendship, index) in dataList"
      :key="index"
      :href="friendship.linkUrl"
      :underline="false"
      target="_blank"
      >{{ friendship.linkName }}</el-link
    >
  </el-card>
</template>
<script setup>
import { ref } from 'vue'

import { list } from '@/api/blog/friendship'

const dataList = ref([])

async function getDataList() {
  const { data } = await list()
  dataList.value = data
}

getDataList()
</script>

<style lang="scss" scoped>
.friendship-link {
  padding: 5px 10px 5px 0px;
  font-size: 15px;
}
</style>
