<template>
  <el-card class="mt20" v-if="data.length > 0">
    <template #header>
      <span class="sidebar-card-title">{{ title }}</span>
    </template>

    <router-link
      :to="'/article/' + article.id"
      target="_blank"
      v-for="article in data"
      :key="article.id"
    >
      <el-row class="article-row" :gutter="10">
        <el-col :span="10">
          <el-image
            fit="cover"
            class="article-card-image article-card-image-common"
            :src="getPictureShowUrl(article.coverUrl)"
            lazy
          ></el-image>
        </el-col>
        <el-col :span="14">
          <span class="article-title">{{ article.articleTitle }}</span>
          <p class="article-time">
            {{ article.gmtPublish }}
          </p>
        </el-col>
      </el-row>
    </router-link>
  </el-card>
</template>
<script setup>
defineProps({
  data: {
    type: Array
  },
  title: {
    type: String,
    default: ''
  }
})
</script>
<style lang="scss" scoped>
.article-row {
  margin-bottom: 10px;
  border-bottom: 1px dashed #efefef;
  padding-bottom: 5px;
  cursor: pointer;

  .article-card-image {
    height: 80px;
  }

  .article-title {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    line-height: 1.5;
    overflow: hidden;
    color: #373e4a;
    font-size: 14px;
    padding-top: 10px;
  }
  .article-title:hover {
    color: #337ab7;
  }

  .article-time {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    margin-top: 5px;
    font-size: 13px;
    color: #979898;
  }
}
</style>
