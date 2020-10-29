package weixuanwork;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class)
public class UserTest {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testUserInfo(){
       User user = iUserService.findByUserList("15555555555");
        System.out.println(user.getProfile_path());
    }
    @Test
    public void testUpdatePersonal(){
        String personal ="的房间圣诞节分厘卡即使打开了解放昆仑山地方就看了大家来看世界放疗科大夫的考虑是否距离喀什地方螺丝钉解放深度了解分厘卡" +
                "圣诞节分厘卡圣诞节分类 流口水的离开的实力老师的课旅客发送量的事发生了地方大师傅大师傅似的顺丰到付士大夫撒旦发发生大范德萨sfd" +
                "电风扇发射点";
        int rows = iUserService.updateUserPersonal(personal,"15555555555");
        System.out.println(rows);
    }
    @Test
    public void testSelectPersonal(){
        String s = iUserService.selectUserPersonal("15555555555");
        System.out.println(s);
    }
}
