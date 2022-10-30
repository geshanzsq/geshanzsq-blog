<template>
  <div class="draggable">
    <div class="header-title" :style="{ background: headerBackgroundColor }">
      {{ headerTitle }}
    </div>

    <draggable :list="dataList" @start="drag = true" @end="end" item-key="id">
      <template #item="{ element }">
        <el-card :body-style="{ padding: '0px' }" class="card">
          <el-image
            lazy
            fit="cover"
            class="article-image"
            :src="getPictureShowUrl(element.coverUrl)"
          ></el-image>
          <div class="title">
            <span class="article-title">{{ element.articleTitle }}</span>
          </div>
        </el-card>
      </template>
    </draggable>
  </div>
</template>
<script setup>
import draggable from 'vuedraggable'

const props = defineProps({
  headerTitle: {
    type: String,
    default: 'Header'
  },
  headerBackgroundColor: {
    type: String,
    default: ''
  },
  dataList: {
    type: Array,
    default() {
      return []
    }
  }
})

/**
 * 拖动完成
 */
const emit = defineEmits(null)
function end() {
  // 子组件传值给父组件
  emit('end', props.dataList)
}
</script>
<style lang="scss" scoped>
.draggable {
  min-width: 300px;
  min-height: 100px;
  height: auto;
  overflow: hidden;
  background: #f0f0f0;
  border-radius: 3px;

  .header-title {
    height: 50px;
    line-height: 50px;
    overflow: hidden;
    padding: 0 20px;
    text-align: center;
    color: #fff;
    border-radius: 3px 3px 0 0;
  }

  .card {
    position: relative;
    cursor: pointer;
    margin-top: 20px;
    .article-image {
      height: 175px;
      width: 100%;
    }

    .title {
      position: absolute;
      z-index: 10;
      height: 30px;
      width: 100%;
      line-height: 30px;
      text-align: center;
      top: 145px;
      background: rgba(0, 0, 0, 0.7);
      font-size: 14px;

      .article-title {
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
        overflow: hidden;
        text-align: center;
        color: white;
      }
    }
  }
}
</style>
