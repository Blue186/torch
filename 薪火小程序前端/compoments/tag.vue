<template>
	<view class="tag" >
		<view class="ing box" v-if="color == 'orange'">
			<image class="image" src="../static/images/tag-orange.png" mode=""></image>
			<text class="name">{{name}}</text>
		</view>
		<view class="pre box"  v-else-if="color == 'green'">
			<image class="image" src="../static/images/tag-green.png" mode=""></image>
			<text class="name">{{name}}</text>
		</view>
		<view class="end box" v-else-if="color == 'gray'">
			<image class="image" src="../static/images/tag-gray.png" mode=""></image>
			<text class="name">{{name}}</text>
		</view>
		
		
	</view>
</template>

<script>
	export default {  
		data() {
			return {
				
			}
		},
		props:['name','color']
	}
</script>

<style lang="scss">
	.tag {
		/* text-align: center;
		color: white;
		font-size: 29rpx;
		width: 55rpx;
		height: 130rpx;
		background: linear-gradient(-45deg, transparent 22px, red 0) right, linear-gradient(45deg, transparent 22px, red 0) left;
		background-size: 50% 100%;
		background-repeat: no-repeat; */
		
		.box{
			position: relative;
			.image {
				width: 60rpx;
				height: 140rpx;
			}
			.name {
				top: 0;
				left: 14rpx;
				position: absolute;
				color: #fff ;
			}
		}
	}
</style>
