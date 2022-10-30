<template>
  <div>
    <!-- 发送评论框 -->
    <send :article-id="articleId" @success="handleSuccessPublish" />

    <!-- 评论列表 -->
    <div v-loading="loading">
      <div
        v-for="comment in dataList"
        :key="comment.id"
        :class="comment.parentId > 0 ? 'parent-comment' : ''"
        :id="'comment-' + comment.id"
      >
        <el-container>
          <el-aside class="aside">
            <el-image
              class="avatar"
              lazy
              :src="
                comment.avatar
                  ? getPictureShowUrl(comment.avatar)
                  : userDefaultAvatar
              "
            >
              <template #error>
                <div class="image-slot">
                  <el-image class="avatar" lazy :src="userDefaultAvatar">
                  </el-image>
                </div>
              </template>
            </el-image>
          </el-aside>
          <el-main class="main">
            <div class="nick-name">
              <el-tag
                type="primary"
                effect="dark"
                class="sticky"
                v-if="comment.hasSticky == 1"
                >置顶</el-tag
              >
              <span>{{ comment.nickName }}</span>
              <el-tag type="error" class="author" v-if="comment.hasAuthor">
                <span v-if="articleId > 0">作者</span>
                <span v-else>站长</span>
              </el-tag>
            </div>
            <div
              v-if="comment.parent"
              class="parent-content"
              @click="handlePosition('comment-' + comment.parent.id)"
            >
              回复：
              <span v-html="comment.parent.commentContent"></span>
            </div>
            <div
              v-html="comment.commentContent"
              v-highlight
              class="content"
            ></div>
            <div class="comment-time">
              <span class="time">{{ comment.gmtCreate }}</span>
              <span class="reply" @click="handleReply(comment.id)">回复</span>
            </div>
            <send
              v-if="comment.id === parentId"
              :article-id="articleId"
              :parent-id="parentId"
              @cancel="handleCancelPublish"
              @success="handleSuccessPublish"
            />
            <el-divider />
          </el-main>
        </el-container>
      </div>
      <el-empty
        v-if="dataList.length === 0"
        description="还没有评论，赶紧去评论抢占沙发吧！"
      />
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { listByArticleId } from '@/api/blog/comment'
import { scrollTo } from '@/utils/scroll-to'

import Send from './components/Send'
import userDefaultAvatar from '@/assets/images/avatar/userDefaultAvatar.jpg'

const props = defineProps({
  articleId: {
    type: Number || String,
    default: 0
  }
})

const loading = ref(true)
const dataList = ref([])
const parentId = ref(undefined)

/**
 * 获取数据
 */
async function getDataList() {
  loading.value = true
  const { data } = await listByArticleId(props.articleId)
  dataList.value = []
  data.forEach((comment) => {
    dataList.value.push(comment)
    recursiveComment(comment, comment.children)
  })
  loading.value = false
}

/**
 * 递归添加评论
 */
function recursiveComment(parentData, data) {
  if (!(data instanceof Array)) {
    return
  }
  data.forEach((comment) => {
    comment.parent = parentData
    dataList.value.push(comment)
    if (comment.children instanceof Array && comment.children.length > 0) {
      recursiveComment(comment, comment.children)
    }
  })
}

/**
 * 点击回复事件
 */
function handleReply(id) {
  parentId.value = id
}

/**
 * 取消发表事件
 */
function handleCancelPublish() {
  parentId.value = undefined
}

/**
 * 发布成功
 */
function handleSuccessPublish() {
  parentId.value = undefined
  // 重新获取评论
  getDataList()
}

/**
 * 描点定位到上级评论
 */
function handlePosition(parentId) {
  const e = document.querySelector('#' + parentId)
  scrollTo(e.offsetTop - 20, 500)
}

getDataList()
</script>
<style lang="scss" scoped>
.parent-comment {
  margin-left: 50px;
}

.aside {
  background-color: white !important;
  width: 50px !important;
  height: 50px !important;
  padding: 0 !important;
  overflow: hidden;
  text-align: center !important;
  border-radius: 50%;

  .avatar {
    border-radius: 50%;
    width: 50px;
    height: 50px;
  }
}

.main {
  padding: 0 !important;
  margin-left: 20px !important;
  .nick-name {
    font-size: 18px;
    font-weight: bold;
    .sticky {
      margin-right: 5px;
    }

    .author {
      margin-left: 5px;
    }
  }
  .content {
    margin-top: 10px;
  }

  /*设置图片，以防过大*/
  .content >>> img {
    max-width: 100%;
    height: auto;
    cursor: pointer;
  }

  .parent-content {
    margin-top: 10px;
    color: var(--el-text-color-secondary);
    font-size: var(--el-font-size-small);
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    cursor: pointer;
  }

  .comment-time {
    font-size: 14px;
    margin-top: 10px;
    .time {
      color: var(--el-text-color-secondary);
      font-size: var(--el-font-size-small);
    }
    .reply {
      float: right;
      cursor: pointer;
    }
  }
}
</style>
