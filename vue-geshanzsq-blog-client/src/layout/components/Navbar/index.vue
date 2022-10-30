<template>
  <el-affix>
    <div class="header-menu">
      <div v-if="isMobile" class="menu-icon-mobile">
        <el-icon v-if="menuMobileShow" @click="handleMenuIconMobile"
          ><Close
        /></el-icon>
        <el-icon v-else @click="handleMenuIconMobile"><Menu /></el-icon>
      </div>
      <div v-if="isMobile" class="logo-mobile">
        <logo :show-image="false" />
      </div>
      <!-- 菜单 -->
      <el-menu
        class="menu"
        v-if="isMobile ? menuMobileShow : true"
        :class="isMobile ? 'menu-mobile' : ''"
        :mode="isMobile ? 'vertical' : 'horizontal'"
        :text-color="
          isMobile
            ? variables.headerMenuTextColorVertical
            : variables.headerMenuTextColorHorizontal
        "
        :background-color="
          isMobile
            ? variables.headerBackgroundColorVertical
            : variables.headerBackgroundColorHorizontal
        "
        @select="handleMenuSelect"
      >
        <el-menu-item class="header-logo" v-show="!isMobile"
          ><logo
        /></el-menu-item>
        <el-menu-item
          v-for="(menu, index) in menuList"
          :index="menu.index"
          :key="index"
          >{{ menu.name }}</el-menu-item
        >
      </el-menu>

      <!-- 右侧 -->
      <div class="header-right" :class="isMobile ? 'header-right-mobile' : ''">
        <search class="search" />
        <div>
          <user-dropdown class="user-dropdown" />
        </div>
      </div>
    </div>
  </el-affix>
</template>
<script setup>
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

import Logo from './components/Logo'
import Search from './components/Search'
import UserDropdown from './components/UserDropdown'

import { isHttp } from '@/utils/validate'
import { scrollTo } from '@/utils/scroll-to'
import variables from '@/assets/styles/variables.module.scss'
import { getOpenStatus } from '@/api/blog/board'

const router = useRouter()
const store = useStore()

const menuList = ref([
  {
    index: '/',
    name: '首页'
  },
  {
    index: '/category',
    name: '分类'
  },
  {
    index: '/tag',
    name: '标签'
  },
  {
    index: '/archive',
    name: '归档'
  },
  {
    index: '/about',
    name: '关于我'
  },
  {
    index: '/board',
    name: '留言板'
  },
  {
    index: 'https://gesdh.cn',
    name: '导航网站'
  }
])
const menuMobileShow = ref(false)
const isMobile = ref(
  computed(() => {
    return store.getters['app/isMobile']
  })
)

/** 菜单选择事件 */
function handleMenuSelect(key) {
  if (isHttp(key)) {
    // 链接，直接跳转
    window.open(key, '_blank')
  } else {
    scrollTo(0, 800)
    router.push(key)
  }
  if (isMobile.value) {
    // 如果为移动端点击，菜单栏关闭
    menuMobileShow.value = false
  }
}

/**
 * 获取留言板开启状态
 */
async function getBoardOpenStatus() {
  const {
    data: { status }
  } = await getOpenStatus()
  // 如果开启，则不做操作
  if (status) {
    return
  }
  // 否则，隐藏留言板
  menuList.value = menuList.value.filter((menu) => menu.index !== '/board')
}

/**
 * 移动端点击菜单显示隐藏
 */
function handleMenuIconMobile() {
  menuMobileShow.value = !menuMobileShow.value
}

getBoardOpenStatus()
</script>

<style lang="scss" scoped>
@import '@/assets/styles/variables.module.scss';
.header-menu {
  position: relative;
  margin: 0;
  height: #{$header-height};
  line-height: #{$header-height};
  background-color: #{$header-background-color-horizontal};

  .menu-icon-mobile {
    position: absolute;
    color: white;
    cursor: pointer;
    font-size: 35px;
    left: 10px;
    top: 5px;
    z-index: 1;
  }

  .logo-mobile {
    position: absolute;
    width: 100%;
    text-align: center;
    padding-right: 50px;
  }

  .menu-mobile {
    position: fixed;
    z-index: 1;
    width: 50% !important;
    top: #{$header-height};
    height: 100%;
    .el-menu-item {
      text-align: center !important;
      display: block;
    }
    .el-menu-item:hover {
      color: #{$header-menu-text-color-hover-vertical} !important;
      background-color: #{$header-background-color-vertical} !important;
    }
  }

  .menu {
    border-bottom: 0px;
    .header-logo {
      padding-left: 50px;
    }
    .el-menu-item {
      font-size: 17px;
      border-bottom: 0px !important;
    }

    .el-menu-item:hover {
      color: #{$header-menu-text-color-hover-horizontal};
    }
    .flex-grow {
      flex-grow: 1;
    }
  }

  .header-right {
    position: absolute;
    top: 0px;
    right: 50px;
    width: 400px;
    .search {
      position: absolute;
      right: 70px;
      font-size: 20px;
      cursor: pointer;
    }
    .user-dropdown {
      position: absolute;
      right: 0px;
      top: 12px;
      cursor: pointer;
    }
  }

  .header-right-mobile {
    right: 20px;
  }
}
</style>
