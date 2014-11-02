/**
 * 
 */
package org.meruvian.yama.showcase.store.product;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dianw
 *
 */
@Service
@Transactional(readOnly = true)
public class RestUnitOfMeasureService implements UnitOfMeasureService {
	@Inject
	private UnitOfMeasureRepository uomRepository;
	
	@Override
	public UnitOfMeasure getUnitOfMeasureById(String categoryId) {
		return uomRepository.findById(categoryId);
	}

	@Override
	public Page<UnitOfMeasure> findUnitOfMeasureByKeyword(String keyword, Pageable pageable) {
		return uomRepository.findByNameContainsOrDescriptionContains(keyword, keyword, pageable);
	}

	@Override
	@Transactional
	public UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure category) {
		if (StringUtils.isBlank(category.getId())) {
			return uomRepository.save(category);
		} else {
			UnitOfMeasure c = uomRepository.findById(category.getId());
			c.setName(category.getName());
			c.setDescription(category.getDescription());
			
			return c;
		}
	}

	@Override
	@Transactional
	public boolean removeUnitOfMeasure(String categoryId) {
		uomRepository.delete(categoryId);
		
		return true;
	}

}
