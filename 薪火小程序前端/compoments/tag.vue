<template>
	<view class="tag" >
		{{name}}
	</view>
</template>

<script>
	export default {  
		data() {
			return {
				
			}
		},
		props:['name']
	}
</script>

<style>
	.tag {
		
		text-align: center;
		color: white;
		font-size: 29rpx;
		
		width: 55rpx;
		height: 130rpx;
		background: linear-gradient(-45deg, transparent 22px, red 0) right, linear-gradient(45deg, transparent 22px, red 0) left;
		background-size: 50% 100%;
		background-repeat: no-repeat;
	
	}
</style>
