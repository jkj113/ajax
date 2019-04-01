package service;

import java.util.List;
import java.util.Map;

public interface MovieService {
  

	public List<Map<String,String>> selectMovieList();
	public int insertMovie(Map<String,String> movie);
}
