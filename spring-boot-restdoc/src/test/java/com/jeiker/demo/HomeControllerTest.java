package com.jeiker.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @Author : xiao
 * @Date : 17/8/3 上午9:01
 */
// 提供Spring测试上下文
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        // 建立web请求
        this.mockMvc.perform(get("/"))
                // 打印结果
                .andDo(print())
                // 检查状态码为200
                .andExpect(status().isOk())
                // 检查内容有"Hello World"
                .andExpect(content().string(containsString("Hello World")))
                // 检查在根目录下有一个名为name的节点，并且该节点对应的值是“Hello World”
                .andExpect(jsonPath("$.message").value("Hello World"))
                .andDo(document("home"));
    }
}