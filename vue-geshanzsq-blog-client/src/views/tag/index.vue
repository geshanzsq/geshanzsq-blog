<template>
  <div>
    <el-card
      :body-style="{ padding: '10px' }"
      v-loading="tagLoading"
      v-if="tagList.length > 0"
    >
      <el-tag
        size="large"
        class="tag-name"
        round
        v-for="(tag, index) in tagList"
        :key="index"
        :effect="isChoose(tag) ? 'dark' : 'plain'"
        :type="isChoose(tag) ? 'primary' : 'info'"
        @click="handleTag(tag)"
        >{{ tag.tagName }}</el-tag
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

import { list } from '@/api/blog/tag'
import { pageSearch } from '@/api/blog/article'

const store = useStore()

const tagList = ref([])
const tagLoading = ref(true)
const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
// 查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  tagName: undefined
})
const isMobile = ref(
  computed(() => {
    return store.getters['app/isMobile']
  })
)

/**
 * 获取标签数据
 */
async function getTagList() {
  tagLoading.value = true
  const { data } = await list()
  tagList.value = data
  tagLoading.value = false
  if (tagList.value.length > 0) {
    queryParams.value.tagName = tagList.value[0].tagName
  }
  await getPage()
}

/**
 * 是否选中
 */
function isChoose(tag) {
  return tag.tagName === queryParams.value.tagName
}

/**
 * 标签点击事件
 */
function handleTag(tag) {
  queryParams.value.tagName = tag.tagName
  dataList.value = []
  queryParams.value.pageNum = 1
  getPage()
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
 * 点击加载更多
 */
function handleMore() {
  queryParams.value.pageNum++
  getPage()
}

getTagList()
</script>
<style lang="scss" scoped>
.tag-name {
  margin: 5px 5px 5px 0px;
  cursor: pointer;
}
</style>
