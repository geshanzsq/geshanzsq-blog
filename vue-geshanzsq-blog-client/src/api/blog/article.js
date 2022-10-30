import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/article/page',
    method: 'get',
    params
  })
}

export function pageSearch(params) {
  return request({
    url: '/article/pageSearch',
    method: 'get',
    params
  })
}

export function getRecommend(recommendLevel) {
  return request({
    url: '/article/getRecommend',
    method: 'get',
    params: {
      recommendLevel
    }
  })
}

export function listClickRanking() {
  return request({
    url: '/article/listClickRanking',
    method: 'get'
  })
}

export function getById(id) {
  return request({
    url: `/article/getById/${id}`,
    method: 'get'
  })
}

export function getContentById(id) {
  return request({
    url: `/article/getContentById/${id}`,
    method: 'get'
  })
}

export function getAboutArticleById(id) {
  return request({
    url: `/article/getAboutArticleById/${id}`,
    method: 'get'
  })
}
