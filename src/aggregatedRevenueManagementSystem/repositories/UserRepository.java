package aggregatedRevenueManagementSystem.repositories;

import aggregatedRevenueManagementSystem.models.User;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class UserRepository {

    Map<Long, User> userMap;
    private static long count = 0;

    public UserRepository(){
        this.userMap = new HashMap<>();
    }

    public Optional<User> findById(long id){
        return Optional.ofNullable(this.userMap.get(id));
    }

    public User save(User user) {
        if(user.getId() == 0){
            user.setId(++count);
        }
        this.userMap.put(user.getId(), user);
        return user;
    }
}
