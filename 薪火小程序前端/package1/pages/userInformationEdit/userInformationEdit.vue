<template>
	<view class="page">
		<view class="title">
			<image src="/package1/static/images/my.png" mode="aspectFill" class="image"></image>
			<text>个人信息编辑</text>
		</view>

		<!-- 填写的信息 -->
		<form class="'information ">
			<!-- 头像 -->
			<editImage :avatarImage=userInfo.avatarImage></editImage>
			<!-- 名字 -->
			<view class="name box" :class="focusStatus.name ? 'getFocus' : ''">
				<label for="name" class="label">姓名：</label>
				<input :focus='focusStatus.name' @focus='getFocus' class="input" type="text" value="" id="name"
					placeholder="填写真实姓名" confirm-type="next" cursor-spacing=100 confirm-hold=true maxlength="12"
					v-model="userInfo.name" />
			</view>
			<!-- 昵称 -->
			<view class="nickname box" :class="focusStatus.nickname ? 'getFocus' : ''">
				<label for="name" class="label">昵称：</label>
				<input :focus='focusStatus.nickName' @focus='getFocus' class="input" type="text" value="" id="nickname"
					cursor-spacing=100 confirm-type="next" v-model="userInfo.nickName" placeholder="请填写你的昵称" />
			</view>
			<!-- 性别 -->
			<view class="sex box">
				<label class="label">性别：</label>
				
				<picker @change="sexChange" :range="sexPick" class="input">
					<view class="uni-input">{{sexPick[sexIndex]}}</view>
				</picker>
			</view>
			<!-- 学校 -->
			<view class="school box" :class="focusStatus.school ? 'getFocus' : ''" @click="showPick">
				<label for="school" class="label">学校：</label>
				<input :focus='focusStatus.school' @focus='getFocus' class="input" type="text" disabled value=""
					id="school" cursor-spacing=100 confirm-type="next" v-model="userInfo.school"
					placeholder="请选择你的学校" />
				<schoolPicker themeColor="#000" ref="schoolPicker" :pickerValueDefault="cityPickerValueDefault"
					@onConfirm="onConfirm">
				</schoolPicker>
			</view>
			<!-- 年级 -->
			<view class="grade box" :class="focusStatus.grade ? 'getFocus' : ''">
				<label for="school" class="label">年级：</label>
				<input :focus='focusStatus.grade' @focus='getFocus' class="input" type="text" value="" id="grade"
					cursor-spacing=100 confirm-type="next" v-model="userInfo.grade" placeholder="请填写你的年级" />
			</view>
			<!-- QQ -->
			<view class="qq box" :class="focusStatus.qq ? 'getFocus' : ''">
				<label for="qq" class="label">QQ：</label>
				<input :focus='focusStatus.qq' @focus='getFocus' class="input" type="digit" value="" id="qq"
					cursor-spacing=100 confirm-type="next" v-model="userInfo.qq" placeholder="请填写你的QQ" />
			</view>
			<!-- 电话 -->
			<view class="phoneNumber box" :class="focusStatus.phoneNumber ? 'getFocus' : ''">
				<label for="phoneNumber" class="label">电话：</label>
				<input :focus='focusStatus.phone' @focus='getFocus' class="input" type="digit" value="" id="phoneNumber"
					cursor-spacing=100 confirm-type="next" v-model="userInfo.phone" placeholder="请填写你的电话" />
			</view>
			<!-- 志愿账号 -->
			<view class="volAccount box" :class="focusStatus.volAccount ? 'getFocus' : ''">
				<label for="phoneNumber" class="label">志愿账号：</label>
				<input :focus='focusStatus.volAccount' @focus='getFocus' class="input" type="text" value=""
					id="volAccount" placeholder="填写中国志愿服务网用户名" confirm-type="next" v-model="userInfo.volAccount" />
			</view>
			<!-- 邮箱 -->
			<view class="emailConfirm box" :class="focusStatus.email ? 'getFocus' : ''">
				<label for="email" class="label">邮箱：</label>
				<input :focus='focusStatus.email' @focus='getFocus' class="input" type="text" value="" id="emile"
					cursor-spacing=100 confirm-type="next" v-model="userInfo.email" placeholder="请填写你的邮箱" />
				<button type="default" class="sent" @click="sentEmail" v-if="sentEmailButton"
					:class="emailButtonContent === '发送成功 ' ? 'active' : ''">{{emailButtonContent}}{{countDown}}</button>
			</view>
			<!-- 验证码 -->
			<view class="verificationCode box" v-if="!haveEmail"
				:class="focusStatus.verificationCode ? 'getFocus' : ''">
				<label for="verificationCode" class="label">验证码：</label>
				<input :focus='focusStatus.verificationCode' @focus='getFocus' class="input" type="digit" value=""
					id="verificationCode" v-model="eCode" cursor-spacing=100 confirm-type="next" placeholder="填写你收到的验证码"
					maxlength="4" />
				<button type="default" class="confirm button" @click="verificationCode">确认</button>
			</view>
			<!-- 自我介绍 -->
			<view class="selfIntroduction box" :class="focusStatus.selfIntroduction ? 'getFocus' : ''">
				<label class="label">自我介绍：</label>
				<textarea :focus="focusStatus.selfIntroduction" @focus='getFocus' auto-height=true class="textarea"
					id="selfIntroduction" value="个人介绍" placeholder="介绍一下自己吧" maxlength=100 confirm-type="done"
					v-model="userInfo.selfIntro" />
			</view>
			<view class="note"  v-if="warning!=''">{{warning}}</view>
			<button type="default" class="save" hover-class="save_hover" @click="postInfo">保存</button>

		</form>

	</view>
