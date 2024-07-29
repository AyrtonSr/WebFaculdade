<%@page import="model.Cusuarios"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.C_Medicos"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="medico" scope="page" class="banco_dados.Medicos"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>SGC - Versão 1.0</title>
        <link href="clinica_medica.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="FundoPagina">
        <%
            ResultSet rsRegistro;
            boolean blnConectado;
            
            C_Medicos Medico = new C_Medicos();
            int intCodigoMedico = Integer.parseInt(request.getParamenter("codigo_medico"));
            blnConectado = false;
            
            if (conexao.abrirConexao()){
                medico.configurarConexao(conexao.obterConexao());
                
                rsRegistro = usuario.lerRegistro(intCodigoMedico);
                
                Medico.setNomeMedico(rsRegistro.getString("nome_medico"));
                Medico.setCrm(rsRegistro.getString("crm"));
                Medico.setCodigomedico(intCodigoMedico);
                
                conexao.fecharConexao();
                
                blnConectado = true;
            }
            else
                out.println("<p>Falha na conexão com o banco de dados!</p>");
        %>
        <%if (blnConectado) {%>
        
            <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
            <p class="TituloPagina">Cadastro de Médico - Exclusão</p>
            
            <form name="formExcluimedico" method="post" action="ExcluirMedico" target="_parent">
              <p>Nome do Médico <%=Medico.getNomeMedico()%></p>
              <br>
              <p>CRM: <%=Medico.getCrm()%></p>
              <p><input type="hidden" name="codigo_medico" value="<%=intCodigoMedico%>"></p>
              <br>
              <p><input type="submit" name="btnExcluir" value="Excluir" />
                  <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span>
              </p>
            </form>
        
        <%} else {%>
            <h1>Foi nao</h1>
        <%}%>
        
    </body>
</html>
