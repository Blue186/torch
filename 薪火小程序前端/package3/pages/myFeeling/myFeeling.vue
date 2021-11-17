<template>
	<view class="page">
		<view class="container">
			<view class="title">
				<text>解放碑志愿活动</text>
			</view>
			<view class="evaluate">
				<view class="rate">
					<text>活动评价</text>
					<uni-rate activeColor='#ffed93' value="5" :is-fill="false" size=27 :touchable='flase'></uni-rate>
				</view>


			</view>
			<view class="feeling">
				<textarea maxlength=1000 value="" placeholder="填写志愿心得、奉献志愿故事" v-model="feeling" />
			</view>
			<view class="images">

				<view class="image" v-for="image in images" :id="image">
					<image :src="image" mode="center"></image>
				</view>
				<view class="entry" @click="chooseImages">
				</view>
			</view>
			<button type="default" open-type="share" >发布</button>
		</view>
		
	</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

				images: [],
				feeling:'',
				
			};
		},
		methods: {
			chooseImages() {
				uni.chooseImage({
					count: 9, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					// sourceType: ['album'], //从相册选择
					success: (res) => {
						// console.log(...res.tempFilePaths)
						this.images.push(...res.tempFilePaths)  
						console.log(JSON.stringify(res.tempFilePaths));
					}
				});
			}
		}
	}
</script>

<style lang="scss">
	.page {
		background-color: #f8f8f8;
		padding: 20rpx 25rpx;

		.container {
			padding: 40rpx 40rpx;
			background-color: #ffffff;
			// min-height: 100vh;
			box-shadow: 3px 3px 10px 8px #eee;
			border-radius: 20rpx;

			.title {
				font-size: 40rpx;
				font-weight: 550;

			}

			.evaluate {
				margin: 30rpx 40rpx;

				.rate {
					display: flex;

					text {
						margin-right: 20rpx;
					}
				}
			}

			.feeling {
				margin-bottom: 30rpx;
			}

			.images {
				overflow:hidden;
				margin-bottom: 100rpx;
				display: flex;
				justify-content: flex-start;
				align-items: center;
				flex-wrap: wrap;
				.entry {
					width: 180rpx;
					height: 180rpx;
					margin-left: 30rpx;
					background-size: contain;
					background-repeat: no-repeat;
					background-image: url(../../static/images/post.png);
				}

				.image {
					margin-bottom: 20rpx;
					border: 1px solid #000;
					image {
						width: 180rpx;
						height: 180rpx;
					}
					
				}
				.image:nth-child(3n+2) {
					margin-left: 30rpx;
					margin-right: 30rpx;
				}
			}
		}
	}
</style>
