package com.example.inicial1;

import com.example.inicial1.entities.*;
import com.example.inicial1.repositories.AutorRepository;
import com.example.inicial1.repositories.LocalidadRepository;
import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Inicial1Application {
	private static final Logger logger = LoggerFactory.getLogger(Inicial1Application.class);

	@Autowired
	private PersonaRepository personaRepository;
	public static void main(String[] args) {
		SpringApplication.run(Inicial1Application.class, args);

		System.out.println("funcionando");
	}

	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository, LocalidadRepository localidadRepository, AutorRepository autorRepository) {
		return args -> {
	// Creo un objeto persona
Persona per1 = Persona.builder().
		nombre("Camila").apellido("Fabian").dni(45143731).
		build();
Domicilio dom1 = Domicilio.builder().
		calle("Olascoaga").
		numero(455).build();

		per1.setDomicilio(dom1);

Persona per2 = Persona.builder().
		nombre("Sofia").apellido("Martinez").dni(54731143).
		build();
Domicilio dom2 = Domicilio.builder().
		calle("Agustin Alvarez").
		numero(564).build();

		per2.setDomicilio(dom2);

Localidad loc1 = Localidad.builder().denominacion("Godoy Cruz").build();
Localidad loc2 =Localidad.builder().denominacion("Capital").build();

		loc1= localidadRepository.save(loc1);
		loc2= localidadRepository.save(loc2);
		dom1.setLocalidad(loc1);
		dom2.setLocalidad(loc2);

Autor aut1 = Autor.builder()
		.nombre("Isabel")
		.apellido("Allende ")
		.biografia("Autora chilena, famosa por su novela La casa de los espíritus.")
		.build();
Autor aut2 =Autor.builder()
		.nombre("Mario")
		.apellido("Vargas LLosa")
		.biografia("Escritor peruano, reconocido por obras como La ciudad y los perros.")
		.build();

		aut1=autorRepository.save(aut1);
		aut2=autorRepository.save(aut2);

Libro lib1 = Libro.builder()
		.titulo("La casa de los espíritus")
		.fecha(1982)
		.genero("Realismo mágico")
		.paginas(433)
		.build();
Libro lib2 = Libro.builder()
		.titulo("La ciudad y los perros")
		.fecha(1963)
		.genero("Ficción")
		.paginas(304)
		.build();

		lib1.getAutores().add(aut1);
		lib2.getAutores().add(aut2);

		per1.getLibros().add(lib1);
		per1.getLibros().add(lib2);

		personaRepository.save(per1);
		personaRepository.save(per2);

		};

		};

}
