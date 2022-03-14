package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.ContactUs;
import com.torch.app.entity.vo.ContactUsCon.UpdateMessage;
import com.torch.app.service.ContactUsService;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import com.torch.app.util.commonutils.R;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(tags = {"联系我们相关接口"},value = "联系我们")
@RestController
@RequestMapping("/contactUs")
public class ContactUsController {

    private ContactUsService contactUsService;

    private JudgeCookieToken judgeCookieToken;

    private RedisUtil redisUtil;

    private RedissonClient redissonClient;

    @Autowired
    public ContactUsController(ContactUsService contactUsService,
                               JudgeCookieToken judgeCookieToken,
                               RedisUtil redisUtil,
                               RedissonClient redissonClient) {
        this.contactUsService = contactUsService;
        this.judgeCookieToken = judgeCookieToken;
        this.redisUtil = redisUtil;
        this.redissonClient = redissonClient;
    }

    @LogCostTime
    @ApiOperation(value = "用户发送的信息")
    @PostMapping()
    public R<?> SendMessage(@ApiParam(name = "content",value = "信息内容",required = true)@RequestBody String content,
                            HttpServletRequest request){
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        ContactUs contactUs = new ContactUs();
        contactUs.setContent(content);
        contactUs.setUserId((Integer)uid);
        contactUs.setCreateTime(new Date().getTime());
        contactUs.setUpdateTime(new Date().getTime());
        int res = contactUsService.getBaseMapper().insert(contactUs);
        if (res==1){
//            后续缓存添加位置，暂时还不需要
            log.info("用户的信息发送成功");
            return R.ok().message("发送成功");
        }else {
            log.error("用户的信息发送失败");
            return R.error().message("发送失败");
        }
    }

    @LogCostTime
    @ApiOperation(value = "用户删除已发送信息")
    @DeleteMapping("/{id}")
    public R<?> deleteMessage(@ApiParam(name = "id",value = "此消息的id",required = true)@PathVariable Integer id){
        //            这里可以模仿消息撤回，超过2分钟，不允许撤回
        int res = contactUsService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok().message("消息撤回成功");
        }else {
            return R.error().message("消息撤回失败");
        }
    }
//    若设置撤回，则不需要修改接口，可以先写着，后面再更改
    @LogCostTime
    @ApiOperation(value = "修改信息")
    @PutMapping()
    public R<?> updateMessage(@ApiParam(name = "id",value = "消息编号",required = true)@RequestBody UpdateMessage updateMes){
        ContactUs contactUs = contactUsService.getBaseMapper().selectById(updateMes.getId());
        contactUs.setContent(updateMes.getContent());
        contactUs.setUpdateTime(new Date().getTime());
        int res = contactUsService.getBaseMapper().insert(contactUs);
        if (res==1){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }

    @LogCostTime
    @ApiOperation(value = "获取所有的信息")
    @GetMapping("/{current}/{limit}")
    public R<?> getAllMessages(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                               @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                               HttpServletRequest request){
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        Page<ContactUs> page = new Page<>(current,limit);
        QueryWrapper<ContactUs> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.orderByDesc("update_time");
        contactUsService.page(page,wrapper);
        List<ContactUs> records = page.getRecords();
        return R.ok().data(records);
    }

    @LogCostTime
    @ApiOperation(value = "获取一条信息的详细")
    @GetMapping("/{id}")
    public R<?> getOneMessage(@ApiParam(name = "id",value = "信息的编号",required = true)@PathVariable Integer id){
        ContactUs contactUs = contactUsService.getBaseMapper().selectById(id);
        return R.ok().data(contactUs);
    }
}
