<template>
  <div>
    <el-card
      :body-style="{ padding: '10px' }"
      v-loading="archiveLoading"
      v-if="archiveList.length > 0"
    >
      <el-tag
        size="large"
        class="tag-name"
        round
        v-for="(archive, index) in archiveList"
        :key="index"
        :effect="isChoose(archive) ? 'dark' : 'plain'"
        :type="isChoose(archive) ? 'primary' : 'info'"
        @click="handleArchive(archive)"
        >{{ archive }}</el-tag
      >
    </el-card>

    <!-- 文章列表 -->
    <div class="mt20" v-loading="loading">
      <div v-if="totalCount > 0">
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
  </div>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

import ArticleCard from '@/components/Article/ArticleCard'

import { getGmtPublishLine } from '@/api/blog/archive'
import { pageSearch } from '@/api/blog/article'

const store = useStore()

const archiveLoading = ref(true)
const archiveList = ref([])
const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
// 查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  archiveGmPublishLine: undefined
})
const isMobile = ref(
  computed(() => {
    return store.getters['app/isMobile']
  })
)

/**
 * 获取时间线数据
 */
async function getGmtPublishLineList() {
  archiveLoading.value = true
  const { data } = await getGmtPublishLine()
  archiveList.value = data
  archiveLoading.value = false
  if (archiveList.value.length > 0) {
    queryParams.value.archiveGmPublishLine = archiveList.value[0]
  }
  await getPage()
}

/**
 * 获取数据
 */
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
 * 是否选中
 */
function isChoose(archive) {
  return archive === queryParams.value.archiveGmPublishLine
}

/**
 * 分类点击事件
 */
function handleArchive(archive) {
  queryParams.value.archiveGmPublishLine = archive
  dataList.value = []
  queryParams.value.pageNum = 1
  getPage()
}

/**
 * 点击加载更多
 */
function handleMore() {
  queryParams.value.pageNum++
  getPage()
}

getGmtPublishLineList()
</script>
<style lang="scss" scoped>
.tag-name {
  margin: 5px 5px 5px 0px;
  cursor: pointer;
}
</style>
