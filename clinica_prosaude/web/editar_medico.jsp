<%@page import="java.sql.ResultSet"%>
<%@page import="model.C_Medicos"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="medico" scope="page" class="banco_dados.Medicos"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SGC - Versão 1.0</title>
        <link href="css/editarmedico.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
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
        <div class="header">
            <h1>SGC - Sistema de Gestão de Clínicas 1.0</h1>
            <h2>Cadastro de Médico - Edição</h2>
        </div>
        
        <div class="container">
            <form name="formEditaMedico" method="post" action="AtualizarMedico" target="_parent">
                <div class="form-group">
                    <label for="txtNomeMedico">Nome do Médico:</label>
                    <input id="txtNomeMedico" name="txtNomeMedico" type="text" size="20" maxlength="50" value="<%=Medico.getNomeMedico()%>"/>
                </div>
                <p>Especialidades:</p>
                <% int codigoEspecialidade = Medico.getCodespecialidade(); %>
                
                <div class="form-group especialidade-group">
                    <input id="plantonista" name="especialidade" type="radio" value="1" <%= (codigoEspecialidade == 1) ? "checked" : "" %> />
                    <label for="plantonista">Plantonista</label>
                </div>
                <div class="form-group especialidade-group">
                    <input id="cardiologista" name="especialidade" type="radio" value="2" <%= (codigoEspecialidade == 2) ? "checked" : "" %> />
                    <label for="cardiologista">Cardiologista</label>
                </div>
                <div class="form-group especialidade-group">
                    <input id="dermatologista" name="especialidade" type="radio" value="3" <%= (codigoEspecialidade == 3) ? "checked" : "" %> />
                    <label for="dermatologista">Dermatologista</label>
                </div>
                <div class="form-group especialidade-group">
                    <input id="dentista" name="especialidade" type="radio" value="4" <%= (codigoEspecialidade == 4) ? "checked" : "" %> />
                    <label for="dentista">Dentista</label>
                </div>
                
                <input type="hidden" name="codigo_medico" value="<%=intCodigoMedico%>">
                <input type="hidden" name="crm" value="<%=Medico.getCrm()%>">
                
                <div class="form-actions">
                    <a class="btn" href="javascript:history.back()">Voltar</a>
                    <input class="btn" type="submit" name="btnAtualizar" value="Atualizar" />
                </div>
            </form>
        </div>
        <%}%>
        
        <footer>
            <p>Copyright(c) 2024 - Editora IFAM.</p>
        </footer>
    </body>
</html>
