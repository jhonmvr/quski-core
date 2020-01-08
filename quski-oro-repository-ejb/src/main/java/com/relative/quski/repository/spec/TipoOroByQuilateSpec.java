package com.relative.quski.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoTipoOro;
public class TipoOroByQuilateSpec  extends AbstractSpecification<TbQoTipoOro> {
	private String quilate;

	public TipoOroByQuilateSpec(String quilate) {

		this.quilate = quilate == null ? "" : quilate;
	}

	public TipoOroByQuilateSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoTipoOro arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoTipoOro> poll, CriteriaBuilder cb) {

		return cb.and(cb.like(poll.<String>get("quilate"),"%"+this.quilate+"%"), cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
	}
}
