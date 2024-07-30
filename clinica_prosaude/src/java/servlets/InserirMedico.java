package servlets;

import banco_dados.ConexaoBancoDados;
import banco_dados.Medicos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.C_Medicos;

/**
 *
 * @author silva
 */
public class InserirMedico extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strNomeMedico, strCRM, strCodigoEspecialidade;
        PrintWriter out;
        
        strNomeMedico = request.getParameter("txtNMedico");
        strCRM = request.getParameter("txtCRM");
        strCodigoEspecialidade = request.getParameter("especialidade");
        
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='css/inserirusuario2.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='message-container'>");
        out.println("<p>SGC - Sistema de Gestão de Clínicas 1.0</p>");
        out.println("<p>Cadastro de Médico</p>");
        
        try{
            ConexaoBancoDados conexao = new ConexaoBancoDados();
            Medicos medico = new Medicos();
            
            int intCodigoEspecialidade;
            try {
                intCodigoEspecialidade = Integer.parseInt(strCodigoEspecialidade);
            } catch (NumberFormatException e) {
                out.println("<p>Erro: Código da especialidade inválido.</p>");
                e.printStackTrace();
                return;
            }
            
            C_Medicos Medico = new C_Medicos (strNomeMedico.toUpperCase(),strCRM, 
            intCodigoEspecialidade,0);
            
            if (conexao.abrirConexao()) {
                medico.configurarConexao(conexao.obterConexao());

                if (medico.inserirRegistro(Medico)) {
                    out.println("<h2>Médico cadastrado com sucesso!</h2>");
                    out.println("<a class='btn-voltar' href='menu_medicos.html'>Voltar</a>");
                } else {
                    out.println("<h2>Não foi possível cadastrar o médico!</h2>");
                    out.println("<a class='btn-voltar' href='menu_medicos.html'>Voltar</a>");
                }

                conexao.fecharConexao();
            } else {
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");
                out.println("<a class='btn-voltar' href='menu_medicos.html'>Voltar</a>");
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: processo de cadastro de médico!</h2>");
            out.println("<a class='btn-voltar' href='menu_medicos.html'>Voltar</a>");
        }
        out.println("</div>");
        out.println("<footer>");
        out.println("<p>Copyright(c) 2024 - Editora IFAM.</p>");
        out.println("</footer>");
        out.println("</body>");
        out.println("</html>");
    }

}
