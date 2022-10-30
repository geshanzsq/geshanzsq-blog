<template>
  <el-card class="article-card" @click="handleArticle">
    <el-row :gutter="24">
      <el-col :span="6">
        <el-image
          fit="cover"
          class="article-card-image article-card-image-common"
          :src="getPictureShowUrl(article.coverUrl)"
          lazy
        >
        </el-image>
      </el-col>
      <el-col :span="18">
        <h3 class="article-title">
          <el-tag
            class="mr5"
            v-if="article.flag === 1"
            effect="plain"
            type="danger"
            size="small"
            @click.stop="handleFlag"
            >{{ flagName }}</el-tag
          >
          <el-tag
            class="mr5"
            v-if="showSticky && article.hasSticky === 1"
            effect="dark"
            size="small"
            >置顶</el-tag
          >
          {{ article.articleTitle }}
        </h3>
        <p class="article-description">{{ article.articleDescription }}</p>
        <ul class="article-footer">
          <li class="article-footer-li" @click.stop="handleAuthor">
            <el-avatar class="avatar li-icon" :src="avatar"></el-avatar>
            <span class="li-title">{{ article.author.nickName }}</span>
          </li>
          <li class="article-footer-li" @click.stop="handleCategory">
            <el-icon class="li-icon"><Menu /></el-icon>
            <span class="li-title">{{ article.categoryName }}</span>
          </li>
          <li class="article-footer-li">
            <el-icon class="li-icon"><Clock /></el-icon>
            <span class="li-title">{{ parseTime(article.gmtPublish) }}</span>
          </li>
          <li class="article-footer-li">
            <el-icon class="li-icon"><View /></el-icon>
            <span class="li-title">{{ article.viewCount }}</span>
          </li>
          <li class="article-footer-li">
            <svg-icon icon-name="good" class="li-icon" />
            <span class="li-title">{{ article.likeCount }}</span>
          </li>
        </ul>
      </el-col>
    </el-row>
  </el-card>
</template>
<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'

import { getPictureShowUrl } from '@/utils/geshanzsq'
import defaultAvatar from '@/assets/images/avatar/userDefaultAvatar.jpg'
import { scrollTo } from '@/utils/scroll-to'

const router = useRouter()

const props = defineProps({
  article: Object,
  showSticky: {
    type: Boolean,
    default: true
  }
})

/**
 * 获取头像
 */
const avatar = computed(() => {
  const avatarUrl = props.article.author.avatar
  return avatarUrl !== '' ? getPictureShowUrl(avatarUrl) : defaultAvatar
})

/**
 * 获取标识名称
 */
const flagName = computed(() => {
  if (props.article.flag === 1) {
    return '原创'
  } else {
    return ''
  }
})

/**
 * 文章点击
 */
function handleArticle() {
  scrollTo(0, 800)
  window.open(`/article/${props.article.id}`)
}

/**
 * 标识点击
 */
function handleFlag() {
  scrollTo(0, 800)
  router.push(`/flag/${flagName.value}`)
}

/**
 * 作者点击
 */
function handleAuthor() {
  scrollTo(0, 800)
  router.push(`/author/${props.article.author.nickName}`)
}

/**
 * 分类点击
 */
function handleCategory() {
  scrollTo(0, 800)
  router.push(`/category/${props.article.categoryName}`)
}
</script>

<style lang="scss" scoped>
.article-card {
  cursor: pointer;

  .article-card-image {
    width: 100%;
    height: 160px;
  }

  .article-title {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
  }
  .article-title:hover {
    color: #337ab7;
  }

  .article-description {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    margin-top: 5px;
    font-size: 14px;
  }

  .article-footer {
    list-style: none;
    padding: 0px;
    color: var(--el-text-color-secondary);
    font-size: var(--el-font-size-small);
    display: inline-block;
    flex-direction: row;

    .article-footer-li {
      float: left;
      padding-right: 15px;
      height: 25px;
      line-height: 25px;

      .avatar {
        height: 20px;
        width: 20px;
        border-radius: 50%;
      }

      .li-icon {
        vertical-align: middle;
        margin-right: 3px;
      }

      .li-title {
        vertical-align: middle;
      }
    }
  }
}
</style>
