<template>
	<view>
	<view class="modelHeader">
		创建模板
	</view>
	<uni-forms ref="form" :rules="">
		<uni-forms-item label="" name="">
			
		</uni-forms-item>
	</uni-forms>
	<label for="modelName"> <text>模板名称: </text></label>
	<input id="modelName" type="text" value="" />
	
	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
	input {
		display: inline-block;
		border: 1px solid #eee;
	}
</style>
