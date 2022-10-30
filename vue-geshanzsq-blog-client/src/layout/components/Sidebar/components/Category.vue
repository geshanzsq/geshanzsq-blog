<template>
  <el-card class="mt20" v-if="dataList.length > 0">
    <template #header>
      <span class="sidebar-card-title">分类云</span>
    </template>
    <p
      class="category"
      v-for="category in dataList"
      :key="category.id"
      @click="handleCategory(category)"
    >
      <el-icon class="icon"><CaretRight /></el-icon>
      <span class="title"
        >{{ category.categoryName }}（{{ category.articleCount }}）</span
      >
    </p>
  </el-card>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { list } from '@/api/blog/category'
import { scrollTo } from '@/utils/scroll-to'

const router = useRouter()

const dataList = ref([])

async function getDataList() {
  const { data } = await list()
  dataList.value = data
}

/**
 * 点击分类
 */
function handleCategory(category) {
  scrollTo(0, 800)
  router.push('/category/' + category.categoryName)
}

getDataList()
</script>
<style lang="scss" scoped>
.category {
  margin: 0px 10px 10px 0px;
  cursor: pointer;
}

.icon,
.title {
  vertical-align: middle;
}
</style>
