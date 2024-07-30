<%@page import="java.sql.ResultSet"%>
<%@page import="model.C_Usuarios"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="usuario" scope="page" class="banco_dados.Usuarios"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>SGC - Versão 1.0</title>
    <link href="css/editarusuario.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%
        ResultSet rsRegistro;
        boolean blnConectado;

        C_Usuarios Usuario = new C_Usuarios();
        int intCodigoUsuario = Integer.parseInt(request.getParameter("codigo_usuario"));
        blnConectado = false;

        if (conexao.abrirConexao()) {
            usuario.configurarConexao(conexao.obterConexao());

            rsRegistro = usuario.lerRegistro(intCodigoUsuario);

            Usuario.setIdUsuario(rsRegistro.getString("Identificacao_Usuario"));
            Usuario.setSenhaAcesso(rsRegistro.getString("Senha_Acesso"));
            Usuario.setCadastroFuncionario(rsRegistro.getString("Cadastro_Funcionario"));
            Usuario.setCadastroUsuario(rsRegistro.getString("Cadastro_Usuario"));
            Usuario.setCadastroPaciente(rsRegistro.getString("Cadastro_Paciente"));
            Usuario.setCadastroEspecialidade(rsRegistro.getString("Cadastro_Especialidade"));
            Usuario.setCadastroMedico(rsRegistro.getString("Cadastro_Medico"));
            Usuario.setCadastroConvenio(rsRegistro.getString("Cadastro_Convenio"));
            Usuario.setAgendamentoConsulta(rsRegistro.getString("Agendamento_Consulta"));
            Usuario.setCancelamentoConsulta(rsRegistro.getString("Cancelamento_Consulta"));
            Usuario.setModuloAdministrativo(rsRegistro.getString("Modulo_Administrativo"));
            Usuario.setModuloAgendamento(rsRegistro.getString("Modulo_Agendamento"));
            Usuario.setModuloAtendimento(rsRegistro.getString("Modulo_Atendimento"));
            Usuario.setCodigoUsuario(intCodigoUsuario);

            conexao.fecharConexao();

            blnConectado = true;
        } else {
            out.println("<p>Falha na conexão com o banco de dados!</p>");
        }
    %>

    <% if (blnConectado) { %>
        <h1>SGC - Sistema de Gestão de Clínicas 1.0</h1>
        <h2>Cadastro de Funcionários - Edição</h2>

        <form name="formEditaUsuario" method="post" action="AtualizarUsuario" target="_parent">
            <p>Nome do usuário: <input type="text" name="txtNomeUsuario" size="20" maxlength="20" value="<%=Usuario.getIdUsuario()%>"/></p>

            <p>
                <input name="chkAdministrativo" type="checkbox" value="ModuloAdministrativo" <%= Usuario.getModuloAdministrativo().equals("S") ? "checked" : "" %> />
                Módulo administrativo
            </p>
            <p>
                <input name="chkFuncionario" type="checkbox" value="Funcionario" <%= Usuario.getCadastroFuncionario().equals("S") ? "checked" : "" %> />
                Cadastro de funcionários
            </p>
            <p>
                <input name="chkUsuario" type="checkbox" value="Usuario" <%= Usuario.getCadastroUsuario().equals("S") ? "checked" : "" %> />
                Cadastro de usuários
            </p>
            <p>
                <input name="chkEspecialidade" type="checkbox" value="Especialidade" <%= Usuario.getCadastroEspecialidade().equals("S") ? "checked" : "" %> />
                Cadastro de especialidades
            </p>
            <p>
                <input name="chkMedico" type="checkbox" value="Medico" <%= Usuario.getCadastroMedico().equals("S") ? "checked" : "" %> />
                Cadastro de médicos
            </p>
            <p>
                <input name="chkConvenio" type="checkbox" value="Convenio" <%= Usuario.getCadastroConvenio().equals("S") ? "checked" : "" %> />
                Cadastro de convênios
            </p>
            <p>
                <input name="chkAgendamento" type="checkbox" value="ModuloAgendamento" <%= Usuario.getModuloAgendamento().equals("S") ? "checked" : "" %> />
                Módulo de agendamento
            </p>
            <p>
                <input name="chkPaciente" type="checkbox" value="Paciente" <%= Usuario.getCadastroPaciente().equals("S") ? "checked" : "" %> />
                Cadastro de pacientes
            </p>
            <p>
                <input name="chkAgendarConsulta" type="checkbox" value="AgendarConsulta" <%= Usuario.getAgendamentoConsulta().equals("S") ? "checked" : "" %> />
                Agendamento de consulta
            </p>
            <p>
                <input name="chkCancelarConsulta" type="checkbox" value="CancelarConsulta" <%= Usuario.getCancelamentoConsulta().equals("S") ? "checked" : "" %> />
                Cancelamento de consulta
            </p>
            <p>
                <input name="chkAtendimento" type="checkbox" value="ModuloAtendimento" <%= Usuario.getModuloAtendimento().equals("S") ? "checked" : "" %> />
                Módulo de atendimento médico
            </p>

            <p>
                <input type="hidden" name="codigo_usuario" value="<%=intCodigoUsuario%>">
                <input type="hidden" name="senha_acesso" value="<%=Usuario.getSenhaAcesso()%>">
            </p>

            <div class="action-buttons">
                <a href="javascript:history.back()">Voltar</a>
                <input type="submit" name="btnAtualizar" value="Atualizar" />
            </div>
        </form>

        <footer>
            Copyright(c) 2024 - Editora IFAM.
        </footer>
    <% } %>
</body>
</html>
