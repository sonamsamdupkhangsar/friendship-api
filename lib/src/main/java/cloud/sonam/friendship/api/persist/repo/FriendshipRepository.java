package cloud.sonam.friendship.api.persist.repo;



import cloud.sonam.friendship.api.persist.entity.Friendship;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FriendshipRepository extends ReactiveCrudRepository<Friendship, UUID> {
    Mono<Boolean>   existsByUserIdAndFriendId(UUID userID, UUID friendId);
    Flux<Friendship> findByUserIdAndFriendId(UUID userID, UUID friendId);
    //Flux<Friendship> findByRequestAcceptedIsFalseAndUserIdAndFriendId(UUID userId, UUID friendId);
    Mono<Integer> deleteByRequestAcceptedIsFalseAndUserIdAndFriendId(UUID userId, UUID friendId);

    @Query("select * from Friendship where (user_id=:userId or friend_id =:userId) and request_accepted=true and" +
            " response_sent_date is not null order by response_sent_date desc")
    Flux<Friendship> findAcceptedFriendsForUser(@Param("userId")UUID userId);

    /**
     * retrieve friendship rows for user where it's the user that requested or was requested by another user
     * and it has not been rejected
     * @param userId
     * @return flux of friendships
     * @return
     */
    @Query("select fs from Friendship fs where (fs.userId=:userId or fs.friendId =:userId) and fs.requestAccepted==true and responseSentDate!=null  " +
            "  order by fs.requestSentDate desc")
    Flux<Friendship> findValidFriendshipForUser(@Param("userId")UUID userId);
    //Mono<Page<Friendship>> findByUserIdAndFriendIdAndRequestAcceptedIsTrueAndResponseSentDateNotNull(UUID userId, UUID friendId, Pageable pageable);
    //Flux<Friendship> findByUserIdAndFriendIdAndRequestAcceptedIsTrueAndResponseSentDateNotNull(UUID userId, UUID friendId);
    Mono<Boolean> existsByUserIdAndFriendIdAndRequestAcceptedIsTrueAndResponseSentDateNotNull(UUID userId, UUID friendId);

    Mono<Void> deleteByUserId(UUID userId);
    Mono<Void> deleteByFriendId(UUID friendId);
}