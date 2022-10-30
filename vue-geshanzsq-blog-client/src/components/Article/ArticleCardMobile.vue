<template>
  <el-card
    class="article-card"
    :body-style="{ padding: '0px' }"
    @click="handleArticle"
  >
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
      <li class="gmt-publish">
        <span>{{ article.gmtPublish }}</span>
      </li>
    </ul>
    <el-image
      :src="getPictureShowUrl(article.coverUrl)"
      fit="cover"
      class="cover"
      lazy
    ></el-image>
    <div class="article-row">
      <div class="article-title">
        <el-tag
          class="mr5"
          v-if="article.flag === 1"
          effect="plain"
          type="danger"
          size="small"
          @click.stop="handleFlag"
          >{{ flagName }}</el-tag
        >
        <el-tag v-if="article.hasSticky === 1" effect="dark" size="small"
          >置顶</el-tag
        ><span class="title">{{ article.articleTitle }}</span>
      </div>
    </div>

    <div class="article-row">
      <p class="views">
        <span @click.stop="handleCategory">
          <el-icon @click.stop="handleCategory"><Menu /></el-icon>
          <span class="category-name">{{ article.categoryName }}</span>
        </span>
        <span>阅读 {{ article.viewCount }}</span>
        <span> · 赞 {{ article.likeCount }}</span>
      </p>
    </div>

    <div class="article-row">
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
import { computed } from 'vue'
import { useRouter } from 'vue-router'

import { getPictureShowUrl } from '@/utils/geshanzsq'
import defaultAvatar from '@/assets/images/avatar/userDefaultAvatar.jpg'

const router = useRouter()

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
  ul {
    list-style: none;
    padding: 0px;
    li {
      float: left;
      margin-right: 10px;
      color: var(--el-text-color-secondary);
      font-size: var(--el-font-size-small);
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
    .gmt-publish {
      float: right;
      margin-right: 10px;
    }
  }

  .cover {
    width: 100%;
    height: 160px;
  }

  .article-row {
    padding: 0px 10px 5px 10px;
  }

  .article-title {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    margin: 0 auto;
  }
  .tag-status {
    margin-right: 5px;
  }
  .title {
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
