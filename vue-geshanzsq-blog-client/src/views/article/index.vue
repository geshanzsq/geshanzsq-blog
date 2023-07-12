<template>
  <el-card class="article-card" v-loading="loading">
    <h2 class="article-title">{{ article.articleTitle }}</h2>

    <ul class="article-title-footer">
      <li class="article-title-footer-li" v-if="article.flag == 1">
        <el-tag type="success" effect="dark" size="mini" @click="handleFlag">{{
          flagName
        }}</el-tag>
      </li>
      <li class="article-title-footer-li" @click="handleAuthor">
        <el-avatar class="avatar li-icon" :src="avatar"></el-avatar>
        <span class="li-title">{{ author.nickName }}</span>
      </li>
      <li class="article-title-footer-li" @click="handleCategory">
        <el-icon class="li-icon"><Menu /></el-icon>
        <span class="li-title">{{ article.categoryName }}</span>
      </li>
      <li class="article-title-footer-li">
        <el-icon class="li-icon"><Clock /></el-icon>
        <span class="li-title">{{ article.gmtPublish }}</span>
      </li>
      <li class="article-title-footer-li">
        <el-icon class="li-icon"><View /></el-icon>
        <span class="li-title">{{ article.viewCount }}</span>
      </li>
    </ul>

    <div class="tag-row">
      <el-tag
        class="tag"
        type="primary"
        effect="dark"
        @click="handleTag(tagName)"
        v-for="(tagName, index) in article.tagNames"
        :key="index"
        >{{ tagName }}</el-tag
      >
    </div>

    <div
      v-loading="contentLoading"
      element-loading-text="正在获取文章内容..."
      class="article-content"
      v-highlight
      v-html="content"
      @click="handleContentImage"
    ></div>

    <div
      class="reprint"
      v-if="new Date(article.gmtLastPublish) - new Date(article.gmtPublish) > 0"
    >
      本文最后更新于
      {{ article.gmtLastPublish }}
    </div>

    <div v-if="article.flag === 1" class="reprint">
      本文为
      <span class="reprint-author" @click="handleAuthor"
        >格姗知识圈 - {{ author.nickName }}
      </span>
      原创文章。
      可自由转载、引用，但需署名作者且注明文章出处。如转载至微信公众号，请微信与我联系。<br />
      微信号：geshanintell
    </div>

    <div class="artilce-operate">
      <el-tag
        class="artilce-operate-tag"
        type="primary"
        effect="dark"
        size="large"
        @click="handleLike"
        ><svg-icon icon-name="good" />
        <span class="artilce-operate-title">点赞</span>
        <span v-if="article.likeCount > 0"> ({{ article.likeCount }})</span>
      </el-tag>
      <el-tag
        class="artilce-operate-tag"
        type="warning"
        effect="dark"
        size="large"
        @click="handleCollect"
        ><el-icon><Star /></el-icon>
        <span class="artilce-operate-title">收藏</span>
        <span v-if="article.collectCount > 0">
          ({{ article.collectCount }})</span
        >
      </el-tag>
      <el-tag
        class="artilce-operate-tag"
        type="danger"
        effect="dark"
        size="large"
        @click="handleAppreciation"
        ><svg-icon icon-name="money" />
        <span class="artilce-operate-title">打赏</span>
      </el-tag>
    </div>

    <!-- 相关文章推荐 -->
    <about-article :id="id" />

    <!-- 评论 -->
    <div v-if="article.hasComment === 1">
      <h3>文章评论</h3>
      <el-divider></el-divider>

      <Comment :article-id="id" />
    </div>

    <!-- 赞赏弹框 -->
    <appreciation ref="appreciationRef" />

    <!-- 正文图片点击显示 -->
    <el-dialog
      class="article-content-image-preview-dialog"
      v-model="dialogPictureVisible"
      fullscreen
    >
      <div class="dialog-content">
        <img
          style="max-width: 100%"
          :src="dialogImageUrl"
          :alt="dialogImageUrl"
        />
      </div>
    </el-dialog>
  </el-card>
