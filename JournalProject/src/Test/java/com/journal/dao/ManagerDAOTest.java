package com.journal.dao;

import com.journal.config.SpringConfig;
import com.journal.pojo.Manager;
import com.journal.pojo.basicClass.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class ManagerDAOTest {
    @Autowired
ManagerDAO dao;
    @Test
    public void findAll() {
        List<Manager> all = dao.findAll();
        for (Manager u :
                all) {
            System.out.println(u);
        }
    }

    @Test
    public void findByAccount() {
        Manager root = dao.findByAccount("root","root");
        System.out.println(root);
    }

    @Test
    public void updateByAccount() {
        int i = dao.updateByAccount("root", "root");
        System.out.println(i);
    }
}