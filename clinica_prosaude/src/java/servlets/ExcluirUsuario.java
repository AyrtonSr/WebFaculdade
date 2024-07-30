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
        out.println("<div class='container'>");
        out.println("<p>SGC - Sistema de Gestão de Clínicas 1.0</p>");
        out.println("<p>Cadastro de Usuários</p>");
        
        try{
            ConexaoBancoDados conexao = new ConexaoBancoDados();
            Usuarios usuario = new Usuarios();
            
            if(conexao.abrirConexao()){
                usuario.configurarConexao(conexao.obterConexao());
                
                if(usuario.excluirRegistro(Integer.parseInt(request.getParameter("codigo_usuario")))){
                    out.println("<h2>Registro do usuário excluído com sucesso!</h2>");
                }else{
                    out.println("<h2 class='error'>Não foi possível excluir o registro do usuário!</h2>");
                }
                conexao.fecharConexao();
            }else{
                out.println("<h2 class='error'>Não foi possível estabelecer conexão com o banco de dados!</h2>");
            }
        }catch(Exception erro){
            erro.printStackTrace();
            out.println("<h2 class='error'>Erro do sistema: processo de exclusão do usuário!</h2>");
        }
        
        out.println("<a href='menu_usuarios.html'>Fechar</a>");
        out.println("<p class='RodapePagina'>Copyright(c) 2015 - Editora Érica Ltda.</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
