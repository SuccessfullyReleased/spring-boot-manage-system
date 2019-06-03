package com.demo.cms;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.demo.cms.authorization.config.AuthorizationConstants;
import com.demo.cms.dao.NoteDao;
import com.demo.cms.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    //    @Autowired
//    FloorDao dao;
    @Autowired
    NoteDao dao;

    @Test
    public void contextLoads() {
//        String file = "<a href=\"#\">你好</a>";
//        Floor floor1 = new Floor(null, file.getBytes(StandardCharsets.UTF_8), "test", new Date(), 1, null, null);
//        dao.insertSelective(floor1);
//        Floor floor2 = dao.selectByPrimaryKey(floor1.getFid());
//        System.out.println(new String(floor2.getContent(), StandardCharsets.UTF_8));
        Example example = new Example(Note.class);
        example.setForUpdate(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            example.createCriteria().andBetween("time", sdf.parse("2019-05-01 00:00:00"), sdf.parse("2019-06-01 00:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        example.orderBy("time").desc();
        List<Note> notes = dao.selectByExample(example);
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    @Test
    public void httpRequest() {
        HttpResponse res = HttpRequest.get("http://localhost:9500/rbac/user/token")
                .header("authorization", "eyJ1c2VybmFtZSI6ImFkbWluIiwidG9rZW4iOiJmZmY1ZmQyZTI3ZjY0YTc3YmZlODkxZGNlYWY1ZDU1YiJ9")
                .execute();
        System.out.println(res.header(AuthorizationConstants.CURRENT_USER));
    }

}
