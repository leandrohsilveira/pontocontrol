package br.com.lhs.pontocontrol.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pontocontrol.controleponto.controller.ControllerFactory;
import br.com.pontocontrol.controleponto.controller.IFolhaPontoController;
import br.com.pontocontrol.controleponto.controller.json.impl.FolhaMensalPontoJSON;
import br.com.pontocontrol.controleponto.controller.json.impl.RegistroDiarioPontoJSON;
import br.com.pontocontrol.controleponto.model.FolhaMensalPonto;
import br.com.pontocontrol.controleponto.model.RegistroDiarioPonto;
import br.com.pontocontrol.controleponto.util.DateUtils;

public class RegistroDiarioServlet extends BaseServlet {

	private static final long serialVersionUID = -2167947735392512684L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Integer dia = getParameterAsIntegerOrDefault(req, "dia", DateUtils.getActualDayOfMonth());
		final Integer mes = getParameterAsIntegerOrDefault(req, "mes", DateUtils.getActualMonth());
		final Integer ano = getParameterAsIntegerOrDefault(req, "ano", DateUtils.getActualYear());

		final IFolhaPontoController controller = ControllerFactory.localizar(IFolhaPontoController.class);
		final FolhaMensalPontoJSON folhaMensal = controller.recuperarFolhaMensal(ano, mes);
		if (folhaMensal != null) {
			final RegistroDiarioPontoJSON registroDiario = controller.recuperarRegistroDiario(dia, folhaMensal);
			if (registroDiario != null) {
				respondJson(resp, registroDiario);
				return;
			}
		}
		respondJson(resp, new RegistroDiarioPontoJSON(new RegistroDiarioPonto(dia)));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		acceptJson(req, resp, RegistroDiarioPontoJSON.class, (content) -> {
			final Integer mes = getParameterAsIntegerOrDefault(req, "mes", DateUtils.getActualMonth());
			final Integer ano = getParameterAsIntegerOrDefault(req, "ano", DateUtils.getActualYear());
			final IFolhaPontoController controller = ControllerFactory.localizar(IFolhaPontoController.class);
			final FolhaMensalPontoJSON folhaMensalJson = controller.recuperarFolhaMensal(ano, mes);
			final FolhaMensalPonto folhaMensal = folhaMensalJson.toModel();
			final RegistroDiarioPonto registroDiario = content.toModel();
			final RegistroDiarioPonto previousValue = folhaMensal.getRegistros().putIfAbsent(registroDiario.getDia(), registroDiario);
			if (previousValue != null) {
				respondJson(resp, new RegistroDiarioPontoJSON(registroDiario));
			} else {
				respondError(resp, HttpServletResponse.SC_CONFLICT, "Já existe um registro diário para esta data.");
			}
		});
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		acceptJson(req, resp, RegistroDiarioPontoJSON.class, (content) -> {
			final Integer mes = getParameterAsIntegerOrDefault(req, "mes", DateUtils.getActualMonth());
			final Integer ano = getParameterAsIntegerOrDefault(req, "ano", DateUtils.getActualYear());
			final IFolhaPontoController controller = ControllerFactory.localizar(IFolhaPontoController.class);
			final FolhaMensalPontoJSON folhaMensalJson = controller.recuperarFolhaMensal(ano, mes);
			final FolhaMensalPonto folhaMensal = folhaMensalJson.toModel();
			final RegistroDiarioPonto registroDiario = content.toModel();
			if (folhaMensal.getRegistros().containsKey(registroDiario.getDia())) {
				folhaMensal.getRegistros().put(registroDiario.getDia(), registroDiario);
				respondJson(resp, new RegistroDiarioPontoJSON(registroDiario));
			} else {
				respondError(resp, HttpServletResponse.SC_NOT_FOUND, "Não foi possível localizar o registro desta data.");
			}
		});
	}

}