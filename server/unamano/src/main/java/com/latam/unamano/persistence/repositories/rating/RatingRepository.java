package com.latam.unamano.persistence.repositories.rating;

import com.latam.unamano.persistence.entities.rating.Rating;
import com.latam.unamano.persistence.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByUser(User user);

}
