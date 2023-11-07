package by.krainet.domashkevich_test_trainee.service;

import by.krainet.domashkevich_test_trainee.dto.CandidateDto;
import by.krainet.domashkevich_test_trainee.entity.Candidate;
import by.krainet.domashkevich_test_trainee.mapper.CandidateMapper;
import by.krainet.domashkevich_test_trainee.mapper.DirectionMapper;
import by.krainet.domashkevich_test_trainee.repository.CandidateRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepo candidateRepo;
    private final CandidateMapper candidateMapper;
    private final DirectionMapper directionMapper;
    private final FileService fileService;


    public List<CandidateDto> findAll(String name, String lastname, Pageable paging) {
        Page<Candidate> page;
        if (name == null && lastname == null) {
            page = candidateRepo.findAll(paging);
        } else if (name != null) {
            page = candidateRepo.findByNameContainingIgnoreCase(name, paging);
        } else {
            page = candidateRepo.findByLastnameContainingIgnoreCase(lastname, paging);
        }
        return candidateMapper.listToDto(page.getContent());
    }

    public Optional<CandidateDto> findById(Long id) {
        return candidateRepo.findById(id).map(candidateMapper::modelToDto);
    }

    @Transactional
    public CandidateDto save(CandidateDto candidateDto) {
        var directions = directionMapper.dtoToList(candidateDto.getDirections());
        var candidate = candidateMapper.dtoToModel(candidateDto);
        candidate.setDirections(directions);

        return candidateMapper.modelToDto(candidateRepo.save(candidate));
    }

    @Transactional
    public Optional<CandidateDto> update(CandidateDto candidateDto, Long id) {
        var directions = directionMapper.dtoToList(candidateDto.getDirections());
        return candidateRepo.findById(id)
                .map(candidate -> {
                    candidate.setName(candidateDto.getName());
                    candidate.setSurname(candidateDto.getSurname());
                    candidate.setLastname(candidateDto.getLastname());
                    candidate.setDescription(candidateDto.getDescription());
                    candidate.setDirections(directions);
                    return candidateMapper.modelToDto(candidateRepo.save(candidate));
                });
    }

    @Transactional
    @SneakyThrows
    public Optional<CandidateDto> uploadPhoto(Long id, MultipartFile image) {
        String bucket = "/app/media/images";
        var imageName = UUID.randomUUID() + "-" + image.getOriginalFilename();
        var content = image.getInputStream();
        return candidateRepo.findById(id)
                .map(entity -> {
                    entity.setPhotoLink(imageName);
                    fileService.uploadFile(bucket, imageName, content);
                    log.info("Photo has been uploaded");
                    return candidateMapper.modelToDto(candidateRepo.save(entity));
                });
    }

    @SneakyThrows
    public Optional<Resource> getPhoto(Long id) {
        String bucket = "/app/media/images";
        return candidateRepo.findById(id)
                .map(Candidate::getPhotoLink)
                .flatMap(photo -> fileService.downloadFile(bucket, photo));

    }

    @Transactional
    @SneakyThrows
    public Optional<CandidateDto> uploadDocument(Long id, MultipartFile image) {
        String bucket = "/app/media/docs";
        var documentName = UUID.randomUUID() + "-" + image.getOriginalFilename();
        var content = image.getInputStream();
        return candidateRepo.findById(id)
                .map(entity -> {
                    entity.setCvLink(documentName);
                    fileService.uploadFile(bucket, documentName, content);
                    log.info("Document has been uploaded");
                    return candidateMapper.modelToDto(candidateRepo.save(entity));
                });
    }


    public Optional<Resource> getDocument(Long id) {
        String bucket = "/app/media/docs";
        return candidateRepo.findById(id)
                .map(Candidate::getCvLink)
                .flatMap(photo -> fileService.downloadFile(bucket, photo));
    }
}
