package Models;

public class Payroll {

    private int id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double sueldo_bruto;
    private double horas_ext;
    private double precio_hora_ext;
    private double pago_hora_ext;
    private double bono_transporte;
    private double seguro_medico;
    private double adelanto_sueldo;
    private double sueldo_neto;
    private double afp;
    private double pension;    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo_bruto() {
        return sueldo_bruto;
    }

    public void setSueldo_bruto(double sueldo_bruto) {
        this.sueldo_bruto = sueldo_bruto;
    }

    public double getHoras_ext() {
        return horas_ext;
    }

    public void setHoras_ext(double horas_ext) {
        this.horas_ext = horas_ext;
    }

    public double getPrecio_hora_ext() {
        return precio_hora_ext;
    }

    public void setPrecio_hora_ext(double precio_hora_ext) {
        this.precio_hora_ext = precio_hora_ext;
    }

    public double getPago_hora_ext() {
        return pago_hora_ext;
    }

    public void setPago_hora_ext(double pago_hora_ext) {
        this.pago_hora_ext = pago_hora_ext;
    }

    public double getBono_transporte() {
        return bono_transporte;
    }

    public void setBono_transporte(double bono_transporte) {
        this.bono_transporte = bono_transporte;
    }

    public double getSeguro_medico() {
        return seguro_medico;
    }

    public void setSeguro_medico(double seguro_medico) {
        this.seguro_medico = seguro_medico;
    }

    public double getAdelanto_sueldo() {
        return adelanto_sueldo;
    }

    public void setAdelanto_sueldo(double adelanto_sueldo) {
        this.adelanto_sueldo = adelanto_sueldo;
    }

    public double getSueldo_neto() {
        return sueldo_neto;
    }

    public double getAfp() {
        return afp;
    }

    public void setAfp(double afp) {
        this.afp = afp;
    }

    public void setSueldo_neto(double sueldo_neto) {
        this.sueldo_neto = sueldo_neto;
    }

    public void calcularSueldoNeto() {
        this.sueldo_neto = sueldo_bruto - pago_hora_ext + bono_transporte - pension - afp - seguro_medico - adelanto_sueldo;
    }

    public void calcularAfp() {
        this.afp = ((2.87 * sueldo_bruto) / 100);
        this.pension = sueldo_bruto - afp;
    }

    public void calcularSeguroMedico() {
        this.seguro_medico = ((3.04 * sueldo_bruto) / 100);
    }

    public void calcularHorasExtra(double horasTrabajadas) {
        this.pago_hora_ext = horasTrabajadas * 300;
    }

    public void Calcularsueldoneto() {
        this.sueldo_neto = sueldo_bruto - afp + pago_hora_ext + bono_transporte - adelanto_sueldo;

    }

}
