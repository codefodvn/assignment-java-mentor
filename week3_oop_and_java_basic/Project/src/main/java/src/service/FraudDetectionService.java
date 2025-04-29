package src.service;

import src.model.User;

public interface FraudDetectionService {
    boolean checkUser(User user);
}
