<template>
	<view class="userPage">
		<!-- 用户个人信息 -->
		<view class="identity">
			<view class="userInformation">
				<view class="avatar">
					<img :src="userInfo.avatarImage" mode="aspectFill"></img>
				</view>
				<view class="other">
					<view class="information">
						<view class="nickname">
							<text>{{userInfo.nickName}}</text>
							<view class="role" v-if="userInfo.level === 2">
								游客
							</view><view class="role" v-if="userInfo.level === 1">
								志愿者
							</view>
						</view>
						<view class="id">
							<text>{{userInfo.account}}</text>
							<image src="/static/images/copy.png" mode="" @click="copy('123')"></image>
						</view>
						<view class="sexAndBirthday">
							<text class="sex">{{userInfo.sex}}</text>
							<!-- <text class="birthday">2000/11/28</text> -->
						</view>
						<!-- 用户角色 -->

					</view>
					<view class="editInformation">
						<text @click="editInformation">编辑资料</text>
						<image src="/static/images/right.png" mode=""></image>
					</view>
				</view>
			</view>
			<!-- 个人简介 -->
			<view class="userDescription">
				<text>自我介绍：“{{userInfo.selfIntro}}” </text>
				<image class="userDescriptionEdit" src="/static/images/tianxie.png" mode="" @click="editInformation">
				</image>
			</view>
		</view>
		<!-- 志愿相关信息 -->
		<view class="userVolunteer">
			<view class="wrap">
				<!-- 志愿时长 -->
				<view class="volunteerHour">
					<image src="/static/images/myHour.png" mode=""></image>
					<text>{{userInfo.totalTime}}h</text>
				</view>
				<view class="currentVolunteer" @click="goMyVolunteer">
					<view class="title">
						我的志愿
					</view>
					<view class="content">

					</view>
				</view>
			</view>
		</view>
		<!-- 志愿活动记录 -->
		<view class="volunteerRecord">
			<text class="title">志愿记录</text>
			<view class="content">
				<view class="timeLine">
					<view class="node">

					</view>
					<view class="line">

					</view>
				</view>
				<view class="records">
					<record class="record" v-for="(item,index) in activityRecords" :record="item" :key='item.id'></record>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {getVolunteerRecords} from '../../models/uesrModel.js'
	
	import {
		getUserImformation
	} from '../../models/indexModel.js'
	import record from '../../compoments/volunteerRecords.vue'
	import myButton from '../../compoments/myButton.vue'
	export default {

		components: {
			myButton,
			record
		},
		data() {
			return {
				nickname: '志愿者',
				avatarUrl: '/static/images/department.png',
				activityRecords:{},
				userInfo: {
					sex: 1,
					selfIntro:'',
					totalTime:0
				}
			}
		},
		onLoad() {
			this.getUserInfo()
			this.getRecords()
			// 修改数据后刷新
			uni.$on('updateUserInfo', (res) => {
				console.log(res)
				this.getUserInfo()
			})
		},
		onUnload(){
			uni.$off('updateUserInfo')
		},
		// 下拉刷新
		onPullDownRefresh() {
			
			this.getUserInfo()
			console.log("刷新了")
			uni.stopPullDownRefresh()
		},
		methods: {
			//获取志愿记录
			getRecords(){
				getVolunteerRecords('1/3').then(res=>{
					this.activityRecords = res.data.records
					console.log('志愿记录',res)
				},err=>{
					console.log(err)
				})
			},
			goMyVolunteer() {
				uni.navigateTo({
					url: '/package3/pages/myVolunteer/myVolunteer',

				})
			},
			getUserInfo() {
				uni.showLoading({
					title: '加载中'
				})
				getUserImformation().then(res => {
					console.log("用户信息数据", res.data)
					if (res.data) {
						this.userInfo = res.data
						if (this.userInfo.sex === 1) {
							this.userInfo.sex = '男'
						} else {
							this.userInfo.sex = '女'
						}
					}

					uni.hideLoading()
				})

			},
			editInformation() {
				uni.navigateTo({
					url: '/package1/pages/userInformationEdit/userInformationEdit?logined=true',
					events: {
						test: function(res) {
							console.log(res)
						}
					},
					success: (res) => {
						// console.log(this)
						res.eventChannel.emit('userInfo', this.userInfo)
					}
				})
			},
			// 复制账号
			copy(value) {
				uni.setClipboardData({
					data: value,

				})

			}
		}
	}
