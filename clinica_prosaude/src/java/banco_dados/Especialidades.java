package banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.C_Especialidades;


public class Especialidades {
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;
    
    public void configurarConexao(Connection conBanco){
        this.conBanco = conBanco;
    }
    public boolean inserirRegistro(C_Especialidades especialidade){
        String strComandoSQL = "INSERT INTO especialidades(descricao_especialidade)"+
                "VALUES(?)";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setString(1, especialidade.getDescespecialidade());
            psComando.executeUpdate();
            return true;
        } catch(SQLException erro){
            erro.printStackTrace();
            return false;
        }
    }
    public int localizarRegistro(String strEspecialidade){
        int intCodigoEspecialidade = 0;
            String strComandoSQL = "SELECT codigo_especialidade FROM especialidades "+
                    "WHERE descricao_especialidade = ? ";
           try{
               psComando = conBanco.prepareStatement(strComandoSQL);
               psComando.setString(1, strEspecialidade);
               rsRegistros = psComando.executeQuery();
               if (rsRegistros.next()){
                   intCodigoEspecialidade = rsRegistros.getInt("codigo_especialidade");
               }
           } catch(SQLException erro){
               erro.printStackTrace();
           }
           return intCodigoEspecialidade;
    }
    
    public ResultSet lerRegistro(int intCodigoEspecialidade){
        String strComandoSQL = "SELECT * FROM especialidades WHERE codigo_especialidade = ?";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setInt(1, intCodigoEspecialidade);
            rsRegistros = psComando.executeQuery();
            if (rsRegistros.next()) {
                return rsRegistros;
            }
        } catch(SQLException erro){
            erro.printStackTrace();
        }
        return null;
    }
    
    public boolean alterarRegistro (C_Especialidades especialidade){
        String strComandoSQL = "Updade especialidades set descricao_especialidade = ?";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setString(1, especialidade.getDescespecialidade());
            psComando.executeUpdate();
            return true;
        } catch(SQLException erro){
            erro.printStackTrace();
            return false;
        }
    } 
    
    public boolean excluirRegistro(int intCodigoEspecialidade){
        String strComandoSQL = "DELETE FROM especialidades where codigo_especialidade = ?";
        
        try{
            psComando = conBanco.prepareStatement(strComandoSQL);
            psComando.setInt(1, intCodigoEspecialidade);
            psComando.executeUpdate();
            return true;
        } catch(SQLException erro){
            erro.printStackTrace();
            return false;
        }
    }
            
}
