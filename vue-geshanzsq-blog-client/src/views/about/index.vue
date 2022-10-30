<template>
  <div v-loading="loading">
    <el-card>
      <div v-html="content" v-if="content" v-highlight class="content"></div>
      <el-empty v-else description="暂无数据" />
    </el-card>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { getContent } from '@/api/blog/about'

const content = ref('')
const loading = ref(true)

/**
 * 获取数据
 */
async function getData() {
  loading.value = true
  const { data } = await getContent()
  content.value = data.content
  loading.value = false
}

getData()
</script>
<style lang="scss" scoped>
/*设置图片，以防过大*/
.content >>> img {
  max-width: 100%;
  height: auto;
  cursor: pointer;
}
</style>
