import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/blog/friendship/page',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/blog/friendship/getById/${id}`,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/blog/friendship/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/blog/friendship/',
    method: 'put',
    data
  })
}

export function remove(ids) {
  return request({
    url: `/blog/friendship/delete/${ids}`,
    method: 'delete'
  })
}

export function getMaxSort() {
  return request({
    url: '/blog/friendship/getMaxSort',
    method: 'get'
  })
}
