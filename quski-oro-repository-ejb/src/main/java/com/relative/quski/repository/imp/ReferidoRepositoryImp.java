package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoReferido;
import com.relative.quski.repository.ReferidoRepository;
import com.relative.quski.repository.spec.DevolucionByNumeroOperacionSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "referidoRepository")
public class ReferidoRepositoryImp extends GeneralRepositoryImp<Long, TbQoReferido>
		implements ReferidoRepository {

	@Inject
	Logger log;

	

}
