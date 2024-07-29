package banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.C_Medicos;

/**
 *
 * @author silva
 */
public class Medicos {
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;
    
    public void configurarConexao(Connection conBanco){
        this.conBanco = conBanco;
    }
    
    public boolean inserirRegistro(C_Medicos medicos){
        String strComandoSQL = "INSERT INTO medicos(nome_medico,CRM,codigo_especialidade)" +
                "VALUES(?,?,?)";
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setString(1, medicos.getNomeMedico());
            psComando.setString(2, medicos.getCrm());
            psComando.setInt(3, medicos.getCodespecialidade());
            psComando.executeUpdate();
            return true;
        }catch(SQLException erro){
           erro.printStackTrace();
           return false;
        }
    }
    
    public int localizarRegistro(String strMedico){
        int intCodigoMedico = 0;
        System.out.println(strMedico + "TESTE2");
        String strComandoSQL = "SELECT codigo_medico FROM medicos WHERE CRM = ?";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setString(1, strMedico);
            System.out.println(psComando + "TESTE3");
            rsRegistros = psComando.executeQuery();
            System.out.println(rsRegistros + "TESTE4");
            if (rsRegistros.next()){
                System.out.println("Entrou");
                System.out.println(rsRegistros + "TESTE5");
                intCodigoMedico = rsRegistros.getInt("codigo_medico");
                System.out.println(intCodigoMedico + "TESTE6");
            }
        } catch(SQLException erro){
            erro.printStackTrace();
        }
        return intCodigoMedico;
    }
    
    public ResultSet lerRegistro (int intCodigoMedico){
        String strComandoSQL = "SELECT * FROM medicos where codigo_medico = ?";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setInt(1, intCodigoMedico);
            rsRegistros = psComando.executeQuery();
            if (rsRegistros.next()) {
                return rsRegistros;
            }
        } catch(SQLException erro){
            erro.printStackTrace();
        }
        return null;
    }
    
    public boolean alterarRegistro(C_Medicos medicos){
        String strComandoSQL = "UPDATE medicos set nome_medico = ?, CRM = ?, codigo_especialidade = ?";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setString(1, medicos.getNomeMedico());
            psComando.setString(2, medicos.getCrm());
            psComando.setInt(3, medicos.getCodespecialidade());
            psComando.executeUpdate();
            return true;
        }catch(SQLException erro){
           erro.printStackTrace();
           return false;
        }
    }
    
    public boolean excluirRegistro(int intCodigoMedico){
        String strComandoSQL = "DELETE FROM medicos WHERE codigo_medico = ?";
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setInt(1, intCodigoMedico);
            psComando.executeUpdate();
            return true;
        } catch(SQLException erro){
            erro.printStackTrace();
            return false;
        }
    }
}
