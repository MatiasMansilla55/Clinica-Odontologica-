package com.backend.clinicaodontologica;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.repository.impl.OdontologoDaoH2;
import com.backend.clinicaodontologica.repository.impl.OdontologoMemoria;
import com.backend.clinicaodontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {
	private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplicationTests.class);
	private OdontologoService odontologService = new OdontologoService(new OdontologoDaoH2(),new ModelMapper());
	private static OdontologoService odontologServiceMemoria=new OdontologoService(new OdontologoMemoria(new ArrayList<>()),new ModelMapper());

	//private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

	@BeforeAll
	static void doBefore() {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/clinica-odontologica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


	@Test
	void deberiaRegistrarYRetonarUnOdontologo(){
		OdontologoEntradaDto odontologoEntradaDtoDto = new OdontologoEntradaDto(123,"matias","perez");


		OdontologoSalidaDto odontologoRegistrado= odontologService.registrarOdontologo(odontologoEntradaDtoDto);

		Assertions.assertTrue(odontologoRegistrado.getId() != 0);
	}

	@Test
	void deberiaRetornarUnaListaDeOdontologos(){

		Assertions.assertFalse(odontologService.listarOdontologo().isEmpty());
	}

	@Test
	void deberiaRegistrarYDevolverEnMemoriaUnOdontologo(){
		odontologServiceMemoria=new OdontologoService(new OdontologoMemoria(new ArrayList<>()),new ModelMapper());
		OdontologoEntradaDto odontologo1= new OdontologoEntradaDto(2,"jose","cevallos");
		OdontologoSalidaDto odontologAPersistir= odontologServiceMemoria.registrarOdontologo(odontologo1);

		Assertions.assertNotNull(odontologAPersistir.getId());
	}


	//DEBERIA DE RETORNARNOS UNA LA LISTA CON UN ODONTOLOGO PERO NO NOS RETORNA VACIA, NO SABEMOS EN DONDE NOS ESTAMOS EQUIVOCANDO.
	@Test
	void deberiaRetornarLaListaDeOdonotologos(){

		//Odontologo odontologoGuardado= new Odontologo(2,3,"sebastian","sanchez");
		//Odontologo odontologoGuardado2= new Odontologo(3,44,"Lucia","Marquez");

		//odontologServiceMemoria.registrarOdontolog(odontologoGuardado);
		//odontologServiceMemoria.registrarOdontolog(odontologoGuardado2);
		//LOGGER.info(odontologServiceMemoria.listaDeOdontologos());
		Assertions.assertFalse(odontologServiceMemoria.listarOdontologo().isEmpty());
	}

	//Test para pacientes:

	//@Test
	//void deberiaAgregarUnPaciente(){

		//Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new Domicilio("Calle", 13, "Localidad", "Provincia"));

		//Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

		//Assertions.assertTrue(pacienteRegistrado.getId() != 0);

	//}

	//@Test
	//void deberiaRetornarUnaListaNoVacia(){

		//assertFalse(pacienteService.listarPacientes().isEmpty());

	//}

}
