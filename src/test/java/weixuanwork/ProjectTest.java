package weixuanwork;

import com.cn.weixuan.SpringWeixuanApplication;
import com.cn.weixuan.config.StringUtil;
import com.cn.weixuan.dao.ProjectEditorMapper;
import com.cn.weixuan.service.IProjectEditorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWeixuanApplication.class)
public class ProjectTest {
    @Autowired
    private ProjectEditorMapper projectEditorMapper;
    @Autowired
    private IProjectEditorService iProjectEditorService;
    @Test
    public void testFindProject(){
        String s = projectEditorMapper.findPicture(1);
        String s1 = StringUtil.trimFirstAndLastChar(s,'[');
        String s2 = StringUtil.trimFirstAndLastChar(s1,']');
        String s3 = s2.replace(" ","");
        System.out.println(s3);
      String[] a = s3.split(",");
        for (int  i = 0 ; i < a.length ; i++){
            File file = new File(a[i]);
            //判断文件是否存在
            if (file.exists() == true){
                System.out.println("图片存在，可执行删除操作");
                Boolean flag = false;
                flag = file.delete();
                if (flag){
                    System.out.println("成功删除图片"+file.getName());
                }else {
                    System.out.println("删除失败");
                }
            }else {
                System.out.println("图片不存在，终止操作");
            }
            System.out.println(a[i]);
        }

    }
    @Test
    public void testDeleteProject(){
        int rows = iProjectEditorService.deleteProject(3);
        System.out.println(rows);
    }
}
