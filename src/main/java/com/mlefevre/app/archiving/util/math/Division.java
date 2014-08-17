package com.mlefevre.app.archiving.util.math;


public class Division {

    private int dividend;
    private int divisor;

    private int quotient;
    private int rest;


    public Division(int dividend, int divisor) {
        if(divisor == 0) {
            throw new IllegalArgumentException("Divisor must not be null");
        }
        this.dividend = dividend;
        this.divisor = divisor;
    }


    public int getQuotient() {
        return quotient;
    }

    public int getRest() {
        return rest;
    }


    public void calculate() {
        this.quotient = this.dividend / this.divisor;
        this.rest = this.dividend % this.divisor;
    }

}
