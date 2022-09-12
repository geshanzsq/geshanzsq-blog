import {
  sideTheme,
  showSettings,
  topNav,
  tagsView,
  fixedHeader,
  sidebarLogo,
  dynamicTitle
} from '@/config/theme.config'
import { title } from '@/config/setting.config'
import { useDynamicTitle } from '@/utils/dynamicTitle'

const storageSetting = JSON.parse(localStorage.getItem('layout-setting')) || ''

const getters = {
  title: (state) => state.title,
  theme: (state) => state.theme,
  sideTheme: (state) => state.sideTheme,
  showSettings: (state) => state.showSettings,
  topNav: (state) => state.topNav,
  tagsView: (state) => state.tagsView,
  fixedHeader: (state) => state.fixedHeader,
  sidebarLogo: (state) => state.sidebarLogo,
  dynamicTitle: (state) => state.dynamicTitle
}

const state = {
  title: title,
  theme: storageSetting.theme || '#409EFF',
  sideTheme: storageSetting.sideTheme || sideTheme,
  showSettings: showSettings,
  topNav: storageSetting.topNav === undefined ? topNav : storageSetting.topNav,
  tagsView:
    storageSetting.tagsView === undefined ? tagsView : storageSetting.tagsView,
  fixedHeader:
    storageSetting.fixedHeader === undefined
      ? fixedHeader
      : storageSetting.fixedHeader,
  sidebarLogo:
    storageSetting.sidebarLogo === undefined
      ? sidebarLogo
      : storageSetting.sidebarLogo,
  dynamicTitle:
    storageSetting.dynamicTitle === undefined
      ? dynamicTitle
      : storageSetting.dynamicTitle
}
const mutations = {
  changeSetting: (state, { key, value }) => {
    if (Object.prototype.hasOwnProperty.call(state, key)) {
      state[key] = value
    }
  }
}

const actions = {
  // 修改布局设置
  changeSetting({ commit }, data) {
    commit('changeSetting', data)
  },
  // 设置网页标题
  setTitle({ commit }, title) {
    state.title = title
    useDynamicTitle()
  }
}

export default {
  namespaced: true,
  getters,
  state,
  mutations,
  actions
}
