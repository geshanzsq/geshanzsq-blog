<template>
  <div>
    <div v-loading="loading" v-if="totalCount > 0">
      <div v-for="article in dataList" :key="article.id">
        <div class="article-time">{{ article.gmtPublish }}</div>
        <router-link :to="'/article/' + article.id" target="_blank">
          <div class="article">
            <el-image
              lazy
              fit="cover"
              class="article-image"
              :src="getPictureShowUrl(article.coverUrl)"
            ></el-image>
            <div class="title">
              <span class="article-title">{{ article.articleTitle }}</span>
            </div>
          </div>
        </router-link>
      </div>
      <div class="more">
        <span class="click-more" v-if="showMore" @click="handleMore"
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

import { page as pageCollect } from '@/api/blog/collect'
import { page as pageLike } from '@/api/blog/like'
import { page as pageFootprint } from '@/api/blog/footprint'

const props = defineProps({
  activeName: {
    type: String,
    default: ''
  }
})

const totalCount = ref(0)
const dataList = ref([])
const loading = ref(true)
const type = ref(props.activeName)
const queryParams = ref({
  pageNum: 1,
  pageSize: 10
})
// 是否显示加载更多
const showMore = computed(() => {
  return (
    totalCount.value > queryParams.value.pageNum * queryParams.value.pageSize
  )
})

/**
 * 获取文章数据
 */
async function getArticleList() {
  loading.value = true
  // 收藏
  if (type.value === 'collect') {
    const {
      data: { total, list }
    } = await pageCollect(queryParams.value)
    totalCount.value = total
    list.forEach((article) => {
      dataList.value.push(article)
    })
  } else if (type.value === 'like') {
    // 点赞
    const {
      data: { total, list }
    } = await pageLike(queryParams.value)
    totalCount.value = total
    list.forEach((article) => {
      dataList.value.push(article)
    })
  } else if (type.value === 'footprint') {
    // 足迹
    const {
      data: { total, list }
    } = await pageFootprint(queryParams.value)
    totalCount.value = total
    list.forEach((article) => {
      dataList.value.push(article)
    })
  }
  loading.value = false
}

/**
 * 加载更多
 */
function handleMore() {
  queryParams.value.pageNum++
  getArticleList()
}

getArticleList()
</script>
<style lang="scss" scoped>
.article-time {
  color: var(--el-text-color-secondary);
  line-height: 1;
  font-size: var(--el-font-size-small);
  margin-bottom: 5px;
}

.article {
  position: relative;

  .article-image {
    height: 175px;
    width: 100%;
  }

  .title {
    cursor: pointer;
    position: absolute;
    z-index: 10;
    height: 30px;
    width: 100%;
    line-height: 30px;
    text-align: center;
    top: 145px;
    background: rgba(0, 0, 0, 0.7);
    font-size: 14px;

    .article-title {
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 1;
      overflow: hidden;
      text-align: center;
      color: white;
    }
  }
}

.more {
  text-align: center;
}
</style>
