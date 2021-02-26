/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Vector2D {

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }
    
    public double getangulo(){
        return Math.asin(y/getMagnitud());
    }

    public double getMagnitud() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D getDireccion(double angulo) {
        return new Vector2D(Math.cos(angulo) * getMagnitud(), Math.sin(angulo) * getMagnitud());
    }

    public Vector2D getDireccion2(double angulo) {
        return new Vector2D(Math.sin(angulo) * getMagnitud(), Math.cos(angulo) * getMagnitud());
    }

    public Vector2D anadir(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D restarV(Vector2D v) {
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    public Vector2D escalar(double valor) {
        return new Vector2D(x * valor, y * valor);
    }

    public void setX(double x) {
        this.x = x;
    }

    public Vector2D limitar(double valor) {
        /* if(x > valor){
            x = valor;
        }
        if(x < -valor){
            x = -valor;
        }
        if(y>valor){
            y = valor;
        }
        if(y<-valor){
            y = -valor;
        }*/

        if (getMagnitud()> valor) {
            return this.normalizar().escalar(valor);
        }
        return this;
    }

    public Vector2D normalizar() {
        return new Vector2D(x / getMagnitud(), y / getMagnitud());
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double x, y;
}
