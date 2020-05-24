package io.microservice.moviecatalogservice.resource;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import io.microservice.moviecatalogservice.model.CatalogItem;
import io.microservice.moviecatalogservice.model.Movie;
import io.microservice.moviecatalogservice.model.Rating;
import io.microservice.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate resttemplate;
	
	
	@RequestMapping("/{userID}")
	public List<CatalogItem> getCatalog(@PathVariable("userID") String userID){
		
		
		
		
		//System.out.print(resttemplate.getForObject("http://localhost:8081/movies/10", Movie.class).getMovieID());
		UserRating rating=resttemplate.getForObject("http://localhost:8082/ratingsdata/user/"+userID, UserRating.class);
		
		return rating.getRatings().stream().map(ratings->{
			Movie movie=resttemplate.getForObject("http://localhost:8081/movies/"+ratings.getMovieId(), Movie.class);
			
			return new CatalogItem(movie.getName(),"desc",ratings.getRating());
		}).collect(Collectors.toList());
		
	}
}