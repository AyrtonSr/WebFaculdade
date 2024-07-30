<%@page import="java.sql.ResultSet"%>
<%@page import="model.C_Medicos"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="medico" scope="page" class="banco_dados.Medicos"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SGC - Versão 1.0</title>
        <link href="css/excluirmedico.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="FundoPagina">
        <%
            ResultSet rsRegistro;
            boolean blnConectado;
            
            C_Medicos Medico = new C_Medicos();
            int intCodigoMedico = Integer.parseInt(request.getParameter("codigo_medico"));
            blnConectado = false;
            
            if (conexao.abrirConexao()){
                medico.configurarConexao(conexao.obterConexao());
                
                rsRegistro = medico.lerRegistro(intCodigoMedico);
                
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
        <div class="container">
            <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
            <p class="TituloPagina">Edição de Médico - Exclusão</p>
            
            <form name="formExcluimedico" method="post" action="ExcluirMedico" target="_parent">
                <div class="form-group">
                    <label>Nome do Médico:</label>
                    <span><%=Medico.getNomeMedico()%></span>
                </div>
                <div class="form-group">
                    <label>CRM:</label>
                    <span><%=Medico.getCrm()%></span>
                </div>
                <input type="hidden" name="codigo_medico" value="<%=intCodigoMedico%>">
                <div class="form-actions">
                    <a class="btn LinkVoltar" href="javascript:history.back()">Voltar</a>
                    <button type="submit" name="btnExcluir">Excluir</button>
                </div>
            </form>
            <footer class="RodapePagina">
                Copyright(c) 2024 - Editora IFAM.
            </footer>
        </div>
        <%}%>
    </body>
</html>
