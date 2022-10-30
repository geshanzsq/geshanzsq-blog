<template>
  <div v-loading="loading">
    <div class="search-header mb20" v-if="searchFlag">
      <div class="search-header-search">搜索</div>
      <div class="search-header-content">{{ searchFlag }}</div>
    </div>
    <div v-if="dataList.length > 0">
      <article-card
        class="mb20"
        v-for="article in dataList"
        :article="article"
        :key="article.articleId"
        :show-sticky="false"
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
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

import ArticleCard from '@/components/Article/ArticleCard'

import { pageSearch } from '@/api/blog/article'

const props = defineProps({
  searchFlag: {
    type: String,
    default: undefined
  },
  title: {
    type: String,
    default: undefined
  },
  categoryName: {
    type: String,
    default: undefined
  },
  tagName: {
    type: String,
    default: undefined
  },
  author: {
    type: String,
    default: undefined
  },
  flag: {
    type: String,
    default: undefined
  }
})

const store = useStore()

const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
// 查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  articleTitle: props.title,
  categoryName: props.categoryName,
  tagName: props.tagName,
  authorNickName: props.author
})
const isMobile = ref(
  computed(() => {
    return store.getters['app/isMobile']
  })
)

async function getPage() {
  loading.value = true
  const {
    data: { total, list }
  } = await pageSearch(queryParams.value)
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
<style lang="scss" scoped>
.search-header {
  height: 40px;
  width: 100%;
  border-bottom: 1px solid #bfbfbf;

  .search-header-search {
    float: left;
    height: 100%;
    width: 100px;
    text-align: center;
    line-height: 40px;
    background-color: #3a6ab5;
    color: white;
  }
  .search-header-content {
    float: left;
    line-height: 40px;
    text-align: center;
    padding-left: 10px;
    color: #484848;
  }
}
</style>
