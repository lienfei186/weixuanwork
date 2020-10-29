package weixuanwork;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.dao.UserMapper;
import com.cn.weixuan.dao.WorkExperienceMapper;
import com.cn.weixuan.pojo.User;
import com.cn.weixuan.pojo.WorkExperience;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class)
public class WorkTest {
    @Autowired
    private WorkExperienceMapper workExperienceMapper;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testWork() throws ParseException {
        WorkExperience entity = new WorkExperience();
        entity.setCompanyName("那啥");
        entity.setJobtitle("java开发");
        entity.setJubduties("负责日志的持久化，客户端的开发");

        SimpleDateFormat a=new SimpleDateFormat("yyyy.MM");
        Date b=a.parse("2020.09");

        entity.setStartTime(b);
        entity.setEndTime(b);
        User u = userMapper.selectUserId("15555555555");
        entity.setUserId(u.getUserId());
        workExperienceMapper.insertUserWork(entity);
    }
    @Test
    public void testSelectWork(){
        User u = userMapper.selectUserId("15555555555");
       List<WorkExperience> list = workExperienceMapper.selectWork(u.getUserId());
        for (WorkExperience w:list) {
            System.out.println(w);
        }
    }
    @Test
    public void testUpdateWork() throws ParseException {
        WorkExperience entity = new WorkExperience();
        entity.setWorkId(11);
        entity.setJubduties("附件是的房间里看到路上风景老师的课");
        entity.setJobtitle("前端工程师");
        entity.setCompanyName("上市公司");
        SimpleDateFormat a=new SimpleDateFormat("yyyy.MM");
        Date b=a.parse("2020.06");
        entity.setStartTime(b);
        entity.setEndTime(b);
        workExperienceMapper.updateWork(entity);
    }
}
