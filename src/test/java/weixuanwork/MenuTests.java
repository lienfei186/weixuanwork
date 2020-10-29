package weixuanwork;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.pojo.Menu;
import com.cn.weixuan.service.IMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class)
public class MenuTests {
    @Autowired
    private IMenuService iMenuService;
    @Test
    public  void testMenuSave(){
        Menu menu = new Menu();
        menu.setMenuName("BBB");
        menu.setParentId(0);
        menu.setPermission("sys:menu:del");
        int rows = iMenuService.saveMenu(menu);
        System.out.println(rows);
    }
}
