import { getToken, removeToken } from '@/utils/auth'
import { logout } from '@/api/auth/login'
import { getUserInfo } from '@/api/auth/user'

const getters = {
  token: (state) => state.token,
  avatar: (state) => state.avatar,
  isLogin: (state) => state.token !== undefined && state.token !== ''
}

const state = {
  token: getToken(),
  avatar: ''
}

const mutations = {
  setToken: (state, token) => {
    state.token = token
  },
  setAvatar: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // 获取用户信息
  async getUserInfo({ commit }) {
    const {
      data: { avatar }
    } = await getUserInfo()
    commit('setAvatar', avatar)
  },

  // 退出登录
  async logout({ commit }) {
    try {
      await logout()
    } catch (error) {
      console.log(error)
    }
    commit('setToken', '')
    removeToken()
  }
}

export default {
  namespaced: true,
  getters,
  state,
  mutations,
  actions
}
