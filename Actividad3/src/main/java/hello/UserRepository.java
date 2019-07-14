package hello;


public interface UserRepository extends CrudRepository<User, Integer> {
public void save(User u);
public  Iterable<User> findAll();
public Object findById(Integer id);
public void deleteById(Integer id);
}
