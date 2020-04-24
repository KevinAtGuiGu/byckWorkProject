package com.kingtrustcloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @version V1.0
 * @Title: FamilytreeAppTest
 * @Package com.kingtrustcloud
 * @Description: ()
 * @author: tanyong
 * @date: 2020/3/10 13:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {familytreeApp.class})
public class FamilytreeAppTest {

    @Test
    public void test() {
        Assert.assertEquals("1","1");
    }

}