</template>

<script>
	import editImage from '../../../compoments/editImage.vue'
	import schoolPicker from '../../../compoments/schoolPicker/schoolPicker.vue'; //学校列表插件
	import {
		pathToBase64,
		base64ToPath
	} from 'image-tools'
	import {
		postEmailCode
	} from '../../../models/indexModel.js'
	import {
		postEmail
	} from '../../../models/indexModel.js'
	import {
		postUserImformation
	} from '../../../models/indexModel.js'
	var avatarImage = ''
	export default {
		components: {
			schoolPicker,
			editImage
		},
		data() {
			return {
				// informationRight: false,
				cityPickerValueDefault: [21, 0, 1], //学校默认值
				eCode: '',
				haveEmail: false,
				emailButtonContent: '发送验证码',
				countDown: '',
				interval: null,
				sentEmailButton:true,
				// 用户信息
				userInfo: {
					phone: '1878668438'
				},
				// 焦点状态
				focusStatus: {
					name: true,
					nickname: false,
					qq: false,
					school: false,
					grade: false,
					phoneNumber: false,
					volAccount: false,
					email: false,
					selfIntroduction: false,
					verificationCode: false
				},
				focusID: 'name',
				// 性别选择器
				sexPick: ['男', '女'],
				sexIndex: 0,
				warning: ''
			};
		},

		onLoad: function(res) {
			// 接收修改后的图片
			uni.$on('backBase64',(res)=>{
				this.userInfo.avatarImage = res
				console.log('test')
			})
			const eventChannel = this.getOpenerEventChannel()
			if (res.logined) {
				// eventChannel.emit('test', 'test触发')
				eventChannel.on('userInfo', (res) => {
					console.log('传过来的数据', res);
					if (res) {
					avatarImage	= res.avatarImage
						this.userInfo = res
						if (res.email) {
							this.haveEmail = true
							this.sentEmailButton = false
						}
					}

				})
			}

		},
		onUnload(){
			uni.$off('backBase64')
		},
		
		methods: {
			showPick() {
				this.$refs.schoolPicker.show()
			},
			onConfirm(e) {

				const school = e.label.split("-")[2];
				if (school === '暂未收录') {
					return;
				} else {
					this.$set(this.userInfo, 'school', school)
					
				}
			},
		
			// 修改性别
			sexChange(e) {
				this.sexIndex = e.target.value
				this.userInfo.sex = this.sexPick[this.sexIndex]
				console.log(this.userInfo.sex)
			},
			// 获得焦点修改样式
			getFocus(e) {
				var id = e.target.id
				this.focusStatus[this.focusID] = false
				this.focusStatus[id] = true
				this.focusID = id;
				// console.log(this.focusStatus)

			},
			// 上传保存信息
			postInfo() {
				let phoneReg = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/ //电话正则
				let nicenameReg = /^([\u4e00-\u9fa5]|\w){1,8}$/ //昵称规定为1到八位
				// let shoolReg = /^[\u4e00-\u9fa5]{2,4}$/
				let gradeReg = /^20\d{2}$/ //年级正则
				let qqReg = /^[1-9][0-9]\d{4,9}$/
				let emailReg = /^\w+@[a-zA-Z0-9]+((\.[a-z0-9A-Z]{1,})+)$/
				if (!nicenameReg.test(this.userInfo.nickName)) {
					this.warning = '请输入1-8位昵称！'
				} else if (!gradeReg.test(this.userInfo.grade)) {
					this.warning = '请输入正确的年级！,例如"2019"'
				} else if (!qqReg.test(this.userInfo.qq)) {
					this.warning = '请输入正确的QQ！'
				} else if (!phoneReg.test(this.userInfo.phone)) {
					this.warning = '电话格式错误！'
				} else {
					if (this.userInfo.sex === '男') {
						this.userInfo.sex = 1
					} else {
						this.userInfo.sex = 2
					}
					//从组件拿回图片
					
					postUserImformation(this.userInfo).then(res => {
						console.log("保存成功",res)
						uni.showToast({
							title: '保存成功',
							duration: 2000,
							icon: 'success',
							success(res) {
								uni.navigateBack({
									Number: 1
								})
								uni.$emit('updateUserInfo', '保存数据成功，更新数据')
							}
						})
					
					})
					
				}

			},
			timer() {
				this.countDown = 5
				this.interval = setInterval(() => {
					this.countDown--
				}, 1000)
			},
			sentEmail() {
				postEmail({
					'mail': this.email
				}).then(res => {
					console.log(res)
					if (res.code === 2000) {
						this.emailButtonContent = '发送成功 '
						this.timer()
					} else if (res)
						console.log(res)
				})
			},
			verificationCode() {
				postEmailCode({
					'code': this.eCode,
					'mail': this.email
				}).then(res => {
					if (res.code === 2000) {
						this.haveEmail = true
						this.emailButtonContent = '已验证'

						uni.showToast({
							title: '验证成功',
							duration: 2000,
							mask: true,
							icon: 'success'
						});
					} else {
						uni.showToast({
							title: '验证码错误',
							duration: 2000,
							mask: true,
							icon: 'error'
						});
						console.log("验证失败")
					}
					console.log(res)
				})
			}
		},
		watch: {
			countDown(value) {
				console.log(value)
				if (value <= 0) {
					clearInterval(this.interval)
					this.emailButtonContent = '重新发送'
					this.countDown = ''
				}
			}
		}
	}
