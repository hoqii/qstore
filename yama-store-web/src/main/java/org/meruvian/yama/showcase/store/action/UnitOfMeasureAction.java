/**
 * 
 */
package org.meruvian.yama.showcase.store.action;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.inca.struts2.rest.ActionResult;
import org.meruvian.inca.struts2.rest.annotation.Action;
import org.meruvian.inca.struts2.rest.annotation.Action.HttpMethod;
import org.meruvian.inca.struts2.rest.annotation.ActionParam;
import org.meruvian.yama.showcase.store.product.UnitOfMeasure;
import org.meruvian.yama.showcase.store.product.UnitOfMeasureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.opensymphony.xwork2.ActionSupport;

@Action(name = "/backend/uom")
public class UnitOfMeasureAction extends ActionSupport {
	@Inject
	private UnitOfMeasureService uomService;
	
	@Action(method = HttpMethod.GET)
	public ActionResult uomList(@ActionParam("q") String q, @ActionParam("max") int max,
			@ActionParam("page") int page) {
		max = max == 0 ? 10 : max;
		q = q == null ? "" : q;
		Page<? extends UnitOfMeasure> uom = uomService.findUnitOfMeasureByKeyword(q, new PageRequest(page, max));
		
		return new ActionResult("freemarker","/view/store/uom/uom-list.ftl")
				.addToModel("uoms", uom);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.GET)
	public ActionResult uomForm(@ActionParam("id") String id) {
		ActionResult actionResult = new ActionResult("freemarker", "/view/store/uom/uom-form.ftl");
		
		if (!StringUtils.equalsIgnoreCase(id, "-")) {
			UnitOfMeasure uom = uomService.getUnitOfMeasureById(id);
			actionResult.addToModel("uom", uom);
		} else {
			actionResult.addToModel("uom", new UnitOfMeasure());
		}
		
		
		return actionResult;
	}
	
	@Action(name = "/{id}/delete", method = HttpMethod.GET)
	public ActionResult deleteUnitOfMeasure(@ActionParam("id") String id) {
		
		uomService.removeUnitOfMeasure(id);
		
		String redirectUri = "/backend/uom?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	@Action(name = "/{id}/edit", method = HttpMethod.POST)
	public ActionResult updateUnitOfMeasure(@ActionParam("id") String id,  @ActionParam("uom") UnitOfMeasure uom) {
		validateUnitOfMeasure(uom, id);
		if (hasFieldErrors()) {
			return new ActionResult("freemarker", "/view/store/uom/uom-form.ftl");
		}

		
		UnitOfMeasure r = uomService.saveUnitOfMeasure(uom);
		String redirectUri = "/backend/uom?success";
		
		return new ActionResult("redirect", redirectUri);
	}
	
	
	
	private void validateUnitOfMeasure(UnitOfMeasure uom, String uomid) {
		if (StringUtils.isBlank(uom.getName())) {
			addFieldError("uom.name", getText("message.store.uom.name.notempty"));
		} else if (!StringUtils.equalsIgnoreCase(uom.getId(), uomid)) {
			if (uomService.getUnitOfMeasureById(uom.getId()) != null) {
				addFieldError("uom.name", getText("message.store.uom.name.exist"));
			}
		}
	}
}
