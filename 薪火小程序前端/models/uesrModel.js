import {
	apiResquest
} from '../utils/http.js'

//获取做过志愿记录
export const getVolunteerRecords = (query) => {
	let str = query
	return apiResquest({
		url: `/signUp/over/${str}`,
		method: 'GET'

	})
}

//发布志愿心得
export const postActicityFeeling = (query)=>{
	return apiResquest({
		url:'/impressions',
		method:'POST',
		query: query
	})
}
	
// 获取志愿心得
export const getActicityFeeling = (query) => {
	let str = query
	return apiResquest({
		url: `/impressions/${str}`,
		method: 'GET'

	})
}
