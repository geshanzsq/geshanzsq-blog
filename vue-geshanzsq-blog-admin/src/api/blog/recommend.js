import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/blog/recommend/list',
    method: 'get',
    params
  })
}

export function updateSort(data) {
  return request({
    url: '/blog/recommend/updateSort',
    method: 'put',
    data
  })
}
