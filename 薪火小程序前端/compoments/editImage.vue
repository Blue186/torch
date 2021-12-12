<template>
	<view class="avatar box" @click="changeAvatar">
		<label for="name" class="label">头像：</label>
		<image :src="image" mode="" class="image"></image>
		<image src="/package1/static/images/right.png" mode="" class="right"></image>
	</view>
</template>

<script>
	import {
		pathToBase64,
		base64ToPath
	} from 'image-tools'
	export default {
		data(){
			return {
			image:''
			}
		},
		props:{
			avatarImage:{
				type:String,
				
			}
		},
		mounted() {
			// uni.$on('image',(res)=>{
			// 	console.log('res',res)
			// 	this.avatarImage = res
			// })
			this.image = this.avatarImage
			console.log(this.image)
			
		},
		methods:{
			changeAvatar() {
				uni.chooseImage({
					count: 1,
					crop: {
						quality: 50,
						width: 200,
						height: 200
					},
			
					success: (res) => {
						var path = res.tempFilePaths
						console.log(res.tempFilePaths)
						uni.compressImage({
						  src: path[0],
						  quality: 20,
						  success: res => {
						    // console.log(res.tempFilePath)
							pathToBase64(res.tempFilePath)
									.then(base64 => {
										this.image = base64 
										console.log('压缩后',base64)
										uni.$emit('backBase64',this.image)   //将图片返回edit页
									})
									.catch(error => {
										console.error(error)
									})
						  }
						})
					}
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
	// .label {
	// 	width: 20%;
	// }
	.avatar {
		padding: 10rpx;
		overflow: hidden;
	
		.label {
			width: 50%;
		}
	
		.image {
			width: 130rpx;
			height: 130rpx;
	
		}
	
		.right {
			width: 40rpx;
			height: 40rpx;
		}
	}
	.box {
		box-sizing: border-box;
		width: 650rpx;
		background-color: #fff;
		border-radius: 10rpx;
		box-shadow: 3px 3px 8px 3px #eee;
		margin-top: 40rpx;
		display: flex;
		align-items: center;
		justify-content: space-around;
		padding: 20rpx 30rpx;
		box-shadow: 3px 3px 3px 1px #6f6f6f;
		z-index: 1;
	}
</style>
