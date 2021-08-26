package mx.uady.musicon.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.musicon.filter.JwtTokenUtil;
import mx.uady.musicon.model.Artist;
import mx.uady.musicon.model.Playlist;
import mx.uady.musicon.model.Podcast;
import mx.uady.musicon.model.Song;
import mx.uady.musicon.model.User;
import mx.uady.musicon.model.request.JwtRequest;
import mx.uady.musicon.model.request.UserRequest;
import mx.uady.musicon.model.response.JwtResponse;
import mx.uady.musicon.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRest {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/self") // /self
    public ResponseEntity<User> getLoggedUser() {
        mx.uady.musicon.model.User user = (mx.uady.musicon.model.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

	@PostMapping("/authenticate") 
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

    @PostMapping("/register") 
	public ResponseEntity<?> saveUser(@RequestBody UserRequest user) throws Exception {
		return ResponseEntity.ok(userService.createUser(user));
	}

    @PostMapping("/login") 
    public ResponseEntity<JwtResponse> postLogin(@Valid @RequestBody JwtRequest request, @RequestHeader("User-Agent") String userAgent) throws Exception{

        return ResponseEntity.ok().body(userService.login(request, userAgent));
    }

    @PostMapping("/logout/{id}") 
    public ResponseEntity<Void> postLogoutUser(@PathVariable  Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.logOut(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/users/find") 
    public ResponseEntity<User> searchUser(@RequestParam("mail") String mail)  {
        return ResponseEntity.ok().body(userService.searchUserByMail(mail));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/{id}/playlists")
    public ResponseEntity<List<Playlist>> getPlaylistsByUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getPlaylistsByUser(id));
    }

    @GetMapping("/users/{id}/artistfollow")
    public ResponseEntity<List<Artist>> getFollowArtitsByUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getFollowArtitsByUser(id));
    }

    @GetMapping("/users/{id}/songshistorial")
    public ResponseEntity<List<Song>> getHistorialSongByUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getHistorialSongByUser(id));
    }

    @GetMapping("/users/{id}/podcastshistorial")
    public ResponseEntity<List<Podcast>> getHistorialPodcastsByUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getHistorialPodcastsByUser(id));
    }

    @PostMapping("/users") 
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) throws URISyntaxException, InterruptedException{
        User userNew = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
    }

    @PutMapping("/users/{id}") 
    public ResponseEntity<User> editUser(@Valid @PathVariable Integer id,@Valid @RequestBody UserRequest request) throws InterruptedException {
        return ResponseEntity.ok().body(userService.editUser(id, request));
    }

    @DeleteMapping("/users/{id}") 
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteUser(id));
    }

    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
    
}
