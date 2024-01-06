package com.example.MoviesSystem;

import com.example.MoviesSystem.common.GlobalConstants;
import com.example.MoviesSystem.data.models.ApplicationRole;
import com.example.MoviesSystem.data.models.ApplicationUser;
import com.example.MoviesSystem.data.models.Genre;
import com.example.MoviesSystem.data.models.Movie;
import com.example.MoviesSystem.data.repositories.GenreRepository;
import com.example.MoviesSystem.data.repositories.MovieRepository;
import com.example.MoviesSystem.data.repositories.RoleRepository;
import com.example.MoviesSystem.data.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MoviesSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesSystemApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(
			RoleRepository roleRepository,
			UserRepository userRepository,
			MovieRepository movieRepository,
			GenreRepository genreRepository,
			PasswordEncoder passwordEncoder){
		return args -> {

			var adminRole = new ApplicationRole(GlobalConstants.ADMIN_ROLE);
			if(roleRepository.count() == 0){

				roleRepository.save(adminRole);
				roleRepository.save(new ApplicationRole(GlobalConstants.USER_ROLE));
			}

			if(userRepository.count() == 0){
				userRepository.save(new ApplicationUser(GlobalConstants.ADMIN_USERNAME, passwordEncoder.encode(GlobalConstants.ADMIN_PASSWORD), adminRole));
			}

			var fantacyGenre = new Genre("Fantasy");
			var comedyGenre = new Genre("Comedy");

			if(genreRepository.count() == 0){
				genreRepository.save(fantacyGenre);
				genreRepository.save(comedyGenre);
				genreRepository.save(new Genre("Action"));
				genreRepository.save(new Genre("Drama"));
				genreRepository.save(new Genre("Horror"));
				genreRepository.save(new Genre("Mystery"));
				genreRepository.save(new Genre("Romance"));
			}

			if(movieRepository.count() == 0){
				movieRepository.save(new Movie(
						"Harry Potter and the Sorcerer's Stone",
						"An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
						"https://m.media-amazon.com/images/M/MV5BNmQ0ODBhMjUtNDRhOC00MGQzLTk5MTAtZDliODg5NmU5MjZhXkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_.jpg",
						fantacyGenre));

				movieRepository.save(new Movie(
						"Harry Potter and the Chamber of Secrets",
						"An ancient prophecy seems to be coming true when a mysterious presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.",
						"https://m.media-amazon.com/images/M/MV5BMjE0YjUzNDUtMjc5OS00MTU3LTgxMmUtODhkOThkMzdjNWI4XkEyXkFqcGdeQXVyMTA3MzQ4MTc0._V1_.jpg",
						fantacyGenre));

				movieRepository.save(new Movie(
						"Home Alone 1",
						"An eight-year-old troublemaker, mistakenly left home alone, must defend his home against a pair of burglars on Christmas Eve.",
						"https://m.media-amazon.com/images/M/MV5BMzFkM2YwOTQtYzk2Mi00N2VlLWE3NTItN2YwNDg1YmY0ZDNmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg",
						comedyGenre));

				movieRepository.save(new Movie(
						"Harry Potter and the Prisoner of Azkaban",
						"Harry Potter, Ron and Hermione return to Hogwarts School of Witchcraft and Wizardry for their third year of study, where they delve into the mystery surrounding an escaped prisoner who poses a dangerous threat to the young wizard.",
						"https://m.media-amazon.com/images/M/MV5BMTY4NTIwODg0N15BMl5BanBnXkFtZTcwOTc0MjEzMw@@._V1_FMjpg_UX1000_.jpg",
						fantacyGenre));

				movieRepository.save(new Movie(
						"Home Alone 2: Lost in New York",
						"One year after Kevin McCallister was left home alone and had to defeat a pair of bumbling burglars, he accidentally finds himself stranded in New York City - and the same criminals are not far behind.",
						"https://m.media-amazon.com/images/M/MV5BYzAyN2NhOTMtNjU0NC00MjQyLTk5NGEtNTM3YzQzZDI0YTI1XkEyXkFqcGdeQXVyMTYzMDM0NTU@._V1_.jpg",
						comedyGenre));

				movieRepository.save(new Movie(
						"Harry Potter and the Goblet of Fire",
						"Harry Potter finds himself competing in a hazardous tournament between rival schools of magic, but he is distracted by recurring nightmares.",
						"https://m.media-amazon.com/images/M/MV5BMTI1NDMyMjExOF5BMl5BanBnXkFtZTcwOTc4MjQzMQ@@._V1_FMjpg_UX1000_.jpg",
						fantacyGenre));
			}
		};
    }
}
