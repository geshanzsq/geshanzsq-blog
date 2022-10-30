import request from '@/utils/request'

export function listByArticleId(articleId) {
  return request({
    url: '/comment/listByArticleId',
    method: 'get',
    params: {
      articleId
    }
  })
}

export function page(params) {
  return request({
    url: '/user/comment/page',
    method: 'get',
    params
  })
}

export function add(data) {
  return request({
    url: '/user/comment',
    method: 'post',
    data
  })
}
