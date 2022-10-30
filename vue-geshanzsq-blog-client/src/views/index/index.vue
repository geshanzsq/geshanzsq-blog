<template>
  <div>
    <!-- 推荐 -->
    <recommend />

    <!-- 文章列表 -->
    <div :class="isMobile ? '' : 'mt20'" v-loading="loading">
      <div v-if="totalCount > 0">
        <article-card
          class="mb20"
          v-for="article in dataList"
          :article="article"
          :key="article.articleId"
        />
        <pagination
          v-if="totalCount > 0 && !isMobile"
          :total="totalCount"
          v-model:page="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="getPage"
        />
        <div v-if="totalCount > 0 && isMobile" class="mobile-page">
          <span
            class="click-more"
            v-if="totalCount > queryParams.pageNum * queryParams.pageSize"
            @click="handleMore"
            >点击加载更多</span
          >
          <span class="no-data" v-else>暂无更多数据</span>
        </div>
      </div>
      <el-empty v-else description="暂无数据" />
    </div>
  </div>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

import Recommend from './components/Recommend'
import ArticleCard from '@/components/Article/ArticleCard'

import { page } from '@/api/blog/article'

const store = useStore()

const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
// 查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10
})
const isMobile = ref(
  computed(() => {
    return store.getters['app/isMobile']
  })
)

/**
 * 获取数据
 */
async function getPage() {
  loading.value = true
  const {
    data: { total, list }
  } = await page(queryParams.value)
  totalCount.value = parseInt(total)
  if (isMobile.value) {
    list.forEach((article) => {
      dataList.value.push(article)
    })
  } else {
    dataList.value = list
  }
  loading.value = false
}

/**
 * 点击加载更多
 */
function handleMore() {
  queryParams.value.pageNum++
  getPage()
}

getPage()
</script>
<style lang="scss" scoped></style>
