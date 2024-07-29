/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author silva
 */
public class C_Medicos {
    private String nomeMedico,
            crm;
    private int codigomedico,
            codespecialidade;

    public C_Medicos() {
        this.nomeMedico = "";
        this.crm = "";
        this.codespecialidade = 0;
    }
    
    

    public C_Medicos(String nomeMedico, String crm, int codespecialidade) {
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.codespecialidade = codespecialidade;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public int getCodespecialidade() {
        return codespecialidade;
    }

    public int getCodigomedico() {
        return codigomedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public void setCodespecialidade(int codespecialidade) {
        this.codespecialidade = codespecialidade;
    }

    public void setCodigomedico(int codigomedico) {
        this.codigomedico = codigomedico;
    }

    
    
}
