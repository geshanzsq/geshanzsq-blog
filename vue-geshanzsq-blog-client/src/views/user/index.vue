<template>
  <div class="user-drawer">
    <el-drawer
      v-model="visible"
      :size="isMobile ? '100%' : '35%'"
      :with-header="isMobile"
      lock-scroll
      @close="close"
    >
      <el-tabs
        class="user-tabs"
        type="border-card"
        v-model="activeName"
        :tab-position="isMobile ? 'top' : 'left'"
        stretch
      >
        <el-tab-pane name="user"
          ><template #label>
            <el-icon><Star /></el-icon>
            <span> 个人中心</span>
          </template>
          <user-center v-if="activeName === 'user'" />
        </el-tab-pane>
        <el-tab-pane name="collect"
          ><template #label>
            <el-icon><Star /></el-icon>
            <span> 我的收藏</span>
          </template>
          <user-article
            :active-name="activeName"
            v-if="activeName === 'collect'"
          />
        </el-tab-pane>
        <el-tab-pane name="like"
          ><template #label>
            <el-icon><Pointer /></el-icon>
            <span> 我的点赞</span>
          </template>
          <user-article
            :active-name="activeName"
            v-if="activeName === 'like'"
          />
        </el-tab-pane>
        <el-tab-pane name="footprint"
          ><template #label>
            <el-icon><View /></el-icon>
            <span> 我的足迹</span>
          </template>
          <user-article
            :active-name="activeName"
            v-if="activeName === 'footprint'"
          />
        </el-tab-pane>
        <el-tab-pane name="comment"
          ><template #label>
            <el-icon><Promotion /></el-icon>
            <span> 我的评论</span>
          </template>
          <user-comment v-if="activeName === 'comment'" />
        </el-tab-pane>
      </el-tabs>
    </el-drawer>
  </div>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import UserArticle from './components/UserArticle'
import UserCenter from './components/UserCenter'
import UserComment from './components/UserComment'

const store = useStore()

const visible = ref(false)
const isMobile = computed(() => {
  return store.getters['app/isMobile']
})
const activeName = ref('')

/**
 * 显示弹窗
 */
function show(eventName) {
  activeName.value = eventName
  visible.value = true
}

/**
 * 关闭
 */
function close() {
  visible.value = false
}

defineExpose({
  show
})
</script>
<style lang="scss" scoped>
.user-drawer {
  .user-tabs {
    height: 100%;
    width: 100%;
  }
}
</style>

<style lang="scss">
.user-drawer {
  .el-drawer__body {
    // 去除边框
    padding: 0px;
  }
  .user-tabs {
    // 修复内容超过高度后不能滚动
    .el-tabs__content {
      height: 100%;
      overflow-y: scroll;
    }
  }

  .more {
    text-align: center;
  }
}
</style>
