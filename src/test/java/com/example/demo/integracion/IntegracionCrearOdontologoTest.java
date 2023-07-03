package com.example.demo.integracion;

import com.example.demo.persistence.entities.Odontologo;
import com.example.demo.util.Jsons;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionCrearOdontologoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrarOdontologo() throws Exception {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Federico");
        odontologo.setApellido("Galan");
        odontologo.setMatricula("125asd");
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(odontologo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }


}
