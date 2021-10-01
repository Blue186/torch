<template>
<view class="bacf">
  <view class="nowVolunter marginTop">
<view class="VolunterTime">
  <view style="width:100%"  class="middle">
    <image src="https://636c-cloud1-1gj1ixwl4cd205b1-1306839659.tcb.qcloud.la/%E6%88%91%E7%9A%84%E6%97%B6%E9%95%BF.png?sign=9478283b18b6a148ac9513891ac5f379&t=1632906974" alt="" mode="widthFix" style="width:100%"></image></view>
    <text class="times">52.0h</text>
</view>
<view class="nowThing">
<view class="left">
  <text class="text">我的志愿</text>
<view class="elseVolunter"></view></view>
</view>
</view>
</view>
</template>

<script>
</script>

<style>
	.nowVolunter{
	  width: 90%;
	  margin-left: 5%;
	  height: 220rpx;
	  display: flex;
	  padding: 20rpx 0;
	}
	.VolunterTime{
	  flex: 2;
	  position: relative;
	  /* background-color: black; */
	}
	.nowThing{
	  flex: 5;
	}
	.times{
	  position: absolute;
	  top: 80rpx;
	  left: 40rpx;
	  color:#5566CD ;
	  font-size: 45rpx;
	}
	.elseVolunter{
	  height: 140rpx;
	  background-color: #FFDC88;
	  border: 4rpx solid #333333;
	  box-sizing: border-box;
	  border-radius: 20rpx;
	  width: 100%
	}
	.text{
	  font-size: 30rpx;
	  font-weight: 700;
	}
	.left{
	  margin-left: 40rpx;
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
	.middle{
	  display: flex;
	  justify-content: center;
	  align-items: center;
	}
</style>
