import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/user/collect/page',
    method: 'get',
    params
  })
}

export function add(articleId) {
  return request({
    url: '/user/collect',
    method: 'post',
    data: {
      articleId
    }
  })
}
