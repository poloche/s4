package org.plc.interview.s4.controllers;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.plc.interview.s4.configuration.HelperPage;
import org.plc.interview.s4.controllers.dto.StudentDTO;
import org.plc.interview.s4.domain.Student;
import org.plc.interview.s4.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    int randomServerPort;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @MockBean
    Pageable pageable;

    private static String STUDENT_URL;

    @Before
    public void init() {
        STUDENT_URL = "http://localhost:" + randomServerPort + "/restapi/students";
    }

    @Test
    public void listAllStudents() {
        PageImpl<Student> page = new PageImpl<>(new ArrayList<>(
                Arrays.asList(
                        new Student(1, "Paolo", "Lizarazu"),
                        new Student(2, "Simon", "Morales")
                )
        ));
        BDDMockito.given(studentRepository.findAll(ArgumentMatchers.isA(Pageable.class))).willReturn(page);

        ParameterizedTypeReference<HelperPage<StudentDTO>> responseType = new ParameterizedTypeReference<HelperPage<StudentDTO>>() {
        };
        ResponseEntity<HelperPage<StudentDTO>> response = restTemplate.exchange(STUDENT_URL, HttpMethod.GET, null/*httpEntity*/, responseType);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getContent()).isNotNull();
        Assertions.assertThat(response.getBody().getContent().size()).isEqualTo(2);
        Assertions.assertThat(response.getBody().getContent().get(0)).isNotNull();
        StudentDTO responseDTO = response.getBody().getContent().get(0);
        Assertions.assertThat(responseDTO.getId()).isEqualTo(1);
    }

    @Test
    public void findById() {
        Student simon = new Student(2, "Simon", "Morales");
        BDDMockito.given(studentRepository.findById(ArgumentMatchers.isA(Integer.class))).willReturn(Optional.of(simon));


        ResponseEntity<StudentDTO> response = restTemplate.exchange(STUDENT_URL + "/2", HttpMethod.GET, null/*httpEntity*/, StudentDTO.class);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(2);
    }

    @Test
    public void findByIdUnExistentId() {

        BDDMockito
                .given(studentRepository.findById(ArgumentMatchers.isA(Integer.class)))
                .willReturn(Optional.empty());

        try {
            restTemplate.getForEntity(STUDENT_URL + "/2", String.class);
            Assert.fail();
        }catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
