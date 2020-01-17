package de.alizada.merging_service.merging_service.service;

import de.alizada.merging_service.merging_service.exception.BadRequestException;
import de.alizada.merging_service.merging_service.repository.MergingRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MergingService {
    private final MergingRepository mergingRepository;

    public MergingService(final MergingRepository mergingRepository) {
        this.mergingRepository = mergingRepository;
    }

    public String merge(final Integer id) {
        try{
            final CompletableFuture<String> userData = getUserData(id);
            final CompletableFuture<String> userComments = getUserComments(id);
            CompletableFuture.allOf(userData, userComments).join();

            return userData.get() + userComments.get();
        }catch (InterruptedException | ExecutionException ex){
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (HttpClientErrorException ex){
            throw new BadRequestException("Can not find any data by requested ID:" + id);
        }
    }

    @Async("threadPoolTaskExecutor")
    private CompletableFuture<String> getUserComments(final Integer id) {
        final String userCommentsJson = mergingRepository.findCommentsById(id);
        return CompletableFuture.completedFuture(userCommentsJson);
    }

    @Async("threadPoolTaskExecutor")
    private CompletableFuture<String> getUserData(final Integer id) {
        final String userDataJson = mergingRepository.findUserById(id);
        return CompletableFuture.completedFuture(userDataJson);
    }
}
