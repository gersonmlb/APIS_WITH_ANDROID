package com.kazale.crud.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kazale.crud.api.documents.Users;
import com.kazale.crud.api.responses.Response;
import com.kazale.crud.api.services.UsersService;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {
	
	@Autowired
	private UsersService clienteService;
	
	@GetMapping
	public List<Users> listarTodos() {
		return this.clienteService.listarTodos();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Users>> listarPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(new Response<Users>(this.clienteService.listarPorId(id)));
	}
	
	@PostMapping
	public ResponseEntity<Response<Users>> cadastrar(@Valid @RequestBody Users users, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Users>(erros));
		}
		
		return ResponseEntity.ok(new Response<Users>(this.clienteService.cadastrar(users)));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Users>> atualizar(@PathVariable(name = "id") String id, @Valid @RequestBody Users users, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Users>(erros));
		}
		
		users.setId(id);
		return ResponseEntity.ok(new Response<Users>(this.clienteService.atualizar(users)));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name = "id") String id) {
		this.clienteService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

}
