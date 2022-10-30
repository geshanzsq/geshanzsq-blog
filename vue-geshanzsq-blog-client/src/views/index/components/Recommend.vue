<template>
  <el-row
    :gutter="6"
    class="recommend"
    v-if="
      firstRecommendLevelList.length > 0 &&
      secondRecommendLevelList.length > 0 &&
      !isMobile
    "
  >
    <el-col :span="16">
      <el-carousel height="355px" v-loading="firstLoading">
        <el-carousel-item
          :key="article.id"
          v-for="article in firstRecommendLevelList"
        >
          <router-link :to="'article/' + article.id" target="_blank">
            <el-image
              fit="cover"
              class="carousel-item-image"
              :src="getPictureShowUrl(article.coverUrl)"
            ></el-image>
            <div class="carousel-title">
              <span class="article-title">{{ article.articleTitle }}</span>
            </div>
          </router-link>
        </el-carousel-item>
      </el-carousel>
    </el-col>
    <el-col :span="8">
      <div
        class="second-recommend"
        v-for="(article, index) in secondRecommendLevelList"
        :key="article.id"
        v-loading="secondLoading"
      >
        <div v-if="index == 0">
          <router-link :to="'article/' + article.id" target="_blank">
            <el-image
              fit="cover"
              lazy
              class="col-image"
              :src="getPictureShowUrl(article.coverUrl)"
            ></el-image>
            <div class="second-recommend-title">
              <span class="article-title">{{ article.articleTitle }}</span>
            </div>
          </router-link>
        </div>
        <div v-if="index == 1">
          <router-link :to="'/article/' + article.id" target="_blank">
            <el-image
              lazy
              fit="cover"
              class="col-image"
              :src="getPictureShowUrl(article.coverUrl)"
            ></el-image>
            <div class="second-recommend-title">
              <span class="article-title">{{ article.articleTitle }}</span>
            </div>
          </router-link>
        </div>
      </div>
    </el-col>
  </el-row>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'

import { getRecommend } from '@/api/blog/article'

const store = useStore()

const firstLoading = ref(true)
const secondLoading = ref(true)
const firstRecommendLevelList = ref([])
const secondRecommendLevelList = ref([])
const isMobile = computed(() => {
  return store.getters['app/isMobile']
})

async function getFirstRecommendLevelList() {
  firstLoading.value = true
  const { data } = await getRecommend(1)
  firstRecommendLevelList.value = data
  firstLoading.value = false
}

async function getSecondRecommendLevelList() {
  secondLoading.value = true
  const { data } = await getRecommend(2)
  secondRecommendLevelList.value = data
  secondLoading.value = false
}

getFirstRecommendLevelList()
getSecondRecommendLevelList()
</script>
<style lang="scss" scoped>
.recommend {
  overflow: hidden;

  .carousel-item-image {
    display: block;
    height: 100%;
    width: 100%;
    overflow: hidden;
  }

  .carousel-title {
    cursor: pointer;
    position: absolute;
    z-index: 10;
    bottom: 30px;
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    background: rgba(0, 0, 0, 0.3);
  }

  .article-title {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    text-align: center;
    color: white;
  }

  .second-recommend {
    position: relative;

    .col-image {
      height: 175px;
      width: 100%;
    }

    .second-recommend-title {
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
    }
  }
}
</style>
