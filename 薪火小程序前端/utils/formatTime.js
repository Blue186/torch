const formatTime = (time, format) => {
	const date = new Date(time)
	const year = date.getFullYear()
	const month = date.getMonth() + 1
	const day = date.getDate()
	const hour = date.getHours()
	const minute = date.getMinutes()
	const second = date.getSeconds()
	// 小时和分钟
	if (format === 'time') {
		return `${[hour, minute].map(formatNumber).join(':')}`
	}
	// 年月日时分秒
	if (format === 'year') {
		return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
	}
	// 小时分钟 用h   min
	if(format === 'duration'){
		return `${[hour, minute].map(formatNumber).join('h')}min`
	}
	// 年月日
	if(format === 'Y-M-D'){
		return `${[year, month, day].map(formatNumber).join('/')} `
	}
	// 月日
	if(format === 'M-D'){
		return `${[ month, day].map(formatNumber).join('月')}日 `
	}
	// 年
	if(format === 'Y'){
		return `${[year].map(formatNumber)}`
	}
	// 年月日时分秒
	return `${[year, month, day].map(formatNumber).join('/')}${[hour, minute, second].map(formatNumber).join(':')}`
}



//转换为两位数
const formatNumber = n => {
	n = n.toString()
	return n[1] ? n : `0${n}`
}
// console.log(formatTime(1638098994000-1638095994000,'time'))

export {
	formatTime
}
