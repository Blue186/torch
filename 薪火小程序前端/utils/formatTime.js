const formatTime = (time, format) => {
	const date = new Date(time)
	const year = date.getFullYear()
	const month = date.getMonth() + 1
	const day = date.getDate()
	const hour = date.getHours()
	const minute = date.getMinutes()
	const second = date.getSeconds()
	if (format === 'time') {
		return `${[hour, minute].map(formatNumber).join(':')}`
	}
	if (format === 'year') {
		return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
	}
	if(format === 'duration'){
		return `${[hour, minute].map(formatNumber).join('h')}min`
	}
	return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
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
