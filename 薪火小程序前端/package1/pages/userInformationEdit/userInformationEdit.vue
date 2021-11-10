<template>
	<view class="page">
		<view class="title">
			<image src="/package1/static/images/my.png" mode=""></image>
			<text>个人信息编辑</text>
		</view>
		<form class="'information">
			<view class="name">
				<label for="name">姓名：</label>
				
				<input type="text" value="" id="name" />
			</view>
			<view class="sex">
				<radio-group name="">
					<label class="sex">
						<radio value="male" id="sex"></radio>男
					</label>
					<label class="sex">
						<radio value="female" id="sex"></radio>女
					</label>
				</radio-group>

			</view>
			<view class="school">
				<label for="school">学校：</label>
				<input type="text" value="" id="school" />
			</view>
			<view class="qq">
				<label for="qq">QQ：</label>
				<input type="digit" value="" id="qq" />
			</view>
			<view class="phoneNumber">
				<label for="phoneNumber">电话：</label>
				<input type="digit" value="" id="phoneNumber" />
			</view>
			<view class="emailConfirm">
				<label for="email">邮箱：</label>
				<input type="number" value="" id="emile" v-model="email"/>
				<button type="default" class="sent" @click="sentEmail">发送验证码</button>
			</view>
			<view class="verificationCode">
				<label for="verificationCode">验证码：</label>
				<input type="digit" value="" id="verificationCode" v-model="eCode"/>
				<button type="default" class="confirm" @click="verificationCode">确认</button>
			</view>
			<view class="">
				<text>个人介绍</text>
			</view>
			<view class="selfIntroduction">
				<textarea auto-height=true class="textarea" value="" placeholder="介绍一下自己吧" maxlength= 100 />
			</view>
			<button type="default" class="save">保存</button>
		</form>
		
	</view>
</template>

<script>
	import {postEmailCode} from '../../../models/index.js'
	import {postEmail} from '../../../models/index.js'
	export default {
		data() {
			return {
				email:'921871539@qq.com',
				eCode:''
			};
		},
		methods:{
			sentEmail(){
				postEmail({
					'mail' : this.email
				}).then(res => {
					console.log(res)
				}) 
			},
			verificationCode(){
				postEmailCode({
					'code':this.eCode,
					'mail':this.email
				}).then(res => {
					console.log(res.data)
				})
			}
		}
	}
</script>

<style lang="scss">
	.page {
		background-color: #fff;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding-bottom: 300rpx;
		.title {
		margin-top: 20rpx;
			img {
				vertical-align: bottom;
				width: 50rpx;
				height: 50rpx;
			}
		}
		
		.information {
			.selfIntroduction {
				.textarea {
					
				}
			}
			.emailConfirm {
				input {
					width: 50%;
				}
				.sent {
				font-size: 20rpx;
				
			}
			}
			label {
				width: 150rpx;
				text-align: right;
			}
			
			input {
				display: inline-block;
				width: 80%;
				height: 80%;
			}
			view {
				width: 650rpx;
				background-color: #fff;
				border-radius: 10rpx;
				box-shadow: 3px 3px 8px 3px #eee ;
				margin-top: 40rpx;
				display: flex;
				align-items: center;
				justify-content: space-around;
				padding: 30rpx 10rpx;
			}
			.verificationCode {
				// width: 60%;
				input {
					width: 50%;
				}
				button {
					width: 150rpx;
					font-size: 20rpx;
					background-color: orange;
				}
				
			}
			.save {
				margin-top: 50rpx;
			}
		}
	}
</style>
