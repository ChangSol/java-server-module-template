package org.changsol.api.apps.samples.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.changsol.api.apps.samples.dto.SampleMasterDto;
import org.changsol.api.apps.samples.domain.SampleMaster;
import org.changsol.api.apps.samples.mapper.SampleMasterMapper;
import org.changsol.api.apps.samples.repository.SampleMasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SampleMasterService {
    private final SampleMasterRepository sampleMasterRepository;

    /**
     * SampleMaster Data Get
     * @param request 검색조건
     * @return SampleMasterDto.Response 리스트
     */
    public List<SampleMasterDto.Response> getSampleMasterList(SampleMasterDto.Request request) {
        //조건
        if (StringUtils.isNotBlank(request.getKeyword())) {

        }

        return sampleMasterRepository.findAll().stream().map(SampleMasterMapper.INSTANCE::toResponse).toList();
    }

    /**
     * SampleMaster Data Create One
     * @param createOrUpdate 생성조건
     * @return SampleMasterDto.Response
     */
    @Transactional
    public SampleMasterDto.Response createOne(SampleMasterDto.CreateOrUpdate createOrUpdate){
        //New Object
        SampleMaster sampleMaster = SampleMasterMapper.INSTANCE.entityCreate(createOrUpdate);
        sampleMasterRepository.save(sampleMaster);
        return SampleMasterMapper.INSTANCE.toResponse(sampleMaster);
    }

    /**
     * SampleMaster Data Update One
     * @param id 고유ID
     * @param createOrUpdate 갱신조건
     * @return SampleMasterDto.Response
     */
    @Transactional
    public SampleMasterDto.Response updateOne(Long id, SampleMasterDto.CreateOrUpdate createOrUpdate){
        //Find Object
        SampleMaster sampleMaster = sampleMasterRepository.findById(id).orElseThrow(() -> new NotFoundException("SampleMaster를 찾을 수 없습니다."));

        //Mapping
        SampleMasterMapper.INSTANCE.entityUpdate(sampleMaster, createOrUpdate);

        return SampleMasterMapper.INSTANCE.toResponse(sampleMaster);
    }

    /**
     * SampleMaster Data Delete One
     * @param id 고유 ID
     * @return SampleMasterDto.Response
     */
    @Transactional
    public SampleMasterDto.Response deleteOne(Long id){
        //Find Object
        SampleMaster sampleMaster = sampleMasterRepository.findById(id).orElseThrow(() -> new NotFoundException("SampleMaster를 찾을 수 없습니다."));

        //Delete
        sampleMasterRepository.delete(sampleMaster);

        return SampleMasterMapper.INSTANCE.toResponse(sampleMaster);
    }
}