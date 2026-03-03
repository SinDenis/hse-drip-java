package withOptional.service;


import withOptional.exception.UserNotFoundException;
import withOptional.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserService {

    private final Map<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Optional<User> findUserByIdOptional(int userId) {
        return Optional.ofNullable(users.get(userId));
    }


    public User findUserByIdNullable(int userId) {
        return users.get(userId);
    }

    public Optional<String> getUserEmail(int userId) {
        return findUserByIdOptional(userId).map(User::getEmail);
    }

    public Optional<String> getUserName(int userId) {
        return findUserByIdOptional(userId).map(User::getName);
    }

    /**
     * Получение email или выброс исключения, если пользователь не найден
     */
    public String findUserEmailOrThrow(int userId) {
        return getUserEmail(userId)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " has no email"));
    }

    /**
     * Получение имени пользователя или значение по умолчанию
     */
    public String findUserNameOrDefault(int userId, String defaultName) {
        return getUserName(userId)
                .orElse(defaultName);
    }


    /**
     * Можно обрабатывать через проверку на null
     */
    public void printUserInfo(int userId) {
        User user = findUserByIdNullable(userId);
        if (user != null) {
            System.out.println("User found: " + user.getName());
        }
    }

    /**
     * Можно обрабатывать через в функциональном стиле
     */
    public void printOptionalUserInfo(int userId) {
        Optional<User> maybeUser = findUserByIdOptional(userId);
        maybeUser.ifPresent(user -> System.out.println("User found: " + user.getName()));
    }

    public String getUserNullableName(int userId) {
        User user = findUserByIdNullable(userId);
        return user != null ? user.getName() : "Unknown";
    }

    public String getUserOptionalName(int userId) {
        return findUserByIdOptional(userId)
                .map(User::getName)
                .orElse("Unknown");
    }

    public User getUserOrException(int userId) throws UserNotFoundException {
        User user = findUserByIdNullable(userId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    public User getOptionalUserOrException(int userId) throws UserNotFoundException {
        return findUserByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
