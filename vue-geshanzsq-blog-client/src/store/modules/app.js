const getters = {
  device: (state) => state.device,
  isMobile: (state) => state.device === 'mobile'
}

const state = {
  device: 'desktop'
}

const mutations = {
  toggleDevice: (state, device) => {
    state.device = device
  }
}

const actions = {
  toggleDevice({ commit }, device) {
    commit('toggleDevice', device)
  }
}

export default {
  namespaced: true,
  getters,
  state,
  mutations,
  actions
}