</template>
<script setup>
import { computed, getCurrentInstance, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

import Appreciation from './components/Appreciation'
import AboutArticle from './components/AboutArticle'
import Comment from '@/views/comment'

import { getPictureShowUrl } from '@/utils/geshanzsq'
import defaultAvatar from '@/assets/images/avatar/userDefaultAvatar.jpg'
import { scrollTo } from '@/utils/scroll-to'

import { getById, getContentById } from '@/api/blog/article'
import { add as addLike } from '@/api/blog/like'
import { add as addCollect } from '@/api/blog/collect'

const router = useRouter()
const { proxy } = getCurrentInstance()
const store = useStore()

const loading = ref(true)
const article = ref({})
const author = ref({})
const contentLoading = ref(true)
const content = ref('')
const id = ref(router.currentRoute.value.params.id)
const isLogin = ref(store.getters['user/isLogin'])
// 正文图片
const dialogPictureVisible = ref(false)
const dialogImageUrl = ref('')

/**
 * 获取文章信息
 */
async function getArticleData() {
  loading.value = true
  const { data } = await getById(id.value)
  article.value = data
  loading.value = false
  author.value = data.author
}

/**
 * 获取文章内容
 */
async function getArticleContent() {
  contentLoading.value = true
  const {
    data: { articleContent }
  } = await getContentById(id.value)
  content.value = articleContent
  contentLoading.value = false
}

/**
 * 获取头像
 */
const avatar = computed(() => {
  const avatarUrl = author.value !== undefined ? author.value.avatar : ''
  if (avatarUrl === undefined) {
    return ''
  }
  return avatarUrl !== '' ? getPictureShowUrl(avatarUrl) : defaultAvatar
})

/**
 * 获取标识名称
 */
const flagName = computed(() => {
  if (article.value.flag === 1) {
    return '原创'
  } else {
    return ''
  }
})

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
  router.push(`/author/${author.value.nickName}`)
}

/**
 * 分类点击
 */
function handleCategory() {
  scrollTo(0, 800)
  router.push(`/category/${article.value.categoryName}`)
}

/**
 * 标签点击
 */
function handleTag(tagName) {
  scrollTo(0, 800)
  router.push(`/tag/${tagName}`)
}

/**
 * 点赞点击
 */
async function handleLike() {
  if (!isLogin.value) {
    proxy.$modal.notifyWarning('登录后才能点赞哦')
    return
  }
  await addLike(id.value)
  proxy.$modal.msgSuccess('点赞成功')
  article.value.likeCount++
}

/**
 * 收藏点击
 */
async function handleCollect() {
  if (!isLogin.value) {
    proxy.$modal.notifyWarning('登录后才能收藏哦')
    return
  }
  await addCollect(id.value)
  proxy.$modal.msgSuccess('收藏成功')
  article.value.collectCount++
}

/**
 * 赞赏点击
 */
function handleAppreciation() {
  proxy.$refs.appreciationRef.show()
}

/**
 * 正文图片点击事件
 */
function handleContentImage(e) {
  // 首先需要判断点击的是否是图片
  const type = e.target.localName
  if (type === 'img') {
    dialogPictureVisible.value = true
    dialogImageUrl.value = e.target.currentSrc
  }
}

getArticleData()
getArticleContent()
</script>
<style lang="scss" scoped>
.article-card {
  .article-title {
    color: #009688;
  }

  .article-title-footer {
    width: 100%;
    list-style: none;
    padding: 0px;
    color: var(--el-text-color-secondary);
    font-size: var(--el-font-size-small);
    display: inline-block;
    flex-direction: row;

    .article-title-footer-li {
      float: left;
      padding-right: 15px;
      height: 25px;
      line-height: 25px;
      cursor: pointer;
    }

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

  .tag-row {
    clear: both;
    .tag {
      margin: 5px 5px 0px 0px;
      cursor: pointer;
    }
  }

  .article-content {
    margin-top: 30px;
  }

  /*设置图片，以防过大*/
  .article-content {
    :deep(img) {
      max-width: 100% !important;
      height: auto;
      cursor: pointer;
    }
  }

  .reprint {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    padding: 10px 5px;
    margin-top: 20px;

    .reprint-author {
      text-decoration: none;
      color: var(--el-color-primary);
      cursor: pointer;
    }
  }

  .artilce-operate {
    text-align: center;
    margin-top: 20px;

    .artilce-operate-tag {
      font-size: 18px;
      cursor: pointer;
      margin-right: 10px;
    }

    .artilce-operate-title {
      margin-left: 5px;
    }
  }
}
</style>

<style lang="scss">
.article-content-image-preview-dialog {
  background-color: #000 !important;

  .dialog-content {
    text-align: center;
  }

  .el-dialog__close {
    color: white;
    font-size: 30px;
  }
}
</style>
