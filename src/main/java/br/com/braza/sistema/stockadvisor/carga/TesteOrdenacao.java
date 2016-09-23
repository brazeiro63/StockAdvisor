package br.com.braza.sistema.stockadvisor.carga;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TesteOrdenacao
 */
@WebServlet("/TesteOrdenacao")
public class TesteOrdenacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesteOrdenacao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Iniciando a ordenação: \n");
		
		Ordenacao ord = new Ordenacao();

		ArrayList<String> arquivoOrdenado = ord.ordenar();
		Integer registros = arquivoOrdenado.size();
		
		response.getWriter().append("Tamanho do arquivo a ordenar: " + registros + "\n").append(request.getContextPath());
		response.getWriter().append("Ordenando: \n" );

		Integer n = 0;
		
		for (String linha : arquivoOrdenado) {

			response.getWriter().append(linha + "\n");

			n++;
			if (n >= 100){
				break;
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
