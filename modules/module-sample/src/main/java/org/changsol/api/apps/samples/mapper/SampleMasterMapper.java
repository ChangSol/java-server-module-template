package org.changsol.api.apps.samples.mapper;

import org.changsol.api.apps.samples.dto.SampleMasterDto;
import org.changsol.api.apps.samples.domain.SampleMaster;
import org.changsol.api.utils.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SampleMasterMapper extends BaseMapper<SampleMaster, SampleMasterDto.Response, SampleMasterDto.CreateOrUpdate, SampleMasterDto.CreateOrUpdate> {

  SampleMasterMapper INSTANCE = Mappers.getMapper(SampleMasterMapper.class);

//  //Response Mapping
//  SampleMasterDto.Response toResponse(SampleMaster sampleMaster);
//
//  //Create Map
//  SampleMaster entityCreate(SampleMasterDto.CreateOrUpdate createOrUpdate);
//
//  //Update Map
//  void entityUpdate(SampleMaster sampleMaster, @MappingTarget SampleMasterDto.CreateOrUpdate createOrUpdate);
}
