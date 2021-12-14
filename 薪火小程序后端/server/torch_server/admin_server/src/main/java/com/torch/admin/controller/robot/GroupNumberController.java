package com.torch.admin.controller.robot;

import com.torch.admin.entity.robot.GroupNumber;
import com.torch.admin.service.robot.GroupNumberService;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Api(tags = "各个群群人数接口")
@RestController
@RequestMapping("/admin/groupNumber")
public class GroupNumberController {

    @Resource
    private GroupNumberService service;

    @ApiOperation("获取六个志愿群近20条人数信息")
    @GetMapping("/getAllGroupNumber")
    public R<?> getAllGroupNumber() {
        String[] groupNumbers = {"720637016", "699502710", "1041136144", "933729831", "764629516", "687844069"};
        Map<String, List<Map<String, Object>>> results = new HashMap<>();
        for (String s : groupNumbers) {
            List<GroupNumber> numberList = service.getGroupNumberList(s, 20);
            List<Map<String, Object>> groupNumber = new ArrayList<>();
            for (GroupNumber number : numberList) {
                Map<String, Object> m = new HashMap<>();
                m.put("time", number.getTime());
                m.put("number", number.getNumber());
                groupNumber.add(m);
            }
            Collections.reverse(groupNumber);
            results.put(s, groupNumber);
        }
        return R.ok().data(results);
    }

}
