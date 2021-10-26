package com.torch.admin.controller.torch;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.admin.entity.torch.TorchView;
import com.torch.admin.service.torch.TorchViewService;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@Api(tags = {"路由添加相关接口"},value ="路由添加相关接口")
@RestController
@RequestMapping("/torchView")
public class TorchViewController {
    @Resource
    private TorchViewService viewService;

    @ApiOperation("添加新路由接口")
    @PostMapping()
    private R<?> addOneView(@ApiParam(name = "torchView",value = "路由对象",required = true)TorchView torchView){
        List<TorchView> torchViews = viewService.getBaseMapper().selectList(null);
//        匹配是否有重复id
        boolean flag = true;
        for (TorchView view : torchViews) {
            flag = !view.getId().equals(torchView.getId());
        }
        if (flag){
            int res = viewService.getBaseMapper().insert(torchView);
            if (res==1){
                return R.ok();
            }else {
                return R.error().message("插入路由信息失败");
            }
        }else {
            return R.error().message("id重复，请重新输入理由id");
        }
    }
    @ApiOperation("删除路由接口")
    @DeleteMapping()
    public R<?> deleteView(@ApiParam(name = "id",value = "路由的id标识",required = true) String id){
        int res = viewService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("删除路由失败");
        }
    }
    @ApiOperation("更改路由信息")
    @PutMapping()
    public R<?> updateView(@ApiParam(name = "",value = "",required = true)TorchView torchView){
        List<TorchView> torchViews = viewService.getBaseMapper().selectList(null);
//        匹配是否有重复id
        boolean flag = true;
        for (TorchView view : torchViews) {
            flag = !view.getId().equals(torchView.getId());
        }
        if (flag){
            int res = viewService.getBaseMapper().updateById(torchView);
            if (res==1){
                return R.ok();
            }else {
                return R.error().message("更新信息失败");
            }
        }else {
            return R.error().message("id重复，请重新输入路由id");
        }
    }
    @ApiOperation("查询所有的路由信息")
    @GetMapping("/{current}/{limit}")
    public R<?> selectAll(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
                          @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit){
        Page<TorchView> page = new Page<>(current,limit);
        Page<TorchView> torchViewPage = viewService.getBaseMapper().selectPage(page, null);
        return R.ok().data(torchViewPage);
    }
}
