<template>
	<view class="activity">
		<view class="pic">
			<image :src="info.actImage" mode="aspectFill" class="image"></image>
		</view>
		<view class="activityInformation">
			<view class="nameAndLocation">
				<text class="activityName">{{info.name}}</text>
				<view class="location">
					<image class="image" src="/static/images/dingwei@2x.png" mode=""></image>
					{{info.address}}
				</view>
			</view>
			<view class="time">
				<!-- <text class="activityTime">{{info.time}}</text> -->
				<text class="activityDate">{{info.volTimePeriod}} </text>
			</view>
			<text class="activityNumber">招募人数：{{info.totalNumber}}/{{info.headcount}}人</text>
		</view>
		
		<tag class="tag" name="招募中" color='orange' v-if="info.isPass === 1"></tag>
		<tag class="tag" name="预招募" color='green' v-if="info.isPass === 0"></tag>
		<tag class="tag" name="已结束" color='#eee' v-if="info.isPass === 2"></tag>
		
		<button type="default" class="btn" @click="goDetail">详情</button>

	</view>
</template>

<script >
	
	import tag from "./tag.vue"
	export default {
		props:['info'],
		components:{
			tag
		},
		data() {
			return {
				
			}
		},
		
		methods: {
			goDetail(){
				console.log("点到我了")
				
				
				uni.navigateTo({
					url:'/package1/pages/activityDetail/activityDetail?id=999',
					animationType:"slide-in-right",
					
					success: (res)=> {
						 res.eventChannel.emit('postActivityInformation', this.info)
					 
					}
				})
			},
			
			
		}
	}
</script>

<style lang="scss" scoped>
	.activity {
		background-color: #fff;
		position: relative;
		// border: 1rpx solid #808080;
		box-shadow: 2px 2px 4px 4px #dadada;
		width: 674rpx;
		height: 400rpx;
		border-radius: 20rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		color:#000;
		.btn {
			position: absolute;
			bottom: 20rpx;
			right: 20rpx;
			height: 70rpx;
			font-size: 35rpx;
			line-height: 70rpx;
			background-color: rgb(85,102,205);
			color: white;
			
		}
		.tag {
			position: absolute;
			right: 40rpx;
			width: 55rpx;
		}

		.pic {
			margin-top: 10rpx;
			width: 95%;
			height: 60%;
			background-color: #4CD964;
			.image {
				height: 100%;
				width: 100%;
			}
		}

		.activityInformation {
			width: 95%;
			display: flex;
			flex-direction: column;
			align-items: flex-start;
			.activityTime{
				font-size: 28rpx;
				color: rgb(85,102,205);
				margin-right: 20rpx;
			}
			.activityDate {
				font-size: 28rpx;
				
			}
			.activityNumber {
				font-size: 32rpx;
			}
			.nameAndLocation {
				display: flex;
				align-items: center;
				.activityName {
					font-weight: bold;
				}
				.location {
					margin-left: 20rpx;
					display: flex;
					align-items: center;
					font-size: 30rpx;
					.image {
						width: 35rpx;
						height: 35rpx;
					}
				}
			}
		}
	}
</style>
