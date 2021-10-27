<template>
	<view class="userPage">
		<view class="identity">
			<view class="userInformation">
				<view class="avatar">
					<img src="../../images/文宣部头像.png" mode="aspectFit"></img>
				</view>
				<view class="other">
					<view class="information">
						<view class="nickname">
							小宣
						</view>
						<view class="id">
							<text>1234556r</text>
							<image src="../../static/个人_slices/mipmap-xhdpi/复制.png" mode=""></image>
						</view>
						<view class="sexAndBirthday">
							<text class="sex">女</text>
							<text class="birthday">2000/11/28</text>
						</view>
						<view class="role">
							管理员
						</view>
					</view>
					<view class="editInformation">
						<text>编辑资料</text>
						<image src="../../static/个人_slices/mipmap-xhdpi/right.png" mode=""></image>
					</view>
				</view>
			</view>
			<view class="userDescription">
				<text>个人简介</text>
				<image class="userDescriptionEdit" src="../../static/个人_slices/mipmap-xhdpi/tianxie.png" mode="">
				</image>
			</view>
		</view>
		<view class="userVolunteer">
			<view class="wrap">
				<view class="volunteerHour">
					<image src="../../static/个人_slices/mipmap-xhdpi/我的时长.png" mode=""></image>
					<text>99h</text>
				</view>
				<view class="currentVolunteer">
					<view class="title">
						我的志愿
					</view>
					<view class="content">

					</view>
				</view>
			</view>
		</view>
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
				<record class="record" v-for="item in 5"></record>
			</view>
		</view>
		</view>
	</view>
</template>

<script>
	import record from '../../compoments/volunteerRecords.vue'
	import myButton from '../../compoments/myButton.vue'
	export default {
		components: {
			myButton,
			record
		},
		data() {
			return {

			}
		},
		methods: {

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

				.avatar { 
					img {
						width: 200rpx;
						height: 200rpx;
					}

				}

				.other {
					margin-left: 30rpx;
					@include flex-row;
					// border: 1px solid #000;
					border-radius: 20rpx;

					.information {
						position: relative;

						.role {
							position: absolute;
							background-color: #5c69bb;
							border-radius: 40rpx;
							color: #fff;
							font-size: 25rpx;
							padding: 10rpx 20rpx;
							top: 0;
							left: 90rpx;

							min-width: 75rpx;
						}

						.nickname {
							font-size: 40rpx;
							font-weight: 400;
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
						}
					}

					.editInformation {
						font-size: 25rpx;
						margin-left: 200rpx;

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
				.node {
					
				}
				.line {
					
				}
			}
			.records {
				margin-top: 20rpx;
				float: right;
				margin-right: 50rpx;
				
				.record {
					
				}
			}
		}
	}
</style>
