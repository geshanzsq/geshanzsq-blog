import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/blog/tag/page',
    method: 'get',
    params
  })
}

export function list(params) {
  return request({
    url: '/blog/tag/list',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/blog/tag/getById/${id}`,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/blog/tag/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/blog/tag/',
    method: 'put',
    data
  })
}

export function remove(ids) {
  return request({
    url: `/blog/tag/delete/${ids}`,
    method: 'delete'
  })
}

export function getMaxSort() {
  return request({
    url: '/blog/tag/getMaxSort',
    method: 'get'
  })
}
