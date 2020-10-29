package weixuanwork;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.dao.GoodATMapper;
import com.cn.weixuan.pojo.GoodAT;
import com.cn.weixuan.service.IGoodATService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class)
public class GoodATTest {
    @Autowired
    private GoodATMapper goodATMapper;
    @Autowired
    private IGoodATService iGoodATService;
    @Test
    public void testSaveGoodAT(){
       GoodAT entity = new GoodAT();
       entity.setGoodatName("java,c++,php,c,pty");

       iGoodATService.insertGoodAT(entity,"17603915791");

    }
    @Test
    public void testSelectUserSkills(){
       List<GoodAT> gat = goodATMapper.selectUserSkills(12);
        for (GoodAT g:gat) {
            System.out.println(g);
        }

    }
    @Test
    public void  testEqauls(){
       int a = goodATMapper.countUserId(1);
        System.out.println(a);
    }
    @Test
    public void  testUpdate(){
        GoodAT entity = new GoodAT();
        entity.setGoodatName("java,pty");
        entity.setUserId(2);
        entity.setGoodatId(3);
        int a = goodATMapper.updateUserSkills(entity);
        System.out.println(a);
    }

}
