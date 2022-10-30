<template>
  <div v-loading="loading" v-if="dataList.length > 0">
    <h3>相关文章</h3>
    <el-divider></el-divider>
    <el-row :gutter="24">
      <el-col :xs="24" :sm="8" v-for="article in dataList" :key="article.id">
        <el-card
          class="about-article-card"
          :body-style="isMobile ? { padding: '0px 0px 10px 0px' } : {}"
        >
          <el-image
            class="article-image article-card-image-common"
            :src="getPictureShowUrl(article.coverUrl)"
            lazy
          />
          <div class="article-title">{{ article.articleTitle }}</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

import { getAboutArticleById } from '@/api/blog/article'

const props = defineProps({
  id: {
    type: String || Number,
    require: true
  }
})

const store = useStore()

const loading = ref(true)
const dataList = ref([])

const isMobile = computed(() => {
  return store.getters['app/isMobile']
})

async function getDataList() {
  loading.value = true
  const { data } = await getAboutArticleById(props.id)
  dataList.value = data
  loading.value = false
}

getDataList()
</script>
<style lang="scss" scoped>
.about-article-card {
  margin-bottom: 20px;
  cursor: pointer;

  .article-image {
    height: 160px;
  }

  .article-title {
    text-align: center;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
  }
}
</style>
