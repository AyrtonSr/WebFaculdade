/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author silva
 */
public class C_Especialidades {
    private String descespecialidade;
    private int codigoespecialidade;

    public C_Especialidades() {
        this.descespecialidade = "";
    }
    

    public C_Especialidades(String descespecialidade, int codigoespecialidade) {
        this.descespecialidade = descespecialidade;
        this.codigoespecialidade = codigoespecialidade;
    }

    public String getDescespecialidade() {
        return descespecialidade;
    }

    public int getCodigoespecialidade() {
        return codigoespecialidade;
    }

    public void setDescespecialidade(String descespecialidade) {
        this.descespecialidade = descespecialidade;
    }

    public void setCodigoespecialidade(int codigoespecialidade) {
        this.codigoespecialidade = codigoespecialidade;
    }

}
