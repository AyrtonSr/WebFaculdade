package servlets;

import banco_dados.ConexaoBancoDados;
import banco_dados.Especialidades;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.C_Especialidades;

/**
 *
 * @author silva
 */
public class InserirEspecialidade extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strdescEspecialidade;
        PrintWriter out;
        
        strdescEspecialidade = request.getParameter("txtEspecialidade");
        
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='clinica_medica.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body class='FundoPagina'>");
        out.println("<p class='TituloAplicacao'>SGC - Sistema de Gestão de Clínicas 1.0 </p>");
        out.println("<p class='TituloPagina'>Cadastro de Especialidade </p>");
        
        try {
            ConexaoBancoDados conexao = new ConexaoBancoDados();
            Especialidades especialidade = new Especialidades();
            
            C_Especialidades Especialidade = new C_Especialidades(strdescEspecialidade.toUpperCase(),0);
            
            if (conexao.abrirConexao()){
                especialidade.configurarConexao(conexao.obterConexao());
                
                if (especialidade.inserirRegistro(Especialidade)) {
                    out.println("<h2>Especialidade cadastrada com sucesso!</h2>");
                    out.println("<br><br><br><br>");
                    out.println("<a href='menu_especialidades.html'>Voltar</a>");
                } else {
                    out.println("<h2>Não foi possível cadastrar a Especialidade!</h2>");
                }
                
                conexao.fecharConexao();
            } else {
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: processo de cadastro de Especialidade!</h2>");
        }
        out.println("<p class='RodapePagina'> Copyright(c) 2024 - Editora IFAM.</p>");
        out.println("</body>");
        out.println("</html>");
    }

}
