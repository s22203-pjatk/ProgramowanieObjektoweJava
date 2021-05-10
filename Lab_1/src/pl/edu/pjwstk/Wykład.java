package pl.edu.pjwstk;

public class Wykład {
    public static void main(String[] args) {

        double x = 9.0/5;       //jesli chcemy wynik zmiennoprzcinkowy to musimy dac liczbe zmienno przecinkowa np. float/double
        System.out.println(x);

        double y = 9;   //float tez moze byc
        double z = y/5;
        System.out.println(z);
/////////////////////////////////////////////////////////////////////////////////////////////////
        String ciagznakow = "Ala ma " + z + " kotów";
        System.out.println(ciagznakow);
//%s-string %d-integer %f-floating point
        String ciagznakow2 = String.format("Ala ma %s kotów",z);
        System.out.println(ciagznakow2);
////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println(Math.pow(3,2));//3 do potęgi 2 , wynik w double
        System.out.println(Math.sqrt(4));//pierwiastek z 4 o podstawie 2
//<0.0 , 1)
        System.out.println(Math.random());

//<0.0 , 5)
        System.out.println(Math.random()*5);

//<0 , 5)
        System.out.println((int)(Math.random()*3));

    }
}
