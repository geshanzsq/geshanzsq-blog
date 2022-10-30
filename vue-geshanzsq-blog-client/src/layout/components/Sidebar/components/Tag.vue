<template>
  <el-card class="mt20" v-if="dataList.length > 0">
    <template #header>
      <span class="sidebar-card-title">标签云</span>
    </template>
    <el-tag
      size="large"
      class="tag"
      effect="dark"
      :type="getTagType(index)"
      v-for="(tag, index) in dataList"
      :key="index"
      @click="handleTag(tag)"
      >{{ tag.tagName }}</el-tag
    >
  </el-card>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { list } from '@/api/blog/tag'

const router = useRouter()

const dataList = ref([])

async function getDataList() {
  const { data } = await list()
  dataList.value = data
}

/**
 * 获取标签类型
 */
function getTagType(index) {
  if (index % 4 === 0) {
    return 'primary'
  } else if (index % 4 === 1) {
    return 'success'
  } else if (index % 4 === 2) {
    return 'warning'
  } else if (index % 4 === 3) {
    return 'danger'
  } else {
    return 'warning'
  }
}

/**
 * 点击标签
 */
function handleTag(tag) {
  router.push('/tag/' + tag.tagName)
}

getDataList()
</script>
<style lang="scss" scoped>
.tag {
  margin: 0px 10px 10px 0px;
  cursor: pointer;
}
</style>
