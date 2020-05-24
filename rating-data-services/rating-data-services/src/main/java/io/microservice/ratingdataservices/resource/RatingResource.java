package io.microservice.ratingdataservices.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.microservice.ratingdataservices.model.Rating;
import io.microservice.ratingdataservices.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
    	List<Rating>rating=Arrays.asList(
				new Rating("1234",5),
				new Rating("5678",6)
				);
		
        UserRating userRating = new UserRating();
        userRating.setRatings(rating);
        return userRating;

    }

}