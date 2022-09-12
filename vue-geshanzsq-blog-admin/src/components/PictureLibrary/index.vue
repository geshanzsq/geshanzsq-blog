<template>
  <el-dialog :title="title" v-model="visible" width="70%" @close="close">
    <div>
      <el-upload
        :headers="headers"
        :action="uploadFileUrl + '?categoryId=' + queryParams.categoryId"
        :on-success="uploadSuccess"
        :on-error="uploadError"
        accept=".jpg,.jpeg,.png,.gif,.ico"
      >
        <el-button plain type="primary" icon="plus">上传图片</el-button>
        <template #tip>
          <span style="margin-left: 10px" class="el-upload__tip"
            >只能上传图片格式的文件，且不超过 10Mb</span
          >
        </template>
      </el-upload>
    </div>

    <div style="margin: 10px 0">
      <el-input v-model="picturePath" placeholder="可输入完整的图片链接地址">
        <template #prepend>图片地址</template>
      </el-input>
    </div>

    <!--图片分类显示-->
    <el-form :model="queryParams" :inline="true" v-if="categoryList.length > 0">
      <el-form-item>
        <el-radio-group v-model="queryParams.categoryId" @change="getPage">
          <el-radio-button
            v-for="category in categoryList"
            :key="category.id"
            :label="category.id"
          >
            {{ category.categoryName }}
          </el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>

    <div class="no-data" v-else>暂无图片分类数据！</div>

    <!--图片显示-->
    <el-row :gutter="18" v-if="dataList.length > 0">
      <el-col
        :xs="24"
        :sm="12"
        :md="12"
        :lg="6"
        :xl="4"
        class="picture-col"
        v-for="picture in dataList"
        :key="picture.id"
        @click="handlePicture(picture.picturePath, picture.pictureOriginalName)"
      >
        <el-card :body-style="{ padding: '0px' }">
          <el-image
            class="picture-show"
            fit="cover"
            lazy
            :src="getPictureShowUrl(picture.picturePath)"
            :class="
              picture.picturePath == picturePath ? 'picture-show-over' : ''
            "
          />
          <div class="picture-original-name">
            {{ picture.pictureOriginalName }}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <div class="no-data" v-else>暂无图片数据！</div>
    <pagination
      v-show="totalCount > 0"
      :total="totalCount"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      :page-sizes="[8, 16, 24, 32]"
      @pagination="getPage"
    />
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="primary" @click="handleSave()">确 定</el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script setup>
import { getCurrentInstance, ref } from 'vue'

import { getToken } from '@/utils/auth'
import { tokenConfig } from '@/config/network.config'

import { list as listCategory } from '@/api/blog/picture/category'
import { page } from '@/api/blog/picture/picture'

const { proxy } = getCurrentInstance()

const props = defineProps({
  title: {
    type: String,
    default: '选择图片'
  }
})

const visible = ref(false)
const title = ref(props.title)
const picturePath = ref('')
const pictureName = ref('')
const categoryList = ref([])
const loading = ref(true)
const dataList = ref([])
const totalCount = ref(0)
const queryParams = ref({
  pageNum: 1,
  pageSize: 8,
  categoryId: undefined
})
const emit = defineEmits(null)

// 图片上传地址
const uploadFileUrl = ref(process.env.VUE_APP_BASE_API + '/blog/picture/upload')
const headers = ref({
  Authorization: tokenConfig.prefix + getToken()
})

/**
 * 显示弹窗
 */
async function show(path) {
  visible.value = true
  picturePath.value = path
  await getCategoryList()
  await getPage()
}

/**
 * 获取分类
 */
async function getCategoryList() {
  const { data } = await listCategory()
  categoryList.value = data
  if (categoryList.value.length > 0) {
    queryParams.value.categoryId = categoryList.value[0].id
  }
}

/**
 * 获取图片
 */
async function getPage() {
  loading.value = true
  const {
    data: { total, list }
  } = await page(queryParams.value)
  totalCount.value = parseInt(total)
  dataList.value = list
  loading.value = false
}

/**
 * 图片上传失败
 */
function uploadError() {
  proxy.$modal.msgError('图片上传失败')
}

/**
 * 图片上传成功
 */
function uploadSuccess(response) {
  if (response.code !== 200) {
    const message = response.message || '图片上传失败'
    proxy.$modal.msgError(message)
  } else {
    proxy.$modal.msgSuccess('图片上传成功')
    getPage()
  }
}

/**
 * 选择图片事件
 */
function handlePicture(path, fileName) {
  picturePath.value = path
  pictureName.value = fileName
}

/**
 * 确定
 */
function handleSave() {
  visible.value = false
  emit('change', picturePath.value)
}

/**
 * 关闭
 */
function close() {
  visible.value = false
  emit('change')
}

defineExpose({
  show
})
</script>

<style scoped lang="scss">
.picture-col {
  padding: 10px 0px;
  .picture-show {
    /* 出现抖动和走位问题 */
    border: 2px solid transparent;
    width: 100%;
    height: 120px;
  }
  .picture-show:hover {
    border: 2px solid #07c160;
  }
  .picture-show-over {
    border: 2px solid #07c160;
    opacity: 0.3;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1;
  }

  .picture-original-name {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    text-align: center;
    margin: 0 10px 5px 10px;
  }
}
</style>
