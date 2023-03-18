package org.changsol.api.apps.samples.service;

import com.google.common.collect.Lists;
import java.util.List;
import javax.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.changsol.api.apps.samples.domain.SampleDetail;
import org.changsol.api.apps.samples.domain.SampleMaster;
import org.changsol.api.apps.samples.dto.SampleMasterDto;
import org.changsol.api.apps.samples.mapper.SampleMasterMapper;
import org.changsol.api.apps.samples.repository.SampleMasterRepository;
import org.changsol.api.utils.ChangSolUtils;
import org.changsol.api.utils.jpas.restriction.ChangSolJpaRestriction;
import org.changsol.api.utils.page.ChangSolPageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	 *
	 * @param request 검색조건
	 * @return SampleMasterDto.Response 리스트
	 */
	public List<SampleMasterDto.Response> getSampleMasterList(SampleMasterDto.Request request) {
		// 조건
		ChangSolJpaRestriction restriction = new ChangSolJpaRestriction();
		if (ChangSolUtils.isNotBlank(request.getKeyword())) {
			final String KEYWORD = "%" + request.getKeyword() + "%";
			restriction.like("masterName", KEYWORD);

			// 디테일
			restriction.like("sampleDetails.detailName", KEYWORD);

			// 디테일 -> 유저
			restriction.like("sampleDetails.user.name", KEYWORD);

			// 디테일 -> 디테일서브 -> 유저
			restriction.like("sampleDetails.sampleDetailSubs.user.name", KEYWORD);
		}

		restriction.addFetch("sampleDetails", JoinType.LEFT);
		// restriction.addJoin("sampleDetails", JoinType.LEFT);

		return sampleMasterRepository.findAll(restriction.toSpecification())
									 .stream()
									 .map(SampleMasterMapper.INSTANCE::response)
									 .toList();
	}

	/**
	 * SampleMaster Data Get Page
	 *
	 * @param request 검색조건
	 * @return ChangSolPageUtils.Response
	 */
	public ChangSolPageUtils.Response<SampleMasterDto.Response> getSampleMasterPage(SampleMasterDto.RequestPage request) {
		// 정렬
		List<Sort.Order> sortList = Lists.newArrayList();
		if (ChangSolUtils.isNotBlank(request.getSortColumn()) && request.getSortType() != null) {
			sortList.add(
				switch (request.getSortType()) {
					case ASC -> Sort.Order.asc(request.getSortColumn());
					case DESC -> Sort.Order.desc(request.getSortColumn());
				}
			);
		}
		// 지정된 정렬이 없거나, 페이징을 위해 고유 값 컬럼 ADD
		sortList.add(Sort.Order.desc("id"));
		Sort sort = Sort.by(sortList);

		// 조건
		ChangSolJpaRestriction restriction = new ChangSolJpaRestriction();
		if (ChangSolUtils.isNotBlank(request.getKeyword())) {
			restriction.like("masterName", request.getKeyword());

			//하위 검색 처리
			restriction.like("sampleDetails.detailName.", request.getKeyword());
		}
		// restriction.addFetch("sampleDetails", JoinType.LEFT);
		// restriction.addJoin("sampleDetails", JoinType.LEFT);

		PageRequest pageRequest = PageRequest.of(request.getPage(), request.getLimit(), sort);
		Page<SampleMaster> sampleMasterPage = sampleMasterRepository.findAll(restriction.toSpecification(), pageRequest);
		return ChangSolPageUtils.valueOf(sampleMasterPage, sampleMasterPage.stream()
																		   .map(SampleMasterMapper.INSTANCE::response)
																		   .toList());
	}

	/**
	 * SampleMaster Data Create One
	 *
	 * @param createOrUpdate 생성조건
	 * @return SampleMasterDto.Response
	 */
	@Transactional
	public SampleMasterDto.Response createOne(SampleMasterDto.CreateOrUpdate createOrUpdate) {
		// New Object
		SampleMaster sampleMaster = SampleMasterMapper.INSTANCE.create(createOrUpdate);
		if (ChangSolUtils.isNotBlank(createOrUpdate.getDetailName())) {
			SampleDetail sampleDetail = new SampleDetail();
			sampleDetail.setDetailName(createOrUpdate.getDetailName());
			sampleDetail.setSampleMaster(sampleMaster);
			sampleMaster.getSampleDetails().add(sampleDetail);
		}
		sampleMasterRepository.save(sampleMaster);

		return SampleMasterMapper.INSTANCE.response(sampleMaster);
	}

	/**
	 * SampleMaster Data Update One
	 *
	 * @param id             고유ID
	 * @param createOrUpdate 갱신조건
	 * @return SampleMasterDto.Response
	 */
	@Transactional
	public SampleMasterDto.Response updateOne(Long id, SampleMasterDto.CreateOrUpdate createOrUpdate) {
		// Find Object
		SampleMaster sampleMaster = sampleMasterRepository.findById(id).orElseThrow(() -> new NotFoundException("SampleMaster를 찾을 수 없습니다."));

		// Mapping
		SampleMasterMapper.INSTANCE.update(sampleMaster, createOrUpdate);

		return SampleMasterMapper.INSTANCE.response(sampleMaster);
	}

	/**
	 * SampleMaster Data Delete One
	 *
	 * @param id 고유 ID
	 * @return SampleMasterDto.Response
	 */
	@Transactional
	public SampleMasterDto.Response deleteOne(Long id) {
		// Find Object
		SampleMaster sampleMaster = sampleMasterRepository.findById(id).orElseThrow(() -> new NotFoundException("SampleMaster를 찾을 수 없습니다."));

		// Delete
		sampleMasterRepository.delete(sampleMaster);

		return SampleMasterMapper.INSTANCE.response(sampleMaster);
	}
}
