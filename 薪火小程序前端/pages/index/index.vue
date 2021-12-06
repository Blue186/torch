<template>
	<view class="indexPage">
		<!-- 蒙层 -->
		<!-- <view class="cover" @click="getUserProfile" v-show="cover"> -->
		<!-- 	</view> -->
		<!-- 轮播图 -->
		<slide-show :images='indexSlideImages'></slide-show>
		<!-- 招募信息 -->
		<view class="recruitmentImformation">
			<view class="title" @longpress="goIng">志愿招募</view>
			<view class="activities">
				<activity class="activity" :info='info' v-for="info in activityInformations" :key='info.id'></activity>
			</view>
			<view class="bottom" v-if="loadDone">
				---别划了，到底了~---
			</view>
		</view>

	</view>
</template>

<script>
	import {
		getVolunteer
	} from '../../models/indexModel.js'
	import slideShow from '../../compoments/slideShow.vue'
	import activity from '../../compoments/activity.vue'

	export default {
		components: {
			slideShow,
			activity
		},
		data() {
			return {

				code: null,
				loadedNumber: 0,
				cover: false,
				loadDone: false,
				//轮播图图片
				indexSlideImages: [{
					url: 'https://www.torchcqs.cn:8080/lxj/allMeeting.jpg',
					name: 'allMeeting'
				}, {
					url: 'https://www.torchcqs.cn:8080/lxj/together.jpg',
					name: 'together'
				}, {
					url: 'https://www.torchcqs.cn:8080/lxj/preach.jpg',
					name: 'preach'
				}],
				// 活动信息
				activityInformations: []

			}
		},
		onLoad() {
			this.getActivityInformations()
		},
		// 页面拉到低了
		onReachBottom() {
			if (!this.loadDone) {
				this.getActivityInformations();
			}
			// console.log("到底了")
		},
		methods: {
			//进入开发中的页面（暂时）
			goIng() {
				uni.navigateTo({
					url: '/package2/pages/ing/ing'
				})
			},
			// 下拉刷新
			onPullDownRefresh() {
				this.getActivityInformations()
			},
			getActivityInformations() {
				getVolunteer(`${this.loadedNumber}/4`).then(res => {
					console.log('活动信息', res.data)
					if (res.data&&res.data.length > 0) {
						this.loadedNumber += 4;
						this.activityInformations = this.activityInformations.concat(res.data)
					} else {
						this.loadDone = true
					}

				})
			}

		}
	}
</script>

<style lang="scss">
	.indexPage {
		display: flex;
		flex-direction: column;
		align-items: center;


	}

	.recruitmentImformation {
		width: 95%;
		min-height: 70vh;
		margin-top: 50rpx;
		background-color: #eee;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;

		.title {
			padding: 20rpx 10rpx;
			background-color: rgb(85, 102, 205);
			border-radius: 20rpx 20rpx 0 0;
			color: white;
		}

		.activities {
			width: 100%;
			margin-top: 30rpx;

			.activity {
				display: flex;
				flex-direction: column;
				align-items: center;
				margin-bottom: 40rpx;
			}
		}

		.bottom {
			margin: 100rpx auto;
		}
	}

	.cover {
		background-color: #eee;
		width: 100vw;
		height: 100vh;
		position: absolute;
		opacity: 0;
	}
</style>
