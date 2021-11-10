<template>
	<view class="page">

		<swiper class="swiper" :indicator-dots="true" :autoplay="true" :interval="3000" :duration="1000"
			indicator-color="#eee" indicator-active-color="#fff" circular=true>
			<swiper-item v-for="image in images" :key="image.name">
				<view class="swiper-item">
					<image :src="image.url" mode="aspectFill" class="image"></image>
				</view>
			</swiper-item>
			
		</swiper>

	</view>
</template>

<script>
	export default {
		data(){
			return{
				
			}
		},
		props:['images']
	}
	
</script>

<style lang="scss">
	.page {
		border-radius: 20rpx;
	}

	.swiper {
		margin-top: 20rpx;

		border-radius: 20rpx;
		width: 90vw;
		height: 25vh;
		// background-color: red;

		.swiper-item {
			border-radius: 20rpx;

			.image {
				border-radius: 20rpx;
				width: 90vw;
				height: 25vh;
			}

		}


	}
</style>
