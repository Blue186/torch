import {
	apiResquest
} from '../utils/http.js'

//获取做过志愿记录 参数为1代表已完成的活动
export const getVolunteerRecords = (query) => {
	let str = query
	return apiResquest({
		url: `/signUp/signInfo/${str}`,
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

// 上传图片
export const postImages = (query) => {
	return apiResquest({
		url: '/file/images',
		method: 'POST',
		query:query
	})
}

