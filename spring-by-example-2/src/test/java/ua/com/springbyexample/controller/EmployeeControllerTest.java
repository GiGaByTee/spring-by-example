package ua.com.springbyexample.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author orezchykov
 * @since 18.04.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = "classpath:employee-controller-test.xml")
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testEmployeeList() throws Exception {
        mvc.perform(get("/employee/list")).andExpect(status().isOk())
                .andExpect(view().name("listEmployees"));

    }

    @Test
    public void testEmployeeCreate() throws Exception {
        mvc.perform(post("/employee/edit").param("firstName", "Some name"))
                .andExpect(status().isOk());

    }

    @Test
    public void testJsonList() throws Exception {
        mvc.perform(get("/android/2")).andExpect(status().isOk())
                                      .andExpect(content().contentType("application/json"))
                                      .andExpect(jsonPath("$.firstName").value(containsString("Eugene")));

    }
}
