import request from '@/utils/request'

export function page(params) {
  return request({
    url: '/blog/picture/category/page',
    method: 'get',
    params
  })
}

export function list(params) {
  return request({
    url: '/blog/picture/category/list',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/blog/picture/category/getById/${id}`,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: '/blog/picture/category/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/blog/picture/category/',
    method: 'put',
    data
  })
}

export function remove(ids) {
  return request({
    url: `/blog/picture/category/delete/${ids}`,
    method: 'delete'
  })
}

export function getMaxSort() {
  return request({
    url: '/blog/picture/category/getMaxSort',
    method: 'get'
  })
}
