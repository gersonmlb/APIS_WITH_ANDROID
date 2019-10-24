package com.kaluzny;


import com.kaluzny.domain.Users;
import com.kaluzny.domain.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    private UsersRepository repository;

    @Autowired
    public void setRepository(UsersRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {

        repository.deleteAll();

        repository.save(new Users(1, "User1", "UsuarioComun1"));
        repository.save(new Users(2, "User2", "UsuarioComun2"));
    }
}