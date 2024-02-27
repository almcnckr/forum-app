package com.project.forlite.core.utilities.mappers;

import com.project.forlite.business.requests.CreatePostRequest;
import com.project.forlite.entities.Post;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService{
    private ModelMapper modelMapper;

    @Autowired
    public ModelMapperManager(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

        modelMapper.typeMap(CreatePostRequest.class, Post.class).addMappings(
                mapper -> mapper.skip(Post::setId)
        );
    }

    @Override
    public ModelMapper forResponse() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
