<template>
	<view class="page">
		<view class="container">
			<view class="title">
				<text>{{name}}</text>
			</view>
			<view class="evaluate">
				<view class="rate">
					<text>活动评价</text>
					<uni-rate activeColor='#ffed93' v-model="rate" :is-fill="false" size=27 :touchable='flase'>
					</uni-rate>
				</view>


			</view>
			<view class="feeling">
				<textarea maxlength=1000 value="" placeholder="填写志愿心得、奉献志愿故事" v-model="feeling" />
			</view>
			<view class="images">

				<view class="imageBox" v-for="image in images" :key='uuid'>
					<image :src="image" mode="center" class="image"></image>
				</view>
				<view class="entry" @click="chooseImages">
				</view>
			</view>
			<button type="default" @click="postFeeling()">发布</button>
		</view>

	</view>
	</view>
</template>

<script>
	import {
		postImages
	} from '../../../models/uesrModel.js'
	import {
		getActicityFeeling
	} from '../../../models/uesrModel.js'
	import uuid from '../../../utils/uuid.js'
	import {
		pathToBase64,
		base64ToPath
	} from 'image-tools'
	import {
		postActicityFeeling
	} from '../../../models/uesrModel.js'
	export default {
		data() {
			return {

				images: [],
				feeling: '',
				name: '',
				id: '',
				rate: 5,
				content: ''
			};
		},
		onLoad(res) {
			if (res) {
				this.name = res.name
				this.id = res.actId
				if (res.wrote === '1') { //判断是否有写过心得，写过加载心得
					this.getFeeling()
				}
			}


		},
		methods: {
			chooseImages() {
				uni.chooseImage({
					count: 9, //默认9
					crop: {
						quality: 50,
					},
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					success: (res) => {
						console.log(JSON.stringify(res.tempFilePaths));
						var path = res.tempFilePaths
						console.log(res.tempFilePaths)
						// 压缩图片
						path.map((res) => {
							uni.compressImage({
								src: res,
								quality: 20,
								success: res => {
									console.log("压缩图片后", res)
									// console.log(res.tempFilePath)
									pathToBase64(res.tempFilePath)
										.then(base64 => {
											this.images.push(base64)
											console.log('压缩后', base64)

										})
										.catch(error => {
											console.error(error)
										})
								}
							})
						})

					}
				});
			},
			postFeeling() {
				postImages(this.images).then(res => {
					console.log("上传图片返回", res)
					postActicityFeeling({
						"actId": this.id,
						"actStars": this.rate,
						"content": this.feeling,
						"imagesUrls": res.data
					}).then(res => {
						console.log(res)
						uni.navigateBack()
					}, err => {
						console.log(err)
					})
				}, err => {
					console.log(err)
				})

			},
			getFeeling() {
				getActicityFeeling(this.id).then(res => {
					console.log(res)
				}, err => {
					console.log(err)
				})
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
				overflow: hidden;
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

				.imageBox {
					margin-bottom: 20rpx;
					// border: 1px solid #000;
					margin-left: 20rpx;

					.image {
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
