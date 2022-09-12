<template>
  <el-card class="article-card" :body-style="{ padding: '0px' }" lazy>
    <ul>
      <li class="author">
        <el-image
          class="avatar"
          :src="avatar"
          @click.stop="handleAuthor"
          fit="cover"
        />
        <span class="nick-name">{{ article.author.nickName }}</span>
      </li>
      <li class="gmt-modify">
        <span>{{ article.gmtModify }}</span>
      </li>
    </ul>
    <el-image
      :src="getPictureShowUrl(article.coverUrl)"
      fit="cover"
      class="cover"
    ></el-image>
    <div class="article-common-padding">
      <div class="article-title-div">
        <dictionary-tag
          class="tag-status"
          effect="dark"
          size="small"
          code="blogArticleStatus"
          :value="article.status"
        />
        <el-tag v-if="article.hasSticky === 1" effect="dark" size="small"
          >置顶</el-tag
        ><span class="article-title">{{ article.articleTitle }}</span>
      </div>
    </div>

    <div class="article-common-padding">
      <p class="views">
        <el-icon><Menu /></el-icon>
        <span class="category-name">{{ article.categoryName }}</span>
        <span>阅读 {{ article.viewCount }}</span>
        <span> · 赞 {{ article.likeCount }}</span>
      </p>
    </div>

    <div class="article-common-padding">
      <el-tag
        v-for="tag in article.tags"
        class="tag"
        effect="plain"
        type="warning"
        size="small"
        @click.stop="handleTag(tag.tagId)"
        :key="tag.tagId"
        >{{ tag.tagName }}</el-tag
      >
    </div>
    <slot name="card-footer"></slot>
  </el-card>
</template>
<script setup>
import { getPictureShowUrl } from '@/utils/geshanzsq'
import { computed } from 'vue'
import defaultAvatar from '@/assets/images/profile.jpg'

const props = defineProps({
  article: Object
})
/**
 * 获取头像
 */
const avatar = computed(() => {
  const avatarUrl = props.article.author.avatar
  return avatarUrl !== '' ? getPictureShowUrl(avatarUrl) : defaultAvatar
})
</script>
<style lang="scss" scoped>
.article-card {
  ul {
    list-style: none;
    padding: 0px;
    li {
      float: left;
      margin-right: 10px;
      font-size: 13px;
      color: #979898;
      vertical-align: middle;
      height: 25px;
      line-height: 100%;
    }

    .author {
      padding: 0px 10px;

      .avatar {
        height: 20px;
        width: 20px;
        border-radius: 50%;
        vertical-align: middle;
        margin-top: -5px;
      }
      .nick-name {
        margin-left: 5px;
      }
    }
    .gmt-modify {
      float: right;
      margin-right: 10px;
    }
  }

  .cover {
    width: 100%;
    height: 160px;
  }

  .article-common-padding {
    padding: 0px 10px 5px 10px;
  }

  .article-title-div {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    margin: 0 auto;
  }
  .tag-status {
    margin-right: 5px;
  }
  .article-title {
    margin-left: 5px;
  }

  .views {
    margin: 0 auto;
    color: #979898;
    font-size: 13px;

    .category-name {
      margin-right: 5px;
    }
  }

  .category-name-ul {
    margin: 0 auto;
  }

  .tag {
    margin-right: 5px;
  }
}
</style>
