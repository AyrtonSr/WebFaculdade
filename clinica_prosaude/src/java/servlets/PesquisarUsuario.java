package servlets;

import banco_dados.ConexaoBancoDados;
import banco_dados.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PesquisarUsuario extends HttpServlet {
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResultSet rsRegistro;
        PrintWriter out;
        String strUsuario;
        int intCodigoUsuario;
        
        strUsuario = request.getParameter("txtUsuario");
        
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='css/pesquisarusuario2.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>SGC - Sistema de Gestão de Clínicas 1.0</h1>");
        out.println("<h2>Cadastro de Usuários</h2>");
        
        try {
            ConexaoBancoDados conexao = new ConexaoBancoDados();
            Usuarios usuario = new Usuarios();
            
            if (conexao.abrirConexao()) {
                usuario.configurarConexao(conexao.obterConexao());
                
                intCodigoUsuario = usuario.localizarRegistro(strUsuario.toUpperCase());
                
                if (intCodigoUsuario != 0) {
                    rsRegistro = usuario.lerRegistro(intCodigoUsuario);
                    
                    out.println("<div class='user-info'>");
                    out.println("<h3>Identificação do usuário: " + rsRegistro.getString("Identificacao_Usuario") + "</h3>");
                    out.println("</div>");
                    
                    out.println("<div class='actions'>");
                    out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
                    out.println("<a href='editar_usuario.jsp?codigo_usuario=" + intCodigoUsuario + "' class='btn editar'>Editar</a>");
                    out.println("<a href='excluir_usuario.jsp?codigo_usuario=" + intCodigoUsuario + "' class='btn excluir'>Excluir</a>");
                    out.println("</div>");
                } else {
                    out.println("<h2>Usuário não encontrado!</h2>");
                    out.println("<div class='actions'>");
                    out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
                    out.println("</div>");
                }
                conexao.fecharConexao();
            } else {
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");
                out.println("<div class='actions'>");
                out.println("<a href='javascript:history.back()' class='btn voltar'>Voltar</a>");
                out.println("</div>");
            }
        
        } catch (Exception erro) {
            erro.printStackTrace();
            out.println("<h2>Erro do sistema: processo de cadastro do usuário!</h2>");
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
