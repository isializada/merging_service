package de.alizada.merging_service.merging_service.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;

@Repository
public class MergingRepository {
    private final RestOperations restOperations;

    public MergingRepository(final RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public String findUserById(final Integer id) {
        final String userRequestUrl = "http://jsonplaceholder.typicode.com/users/" + id;
        return restOperations.getForObject(userRequestUrl, String.class);
    }

    public String findCommentsById(final Integer id) {
        final String commentRequestUrl = "http://jsonplaceholder.typicode.com/posts?userId=" + id;
        return restOperations.getForObject(commentRequestUrl, String.class);
    }
}
