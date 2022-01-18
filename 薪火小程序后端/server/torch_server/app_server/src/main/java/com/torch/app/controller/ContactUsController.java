package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.ContactUs;
import com.torch.app.entity.vo.ContactUsCon.UpdateMessage;
import com.torch.app.service.ContactUsService;
import com.torch.app.util.tools.FileUtil;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Api(tags = {"联系我们相关接口"},value = "联系我们")
@RestController
@RequestMapping("/contactUs")
public class ContactUsController {
    @Resource
    private ContactUsService contactUsService;
    @Resource
    private JudgeCookieToken judgeCookieToken;
    @Resource
    private FileUtil fileUtil;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户发送的信息")
    @PostMapping()
    public R<?> SendMessage(@ApiParam(name = "content",value = "信息内容",required = true)@RequestBody String content,
                            HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        ContactUs contactUs = new ContactUs();
        contactUs.setContent(content);
        contactUs.setUserId((Integer)uid);
        contactUs.setCreateTime(new Date().getTime());
        contactUs.setUpdateTime(new Date().getTime());
        int res = contactUsService.getBaseMapper().insert(contactUs);
        if (res==1){
            return R.ok().message("发送成功");
        }else {
            return R.error().message("发送失败");
        }
    }

    @ApiOperation(value = "用户删除已发送信息")
    @DeleteMapping("/{id}")
    public R<?> deleteMessage(@ApiParam(name = "id",value = "此消息的id",required = true)@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
        //            这里可以模仿消息撤回，超过2分钟，不允许撤回
        int res = contactUsService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok().message("消息撤回成功");
        }else {
            return R.error().message("消息撤回失败");
        }
    }
//    若设置撤回，则不需要修改接口，可以先写着，后面再更改
    @ApiOperation(value = "修改信息")
    @PutMapping()
    public R<?> updateMessage(@ApiParam(name = "id",value = "消息编号",required = true)@RequestBody UpdateMessage updateMes,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
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

    @ApiOperation(value = "获取所有的信息")
    @GetMapping("/{current}/{limit}")
    public R<?> getAllMessages(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                               @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
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

    @ApiOperation(value = "获取一条信息的详细")
    @GetMapping("/{id}")
    public R<?> getOneMessage(@ApiParam(name = "id",value = "信息的编号",required = true)@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
        ContactUs contactUs = contactUsService.getBaseMapper().selectById(id);
        return R.ok().data(contactUs);
    }
}
