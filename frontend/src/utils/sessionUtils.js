/**
 * TODO sessionStorage工具
 *
 * @author Mr.He
 * 2021/4/22 18:47
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
import {store} from '@/utils/store'

function setToken(token) {
    store.state.token = token
    sessionStorage.setItem('token', token)
}

function getToken() {
    return sessionStorage.getItem('token')
}

function setUserInfo(userInfo) {
    store.state.userInfo = userInfo
    userInfo = JSON.stringify(userInfo)
    sessionStorage.setItem('userInfo', userInfo)
}

function getUserInfo() {
    let userInfo = sessionStorage.getItem('userInfo')
    return JSON.parse(userInfo)
}

function setRouterMap(routerMap) {
    store.state.routerMap = routerMap
    routerMap = JSON.stringify(routerMap)
    sessionStorage.setItem('routerMap', routerMap)
}

function getRouterMap() {
    let routerMap = sessionStorage.getItem('routerMap')
    return JSON.parse(routerMap)
}

export {
    setToken,
    getToken,
    setUserInfo,
    getUserInfo,
    setRouterMap,
    getRouterMap,
}
