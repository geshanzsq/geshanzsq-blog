import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/blog/third/config/page',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/blog/third/config/getById/${id}`,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/blog/third/config/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/blog/third/config/',
    method: 'put',
    data
  })
}

export function remove(ids) {
  return request({
    url: `/blog/third/config/delete/${ids}`,
    method: 'delete'
  })
}
