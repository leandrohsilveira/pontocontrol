package br.com.lhs.pontocontrol.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pontocontrol.controleponto.SessaoManager;
import br.com.pontocontrol.controleponto.controller.ControllerFactory;
import br.com.pontocontrol.controleponto.controller.IFolhaPontoController;
import br.com.pontocontrol.controleponto.controller.json.impl.FolhaMensalPontoJSON;
import br.com.pontocontrol.controleponto.model.FolhaMensalPonto;

public class RegistroMensalServlet extends BaseServlet {

	private static final long serialVersionUID = -6357457030505936418L;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		if (SessaoManager.getInstance().isUsuarioAutenticado()) {
			final Integer mes = Integer.valueOf(req.getParameter("mes"));
			final Integer ano = Integer.valueOf(req.getParameter("ano"));

			final IFolhaPontoController folhaPontoController = ControllerFactory.localizar(IFolhaPontoController.class);
			final FolhaMensalPontoJSON mensalPontoJSON = folhaPontoController.recuperarFolhaMensal(ano, mes);
			if (mensalPontoJSON != null) {
				respondJson(resp, mensalPontoJSON);
			} else {
				respondJson(resp, new FolhaMensalPontoJSON(new FolhaMensalPonto()));
			}
		} else {
			respondError(resp, HttpServletResponse.SC_UNAUTHORIZED, "Você deve estar autenticado para acessar este conteúdo.");
		}

	}

}