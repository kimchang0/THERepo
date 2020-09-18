package poly.persistance.mapper;

import config.Mapper;
import poly.dto.NewsDTO;

@Mapper("NewsMapper")
public interface INewsMapper {

	int InsertNewsInfo(NewsDTO nDTO)throws Exception;
	
}
