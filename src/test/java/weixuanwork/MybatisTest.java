package weixuanwork;




import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.pojo.User;

import javax.annotation.Resource;

/**
 * @program: springboot_mybatis
 * @description:
 * @author: Mr.Xiao
 * @create: 2020-05-03 19:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class) // 指定引导类的class字节码
public class MybatisTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
//        List<User> users = userMapper.selectAllUser();
//        System.out.println(users);
    }
}
