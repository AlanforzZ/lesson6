package com.biz.lesson.dao.user;

import com.biz.lesson.model.user.MainMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class MainMenuRepositoryTest {
    @Autowired
    MainMenuRepository mainMenuRepository;
    @Test
    public void findByOrderByCodeAscNameAsc() {
        List<MainMenu> list = mainMenuRepository.findByOrderByCodeAscNameAsc();
        System.out.println("list是----------》"+list);
    }
}