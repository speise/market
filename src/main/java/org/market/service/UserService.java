package org.market.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.market.domain.User;
import org.market.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * GET /users -> get all Users.
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAll() {
		log.debug("REST request to get all Users");
		return (List<User>) userRepository.findAll();
	}

	/**
	 * GET /users/:id -> get the "id" user.
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@PathVariable Long id) {
		log.debug("REST request to get User : {}", id);
		return Optional
				.ofNullable(userRepository.findOne(id))
				.map(attraction -> new ResponseEntity<>(attraction,
						HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * POST /users -> create new User.
	 * 
	 * @throws URISyntaxException
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody User user)
			throws URISyntaxException {
		log.debug("REST request to create User : ", user);
		if (user.getId() != null) {
			return ResponseEntity.badRequest()
					.header("Failure", "A new user cannot already have an ID")
					.build();
		}
		userRepository.save(user);
		return ResponseEntity.created(new URI("/users/" + user.getId()))
				.build();
	}

	/**
	 * DELETE /users/:id -> delete the :id User.
	 */
	@RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete User : {}", id);
		userRepository.delete(id);
	}
}
