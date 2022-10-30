import request from '@/utils/request'

/**
 * 获取登录地址
 */
export function getLoginUrl(source) {
  return request({
    url: `/auth/third/login/getLoginUrl/${source}`,
    method: 'get'
  })
}

/**
 * 退出登录
 */
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}
