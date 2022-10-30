import request from '@/utils/request'

export function getContent(id) {
  return request({
    url: '/blog/about/getContent',
    method: 'get'
  })
}

export function edit(data) {
  return request({
    url: '/blog/about/edit',
    method: 'post',
    data
  })
}
