import request from '@/utils/request'

// 获取用户信息
export function getUserInfo() {
  return request({
    url: '/user/getUserInfo',
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/user',
    method: 'put',
    data
  })
}
