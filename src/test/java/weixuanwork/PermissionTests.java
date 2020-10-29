package weixuanwork;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.pojo.Permission;
import com.cn.weixuan.service.IPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class)
public class PermissionTests {
    @Autowired
    private IPermissionService iPermissionService;
    @Test
    public void testSavePermission(){
        Permission entity = new Permission();
        entity.setParentId(0L).setPermission("user:update").setPermissionName("用户修改").setResourceType("button").setUrl("user/userupdate").setParentIds("0/1");
        List<Integer> menuIds = new ArrayList<>();
        menuIds.add(1);
        menuIds.add(2);
        int rows = iPermissionService.savePermission(entity,menuIds);
        System.out.println(rows);
    }
}
