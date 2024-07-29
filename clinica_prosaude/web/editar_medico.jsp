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
            int intCodigoMedico = Integer.parseInt(request.getParameter("codigo_medico"));
            blnConectado = false;
            
            if (conexao.abrirConexao()) {
                medico.configurarConexao(conexao.obterConexao());
                
                rsRegistro = medico.lerRegistro(intCodigoMedico);
                
                Medico.setNomeMedico(rsRegistro.getString("nome_medico"));
                Medico.setCrm(rsRegistro.getString("crm"));
                Medico.setCodespecialidade(rsRegistro.getInt("codigo_especialidade"));
                Medico.setCodigomedico(intCodigoMedico);
                
                conexao.fecharConexao();
                
                blnConectado = true;
            }else
                out.println("<p>Falha na conexão com o banco de dados!</p>");
        %>
        
        <% if (blnConectado) {%>
            <p class="TituloAplicacao">SGC - Sistema de Gestão de Clínicas 1.0</p>
            <p class="TituloPagina">Cadastro de Médico - Edição</p>
            
            <form name="formEditaMedico" method="post" action="AtualizarMedico" target="_parent">
                <p>Nome do Médico <input type="text" name="txtNomeMedico" size="20" maxlength="50" value="<%=Medico.getNomeMedico()%>"/></p>
                <p>Especialidades: </p>
                <% int codigoEspecialidade = Medico.getCodespecialidade(); %>
                
                <p>&nbsp;&nbsp;&nbsp;&nbsp;<input name="especialidade" type="radio" value="1" <%= (codigoEspecialidade == 1) ? "checked" : "" %> />Plantonista</p>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;<input name="especialidade" type="radio" value="2" <%= (codigoEspecialidade == 2) ? "checked" : "" %> />Cardiologista</p>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;<input name="especialidade" type="radio" value="3" <%= (codigoEspecialidade == 3) ? "checked" : "" %> />Dermatologista</p>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;<input name="especialidade" type="radio" value="4" <%= (codigoEspecialidade == 4) ? "checked" : "" %> />Dentista</p>
                
                <p>
                  <input type="hidden" name="codigo_medico" value="<%=intCodigoMedico%>">
                  <input type="hidden" name="crm" value="<%=Medico.getCrm()%>">
                </p>
                <br>
                <p><input type="submit" name="btnAtualizar" value="Atualizar" />
                  <span class="LinkVoltar"><a href="javascript:history.back()">[Voltar]</a></span>
                </p>
            </form>
        <p class="RodapePagina">Copyright(c) 2015 - Editora Érica Ltda.</p>
        <%}%>
        
    </body>
</html>
