package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.User;
import hello.UserRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired 
	private UserRepository userRepository;
        
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String username,@RequestParam String name,@RequestParam String lastname
                
		, @RequestParam String email,@RequestParam String telephone) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = new User();
                n.setUsername(username);
		n.setName(name);
                n.setLastname(lastname);
		n.setEmail(email);
                n.setTelephone(telephone);
                
                
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
        @GetMapping(path="/get")
        public @ResponseBody User getUser(@RequestParam Integer id){
           User u=new User();
           u=userRepository.findById(id).get();
           return u;
        }
        @GetMapping(path="/update")
    public @ResponseBody String updateUser(@RequestParam Integer id,@RequestParam String username,@RequestParam String name,@RequestParam String lastname
                
		, @RequestParam String email,@RequestParam String telephone){
       User u=new User();
       u=userRepository.findById(id).get();
       u.setUsername(username);
		u.setName(name);
                u.setLastname(lastname);
		u.setEmail(email);
                u.setTelephone(telephone);
       userRepository.save(u);
       return "Actualizado";
    }
    @GetMapping(path="/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id){
        userRepository.deleteById(id);
        return "Usuario Eliminado";
        
    }
        
}