</script>

<style lang="scss" scoped>
	// 混合
	@mixin flex-column {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	@mixin flex-row {
		display: flex;
		// flex-direction: column;
		align-items: center;
	}


	.userPage {
		background-color: #eee;
		@include flex-column;

		.identity {
			width: 100%;
			padding: 20rpx 0;
			background-color: #fff;
			@include flex-column;

			.userInformation {
				@include flex-row;
				width: 90%;

				.avatar {
					img {
						width: 150rpx;
						height: 150rpx;
					}

				}

				.other {
					margin-left: 30rpx;
					@include flex-row;
					// border: 1px solid #000;
					border-radius: 20rpx;

					.information {
						position: relative;



						.nickname {
							display: flex;
							font-size: 40rpx;
							font-weight: 400;

							.role {
								background-color: #5c69bb;
								border-radius: 40rpx;
								color: #fff;
								font-size: 25rpx;
								padding: 10rpx 20rpx;
								margin-left: 20rpx;
								// min-width: 75rpx;
							}
						}

						.id {
							color: #bababa;
							font-size: 25rpx;

							text {
								margin-right: 20rpx;
							}

							margin: 10rpx 0;

							image {
								width: 25rpx;
								height: 25rpx;
							}
						}

						.sexAndBirthday {
							color: #bababa;
							font-size: 25rpx;

							text {
								margin-left: 10rpx;
							}
						}
					}

					.editInformation {
						position: absolute;
						top: 120rpx;
						right: 20rpx;
						font-size: 25rpx;

						// margin-left: 200rpx;
						image {
							vertical-align: text-bottom;
							width: 30rpx;
							height: 30rpx;
						}
					}
				}
			}

			.userDescription {
				width: 90%;
				border: 4rpx solid #5c69bb;
				background-color: #e8edff;
				border-radius: 20rpx;
				height: 120rpx;
				position: relative;
				text-indent: 2em;
				padding-top: 10rpx;

				image {
					width: 50rpx;
					height: 50rpx;
					position: absolute;
					right: 10rpx;
					top: 40rpx;
				}
			}
		}

		.userVolunteer {
			background-color: #fff;

			width: 100%;
			margin-top: 20rpx;
			padding: 20rpx 0;

			.wrap {

				@include flex-row;
				width: 90%;
				margin: 0 auto;
				justify-content: space-between;

				.volunteerHour {
					position: relative;

					image {
						width: 150rpx;
						height: 150rpx;
					}

					text {
						position: absolute;
						top: 60rpx;
						left: 40rpx;
						color: #7272b5;
						font-size: 40rpx;

					}
				}

				.currentVolunteer {
					.title {
						margin-bottom: 10rpx;
					}

					.content {
						height: 110rpx;
						width: 430rpx;
						border: 2px solid #000000;
						background-color: #ffdc88;
						border-radius: 20rpx;
					}
				}
			}
		}

		.volunteerRecord {
			background-color: #fff;
			margin-top: 20rpx;
			width: 100%;
			padding: 10rpx;
			padding-left: 40rpx;
			overflow: hidden;

			.timeLine {
				margin-top: 20rpx;
				float: left;
				border: 1px solid orange;
				width: 50rpx;
				height: 500rpx;

				.node {}

				.line {}
			}

			.records {
				margin-top: 20rpx;
				float: right;
				margin-right: 50rpx;

				.record {}
			}
		}
	}
</style>
