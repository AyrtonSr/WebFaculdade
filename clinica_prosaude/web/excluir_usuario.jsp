<%@page import="java.sql.ResultSet"%>
<%@page import="model.C_Usuarios"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="usuario" scope="page" class="banco_dados.Usuarios"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>SGC - Vers�o 1.0</title>
    <link href="css/excluirusuario.css" rel="stylesheet" type="text/css" />
</head>
<body class="FundoPagina">
    <%
        ResultSet rsRegistro;
        boolean blnConectado;

        C_Usuarios Usuario = new C_Usuarios();
        int intCodigoUsuario = Integer.parseInt(request.getParameter("codigo_usuario"));
        blnConectado = false;

        if (conexao.abrirConexao()) 
        {
            usuario.configurarConexao(conexao.obterConexao());

            rsRegistro = usuario.lerRegistro(intCodigoUsuario);

            Usuario.setIdUsuario(rsRegistro.getString("Identificacao_Usuario"));
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
        }
        else
            out.println("<p>Falha na conex�o com o banco de dados!</p>");
    %>

    <% if (blnConectado) {%>
    <div class="container">
        <p class="TituloAplicacao">SGC - Sistema de Gest�o de Cl�nicas 1.0</p>
        <p class="TituloPagina">Cadastro de Usu�rios - Exclus�o</p>

        <form name="formExcluiUsuario" method="post" action="ExcluirUsuario" target="_parent">
            <p>Nome do usu�rio: <%=Usuario.getIdUsuario()%></p>
            <p>M�dulo administrativo: <%=Usuario.getModuloAdministrativo()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cadastro de funcion�rios: <%=Usuario.getCadastroFuncionario()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cadastro de usu�rios: <%=Usuario.getCadastroUsuario()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cadastro de especialidades: <%=Usuario.getCadastroEspecialidade()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cadastro de m�dicos: <%=Usuario.getCadastroMedico()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cadastro de conv�nios: <%=Usuario.getCadastroConvenio()%></p>
            <p>M�dulo de agendamento: <%=Usuario.getModuloAgendamento()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cadastro de pacientes: <%=Usuario.getCadastroPaciente()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Agendamento de consulta: <%=Usuario.getAgendamentoConsulta()%></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Cancelamento de consulta: <%=Usuario.getCancelamentoConsulta()%></p>
            <p>M�dulo de atendimento m�dico: <%=Usuario.getModuloAtendimento()%></p>
            <input type="hidden" name="codigo_usuario" value="<%=intCodigoUsuario%>">
            <p>
                <span class="LinkVoltar"><a class="btn" href="javascript:history.back()">Voltar</a></span>
                <button type="submit" name="btnExcluir">Excluir</button>
            </p>
        </form>
        <p class="RodapePagina">Copyright(c) 2024 - Editora IFAM.</p>
    </div>
    <%}%>
</body>
</html>
