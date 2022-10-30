import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/user/like/page',
    method: 'get',
    params
  })
}

export function add(articleId) {
  return request({
    url: '/user/like',
    method: 'post',
    data: {
      articleId
    }
  })
}
