package servlets;

import banco_dados.ConexaoBancoDados;
import banco_dados.Medicos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author silva
 */
public class PesquisarMedico extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResultSet rsRegistro;
        PrintWriter out;
        String strMedico;
        int intCodigoMedico;
        
        strMedico = request.getParameter("txtCRM");
        System.out.println(strMedico + "TESTE1");
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='css/pesquisarmedico2.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>SGC - Sistema de Gestão de Clínicas 1.0</h1>");
        out.println("<h2>Edição de Médicos</h2>");
        
        try{
            ConexaoBancoDados conexao = new ConexaoBancoDados();
            Medicos medico = new Medicos();
            
            if(conexao.abrirConexao()){
                medico.configurarConexao(conexao.obterConexao());
                
                intCodigoMedico = medico.localizarRegistro(strMedico.toUpperCase());
                
                if(intCodigoMedico != 0){
                    rsRegistro = medico.lerRegistro(intCodigoMedico);
                    
                    out.println("<div class='user-info'>");
                    out.println("<h3>Nome do Médico:"+rsRegistro.getString("nome_medico")+"<br>");
                    out.println("<h3>CRM do Médico:"+rsRegistro.getString("crm")+"<br>");
                    out.println("</div>");
                    
                    out.println("<div class='actions'>");
                    out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
                    out.println("<a href='editar_medico.jsp?codigo_medico=" + intCodigoMedico + "' class='btn editar'>Editar</a>");
                    out.println("<a href='excluir_medico.jsp?codigo_medico=" + intCodigoMedico + "' class='btn excluir'>Excluir</a>");
                    out.println("</div>");
                    
                }else{
                    out.println("<h2>Médico não encontrando!</h2>");
                    out.println("<div class='actions'>");
                    out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
                    out.println("</div>");
                }
                conexao.fecharConexao();
            }else{
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");
                out.println("<div class='actions'>");
                out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
                out.println("</div>");
            }
        }catch(Exception erro){
            erro.printStackTrace();
            out.println("<h2>Erro do sistema:processo de cadastro do Médico!</h2>");
            out.println("<div class='actions'>");
            out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
            out.println("</div>");
        }
        out.println("</div>");
        out.println("<footer>");
        out.println("<p class='footer'>Copyright(c) 2024 - Editora IFAM.</p>");
        out.println("</footer>");
        out.println("</body>");
        out.println("</html>");
    }

}
