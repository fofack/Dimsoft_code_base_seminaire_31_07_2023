package com.proof.concept.controllers.unit_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.proof.concept.beans.Etudiant;
import com.proof.concept.controllers.EtudiantsController;
import com.proof.concept.exceptions.CannotCreateEntityException;
import com.proof.concept.model.EtudiantDto;
import com.proof.concept.services.EtudiantsService;
import com.proof.concept.utils.Utils;

@WebMvcTest(EtudiantsController.class)
public class EtudiantsControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    public EtudiantsService etudiantsService;

    @Test
    public void aStudentCanBeCreatedWhenTheDtoDataAreCorrectAndTheRightEndpointIsFired() throws Exception {
        EtudiantDto etudiantDto = EtudiantDto.fromEtudiant(new Etudiant("CM04-10SCI1755", "Zekeng", 1992));

        when(etudiantsService.createOrUpdateEtudiant(any(EtudiantDto.class))).thenReturn(new Etudiant(1, "CM04-10SCI1755", "Zekeng", 1992));

        this.mockMvc.perform(post("/etudiants/createEtudiant")
                                .content(Utils.asJsonString(etudiantDto))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.etudiant.etudiantId").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.etudiant.etudiantId").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.etudiant.etudiantRegNumber").value("CM04-10SCI1755"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.etudiant.etudiantBirthYear").value(1992));
    }

    @Test
    public void anCannotCreateEntityExceptionIsRaisedWhenTheStudentAlreadyExist() throws Exception {
        EtudiantDto etudiantDto = EtudiantDto.fromEtudiant(new Etudiant("CM04-10SCI1755", "Zekeng", 1992));

        when(etudiantsService.existsByRegNumber(etudiantDto.getEtudiant().getEtudiantRegNumber())).thenReturn(true);
        when(etudiantsService.createOrUpdateEtudiant(any(EtudiantDto.class))).thenReturn(new Etudiant(1, "CM04-10SCI1755", "Zekeng", 1992));

        this.mockMvc.perform(post("/etudiants/createEtudiant")
                                .content(Utils.asJsonString(etudiantDto))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError())
                    .andExpect(result -> assertTrue(result.getResolvedException() instanceof CannotCreateEntityException));
    }
}
