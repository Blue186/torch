<template>
	<view class="detailPage">
		<view class="header">
			解放碑街道清理
			<tag name='进行中'></tag>
		</view>
		<view class="activityMessage">
			<view class="title">
				活动信息
			</view>
			<view class="content">
				<view class="messageItem" v-for="item in activityMessage" >
					<view class="messageName">
						{{item.name}}:
					</view>
				
			    </view>
			</view>
			
			
		</view>
		<view class="announcements">
			 <view class="title">
				注意事项
			</view>
			<view class="content">
				<view class="annuncement" v-for="(item ,index) in announcements">
					{{index+1}}、{{item}}
				</view>
				
			</view>
		</view>
		
		<view class="apply">
			<view class="title">
				<text>志愿报名</text>
				<text class="deadline">截止至5月31日 18:00</text>
			</view>
			<view class="applyDate">
				<view class="dateBox" v-for="(item,index) in applyDate" :class="dateIndex === index ? 'active' : ''">
					<text>{{item.date}}</text>
				</view>
			</view>
			<view class="applyTime">
				<view class="timeBox" v-for="item in applyTime" >
					<text class="time">{{item.startTime}}:00</text>
					<view class="arrow">
						<text>{{item.endTime - item.startTime}}小时</text>
						<text class="line"></text>
						<text>{{item.address}}</text>
					</view>
					<text class="time">{{item.endTime}}:00</text>
					<my-button class="myButton" name="报名(1/5)"></my-button>
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
	import tag from '../../compoments/tag.vue'
	import myButton from '../../compoments/myButton.vue'
	export default {
		components:{
			tag,
			myButton
		},
		data() {
			return {
				dateIndex:0,
				announcements:[
					"这是一个注意事项",
					"这也是一个注意事项",
					"这还是一个注意事项"
				],
				activityMessage:[
					{
						name:'服务内容',
					},
					{
						name:'志愿地点',
					},
					{
						name:'活动日期',
					},
					{
						name:'时长',
					},
					{
						name:'招募人数',
					}
				],
				applyDate:[
					{
					date:'6月1日'
					},	
					{
					date:'6月2日'
					},	
					{
					date:'6月3日'
					},
				],
				applyTime:[
					{
						startTime:'9',
						endTime:'12',
						address:'朝天门',
						status:'0'
					},{
						startTime:'16',
						endTime:'19',
						address:'朝天门',
						status:'1'
					},{
						startTime:'9',
						endTime:'12',
						address:'解放碑',
						status:'2'
					},
				]	
			}
		},
		methods: {
			
		}
	}
</script>

<style lang="scss">
	.title {
		display: inline-block;
		padding: 0 40rpx;
		height: 70rpx;
		line-height: 70rpx;
		font-weight: 600;
		background-color:  rgb(255, 220, 136);
		border: 5rpx solid #000;
		border-left: none;
		border-radius: 0  10rpx 10rpx 0;
	}
	.content {
		width: 80%;
		margin: 20rpx auto;
		border: 2px solid #fd8933;
		border-radius: 10rpx;
		background-color: #eefdff;
	}
	.detailPage {
		width: 95%;
		border: 5rpx solid #000;
		margin: 20rpx auto;
		border-radius: 15rpx;
		background-color: rgb(255,251,223);
	
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
					background-color:#fd8933 ;
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
					.myButton{
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
