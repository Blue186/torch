<template>
	<view class="detailPage">
		<view class="header">
			{{activityInfo.name}}
			<tag name='进行中'></tag>
		</view>
		<view class="activityMessage">
			<view class="title">
				活动信息
			</view>
			<view class="content">
				<view class="messageItem">
					<view class="messageName">
						服务内容:
					</view>
					<view class="messageContent">
						{{activityInfo.content}}
					</view>
				</view>
				<view class="messageItem">
					<view class="messageName">
						地点:
					</view>
					<view class="messageContent">
						{{activityInfo.content}}
					</view>
				</view>
				<view class="messageItem">
					<view class="messageName">
						日期:
					</view>
					<view class="messageContent">
						{{activityInfo.volTimePeriod}}
					</view>
				</view>
				<view class="messageItem">
					<view class="messageName">
						时长:
					</view>
					<view class="messageContent">
						{{activityInfo.volTimeDesc}}
					</view>
				</view>
				<view class="messageItem">
					<view class="messageName">
						招募人数:
					</view>
					<view class="messageContent">
						{{activityInfo.headcount}}
					</view>
				</view>
			</view>


		</view>
		<view class="announcements">
			<view class="title">
				注意事项
			</view>
			<view class="content">
				<textarea disable height='40rpx'>{{activityInfo.attention}}</textarea>
			</view>
		</view>

		<view class="apply">
			<view class="title">
				<text>志愿报名</text>
				<text class="deadline">截止时间：{{activityInfo.passTime}}</text>
			</view>
			<view class="applyDate">
				<view class="dateBox" v-for="(item,index) in activityDetail"
					:class="dateIndex === index ? 'active' : ''" :key='item.id' @click="changeDate(index)">
					<text>{{item.servicePeriod}}</text>
				</view>
			</view>
			<view class="applyTime">
				<view v-for="(item,index) in activityDetail" :key='item.id'>
					<view class="timeBox" v-for="(item1,index1) in item.activityTimes" :key='item1.id'
						v-if="dateIndex === index">
						<text class="time">{{format(item1.startTime,'time')}}</text>
						<view class="arrow">
							<text>{{format(item1.endTime - 1638095994000,'duration'),}}</text>
							<text class="line"></text>
							<text>{{item1.address}}</text>
						</view>
						<text class="time">{{format(item1.endTime,'time')}}</text>
						<my-button class="myButton" @click.native="apply(item1.actChiId,item1.actId,item1.id,applyStatus)"
							:backgroundColor="item1.recruiting==item1.recruited ? '#eee' : '#ffaa7f' "
							:name="'报名('+item1.recruited+'/'+item1.recruiting+')'"></my-button>
					</view>
				</view>
			</view>
			<view class="note">
				<text>说明：</text>
				<text>阿巴阿巴阿巴</text>
			</view>
		</view>
	</view>
</template>

<script>
	import {cancelSignUp} from '../../../models/indexModel.js'
	import {
		signUp
	} from '../../../models/indexModel.js'
	import {
		getActivityDetail
	} from '../../../models/indexModel.js'
	import tag from '../../../compoments/tag.vue'
	import myButton from '../../../compoments/myButton.vue'
	import {
		formatTime
	} from '../../../utils/formatTime.js'

	export default {
		components: {
			tag,
			myButton
		},

		data() {
			return {
				dateIndex: 0,
				activityInfo: '',
				activityDetail: {},
			}
		},
		onLoad(res) {
			if (res) {
				const eventChannel = this.getOpenerEventChannel();
				eventChannel.on('postActivityInformation', (data) => {
					console.log('父活动数据', data)
					this.activityInfo = data;
					this.activityInfo.passTime = formatTime(this.activityInfo.passTime)
				})
			}

			this.getDetail()
			// this.getactivityInfo()
		},
		methods: {
			// getactivityInfo(){
			// 	this.activityInfo = '22'
			// 	let that = this
			// 	uni.$on('postActivityInformation',(res)=>{
			// 			that.activityInfo = res;
			// 			that.dateIndex = 100
			// 		console.log('父活动数据',this.activityInfo)
			// 	})
			// },
			apply(dadId, sonId, id,applyStatus) {
				
				signUp({
					"actChiId": dadId,
					"actId": sonId,
					"actTimesId": id
				}).then(res => {
					console.log(res)
				}, err => {
					console.log(err)
				})
			},
			getDetail() {
				getActivityDetail('11').then(res => {
					console.log('活动细节数据', res.data)
					this.activityDetail = res.data
					// this.activityDetail.activityTimes.forEach((item)=>{
					// 	if(item.recruited === item.recruiting)
					// 		item.
					// })
					// if(this.activityDetail.activityTimes.recruited === this.activityDetail.activityTimes.recruiting)

				})
			},
			format(res, method) {
				return formatTime(res, method)
			},
			changeDate(index) {
				this.dateIndex = index
			}

		},
		// computed:{
		// 	buttonColor(){
		// 		if(this.activityDetail.activityTimes.recruited === this.activityDetail.activityTimes.recruiting)
		// 		return 
		// 	}
		// }
	}
</script>

<style lang="scss">
	.title {
		display: inline-block;
		padding: 0 40rpx;
		height: 70rpx;
		line-height: 70rpx;
		font-weight: 600;
		background-color: rgb(255, 220, 136);
		border: 5rpx solid #000;
		border-left: none;
		border-radius: 0 10rpx 10rpx 0;
	}

	.content {
		width: 83%;
		margin: 20rpx auto;
		border: 2px solid #fd8933;
		border-radius: 10rpx;
		background-color: #eefdff;
		padding: 25rpx;
	}

	.detailPage {
		width: 95%;
		border: 5rpx solid #000;
		margin: 20rpx auto;
		border-radius: 15rpx;
		background-color: rgb(255, 251, 223);

		.header {
			margin-top: 20rpx;
			height: 100rpx;
			border-bottom: 2px solid #ffc43a;
			font-size: 50rpx;
			font-weight: 500;
			text-align: center;

			.tag {
				position: absolute;
				top: 20rpx;
				right: 100rpx;
			}
		}

		.activityMessage {
			margin-top: 50rpx;

			.content {
				.messageItem {
					display: flex;

					.messageName {
						width: 25%;
						text-align: right;
					}
				}
			}

		}

		.announcements {
			margin-top: 50rpx;
		}

		.apply {
			.deadline {
				font-size: 24rpx;
				padding-left: 20rpx;
			}

			.applyDate {
				display: flex;
				margin-top: 20rpx;
				border: 2px solid #fd8933;
				border-radius: 10px;
				background-color: #f0ffed;

				.dateBox {
					border: 1px solid #000;
					border-radius: 40px;
					background-color: #fff;
					padding: 0 10rpx;
					margin: 20rpx;
				}

				.active {
					background-color: #fd8933;
				}
			}

			.applyTime {
				width: 90%;
				margin: 0 auto;

				.timeBox {
					background-color: #fff;
					margin-top: 30rpx;
					border: 1px solid #000;
					display: flex;
					padding: 40rpx 10rpx;
					border-radius: 10px;
					align-items: center;

					.time {
						text-align: right;
						width: 100rpx;
						font-size: 40rpx;
					}

					.myButton {
						margin-left: 30rpx;
					}

					.arrow {
						width: 120rpx;
						margin: 0 20rpx;
						text-align: center;
						display: flex;
						flex-direction: column;
						font-size: 20rpx;

						.line {

							border: 1px solid #000;
						}
					}
				}
			}

			.note {
				color: #c3c3c3;
				font-size: 25rpx;
				margin: 40rpx 35rpx;

			}
		}
	}
</style>
