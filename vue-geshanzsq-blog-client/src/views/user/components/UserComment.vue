<template>
  <div>
    <div v-loading="loading" v-if="totalCount > 0">
      <div v-for="comment in dataList" :key="comment.id" class="comment">
        <div class="comment-top">
          <span class="time">{{ comment.gmtCreate }}</span>
          <el-tag
            effect="dark"
            class="article-detail"
            @click="handleArticle(comment.articleId)"
            ><span v-if="comment.articleId > 0">文章详情</span>
            <span v-else>留言板</span></el-tag
          >
        </div>

        <el-card class="card" :body-style="{ padding: '10px' }">
          <div v-html="comment.commentContent" class="content"></div>
        </el-card>
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

import { page } from '@/api/blog/comment'

const totalCount = ref(0)
const dataList = ref([])
const loading = ref(true)
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
 * 加载更多
 */
function handleMore() {
  queryParams.value.pageNum++
  getDataList()
}

/**
 * 获取数据
 */
async function getDataList() {
  loading.value = true
  const {
    data: { total, list }
  } = await page(queryParams.value)
  totalCount.value = total
  list.forEach((comment) => {
    dataList.value.push(comment)
  })
  loading.value = false
}

// 文章详情事件
function handleArticle(articleId) {
  if (articleId > 0) {
    window.open(`/article/${articleId}`)
  }
}

getDataList()
</script>
<style lang="scss" scoped>
.comment {
  margin-bottom: 20px;
  cursor: auto;

  .comment-top {
    height: 30px;
    line-height: 30px;
    .time {
      float: left;
      color: var(--el-text-color-secondary);
      line-height: 1;
      font-size: var(--el-font-size-small);
      margin-bottom: 5px;
    }
  }
  .article-detail {
    cursor: pointer;
    float: right;
  }

  .card {
    clear: both;

    .content {
      line-height: 25px;
    }
  }
}
</style>
