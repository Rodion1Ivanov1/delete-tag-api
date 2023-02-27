package stack.overflow.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stack.overflow.backend.model.entity.Tag;
import stack.overflow.backend.repository.TagRepository;
import stack.overflow.backend.service.TagService;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Transactional
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (tagRepository.findById(id).isPresent()) {
            tagRepository.delete(tagRepository.findById(id).get());
        }
        throw new EntityNotFoundException();
    }

}