</script>

<style lang="scss">
	.note {
		color: red;
		text-align: center;
		margin-top: 30rpx;
		font-size: 30rpx;
	}

	.page {
		background-color: #eee;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding-bottom: 300rpx;

		.title {
			margin-top: 20rpx;

			.image {
				vertical-align: bottom;
				width: 50rpx;
				height: 55rpx;
			}
		}

		.information {


			.selfIntroduction {
				.label {
					width: 40%;
				}

				.textarea {}
			}

			.emailConfirm {
				.label {
					width: 17%;
				}

				input {
					width: 59%;
				}

				.sent {
					font-size: 20rpx;
					background-color: orange;
					padding: 5rpx 20rpx;
				}

				.active {
					background-color: #eee;
				}
			}

			.label {
				width: 20%;
			}

			.input {
				display: inline-block;
				width: 80%;
				height: 80%;

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

			.volAccount {
				.label {
					width: 30%;
				}
			}

			.verificationCode {

				// width: 60%;
				.input {
					width: 50%;
				}

				.label {
					width: 23%;
				}

				.button {
					width: 150rpx;
					font-size: 20rpx;
					background-color: orange;
				}

			}

			

			.save {
				margin-top: 50rpx;
				background-color: #ffaa00;
			}

			.save_hover {
				background-color: #ffd56b;
			}
		}

		// 获得焦点样式改变
		.getFocus {
			// border: 1px solid orange;
			// transform: scale(1.04);
			// transition: all 0.1s;

		}
	}
</style>
