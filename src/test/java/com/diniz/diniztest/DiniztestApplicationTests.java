package com.diniz.diniztest;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.diniz.diniztest.domain.model.Student;
import com.diniz.diniztest.domain.repository.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiniztestApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@LocalServerPort
	private int port;

	@Test
	public void testingSaveStudentWithSucess() {
		// scenary
		Student newStudent = new Student();
		newStudent.setName("Guilherme");
		newStudent.setEmail("guilherme@gmail.com");

		// action
		newStudent = studentRepository.save(newStudent);

		// validation
		// assert = asserções
		assertThat(newStudent).isNotNull(); // Me acerte se o novo estudante não está nulo
		assertThat(newStudent.getId()).isNotNull(); // Me acerte que o novo estudante não tem o id nulo
	}

	// Happy path
	@Test
	public void mustBeReturnStatusCode200_When_GET_inStudents() {
		// Ativa o log de requisição e resposta quando a validação falhar.
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		// Configuração e execução do teste:

		given() // Dado que:
			.basePath("/students") // O caminho base para a API é "/students".
			.port(port) // A porta do servidor em que a API está rodando.
			.accept(ContentType.JSON) // O cliente aceita respostas no formato JSON.
		.when() // Quando:
			.get() // É feita uma requisição GET para o endpoint "/students".
		.then() // Então:
			.statusCode(HttpStatus.OK.value()); // Espera-se que o status da resposta seja 200 (OK).
	}

}
