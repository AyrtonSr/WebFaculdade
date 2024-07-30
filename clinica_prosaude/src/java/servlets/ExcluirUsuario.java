package servlets;

import banco_dados.ConexaoBancoDados;
import banco_dados.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcluirUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>SGC - Versão 1.0</title>");
        out.println("<link href='css/excluirusuario2.css' rel='stylesheet' type='text/css' />");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='message-container'>");
        out.println("<p>SGC - Sistema de Gestão de Clínicas 1.0</p>");
        out.println("<p>Exclusão de Usuários</p>");
        
        try{
            ConexaoBancoDados conexao = new ConexaoBancoDados();
            Usuarios usuario = new Usuarios();
            
            if(conexao.abrirConexao()){
                usuario.configurarConexao(conexao.obterConexao());
                
                if(usuario.excluirRegistro(Integer.parseInt(request.getParameter("codigo_usuario")))){
                    out.println("<h2>Registro do usuário excluído com sucesso!</h2>");
                    out.println("<a class='btn-voltar' href='menu_administracao.html'>Voltar</a>");
                }else{
                    out.println("<h2>Não foi possível excluir o registro do Médico!</h2>");
                    out.println("<a class='btn-voltar' href='menu_usuarios.html'>Voltar</a>");
                }
                conexao.fecharConexao();
            }else{
                out.println("<h2>Não foi possível estabelecer conexão com o banco de dados!</h2>");
                out.println("<a class='btn-voltar' href='menu_usuarios.html'>Voltar</a>");
            }
        }catch(Exception erro){
            erro.printStackTrace();
            out.println("<h2>Erro do sistema:processo de exclusão de Médico!</h2>");
            out.println("<a class='btn-voltar' href='menu_usuarios.html'>Voltar</a>");
        }
        
        out.println("</div>");
        out.println("<footer>");
        out.println("<p>Copyright(c) 2024 - Editora IFAM.</p>");
        out.println("</footer>");
        out.println("</body>");
        out.println("</html>");
    }
}
