<template>
	<view class="cu-timeline bacf padding marginTop">
	  <view class="cu-time">志愿记录</view>
	  <view class="cu-item text-blue">
	    <view class="bg-blue bac content">
	     <view class="flex">
	      <view class="bacRecord padding">
	      <text class="recordTime">2020/02/06</text>
	      <view class="recordText">
	      <text>XXXXXXXXXX志愿活动</text>
	      <text>+3.0h</text>
	      </view>
	      </view>
	      <view class="images middle padding imgBook">
	        <view style="width:35%;" class="middle">
	    <image src="https://636c-cloud1-1gj1ixwl4cd205b1-1306839659.tcb.qcloud.la/pencil.png?sign=1e939cbf7019e72ef47a51a657d2b010&t=1632906806" alt="" mode="widthFix" style="width:100%"></image>
		</view>
	    <text class="imgText">填写心得</text>
	      </view>
	     </view>
	    </view>
	    <view class="bg-cyan bac content">
	      <view class="flex">
	      <view class="bacRecord padding">
	      <view class="recordText">
	      </view>
	      </view>
	      <view class="images middle padding imgBook">
	        <view style="width:35%;" class="middle ">
	    <image src="https://636c-cloud1-1gj1ixwl4cd205b1-1306839659.tcb.qcloud.la/pencil.png?sign=78fbffbfd16e7b2ec2b24d5f1590b604&t=1632906926" alt="" mode="widthFix" style="width:100%"></image>
	    </view>
	    <text class="imgText">我的心得</text>
	      </view>
	     </view>
	    </view>
	  </view>
	</view>
</template>

<script>
	import a from"../colorui/components/cu-custom.js"
</script>

<style>
	@import "../colorui/main.wxss";
	@import "../colorui/icon.wxss";
	.bac{
	  background-color: #FFFBDD;
	  color: #000000;
	  border: 4rpx solid #000000;
	  border-radius: 20rpx!important;
	  padding-right: 0!important;
	  padding-left: 0!important;
	  padding-top: 0!important;
	  padding-bottom: 0!important;
	  /* display: flex; */
	  /* flex-direction: column; */
	}
	.bacRecord{
	  flex: 3;
	  /* background-color: pink; */
	  /* width: 60%; */
	}
	.images{
	  flex: 1;
	  border-left: 4rpx solid #000000;
	  /* width: 40%; */
	}
	.flex{
	  display: flex;
	}
	.recordTime{
	  padding: 0rpx 15rpx;
	  font-size: 25rpx;
	  background-color: #FFDC88;
	  border-top-right-radius: 20rpx;
	  border-bottom-right-radius: 20rpx;
	  text-align: left!important;
	}
	.recordText{
	  font-size: 32rpx;
	  margin-left: 10rpx;
	  display: flex;
	  justify-content: space-between;
	}
	.middle{
	  display: flex;
	  justify-content: center;
	  align-items: center;
	}
	.imgBook{
	  display: flex;
	  flex-direction: column;
	}
	.imgText{
	  font-size: 22rpx;
	  color: #666666;
	}
	.marginTop{
	  margin-top:20rpx ;
	}
	.paddingLeft{
	  padding-left: 20rpx;
	}
	.padding{
	  padding: 20rpx 0;
	}
	.bacf{
	  background-color: #fff;
	}
</style>
