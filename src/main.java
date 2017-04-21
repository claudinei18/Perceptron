import java.util.Random;
import java.util.Scanner;

/**
 * Created by claudinei on 21/04/17.
 */
public class main {

    public static int[] readVet(String msg){
        Scanner keyboard = new Scanner(System.in);
        System.out.println(msg);
        String in = keyboard.nextLine();
        String[] vet = in.split(" ");
        int[] vetInt = new int[vet.length];
        for(int i = 0; i < vetInt.length; i++){
            vetInt[i] = Integer.parseInt(vet[i]);
        }
        return vetInt;
    }


    public static void main(String[] args){
//        int[] vet = readVet("Digite o vetor de inicialização");
        int[][] vet = {{0,0},{0,1},{1,0},{1,1}};
        int[] resp = readVet("Digite o vetor resposta");
        Perceptron p = new Perceptron();
        p.Train(vet, resp, 0.001, 0.3, 200);

        while(true){
            int[] test = readVet("Digite um vetor para teste");
            System.out.println(p.funcaoLimiar(test));
        }
    }
}

class Perceptron{
    double[] w;
    double limite;

    public void Train(int[][] x, int[] y, double limite, double N, double epocas){
        this.limite = limite;
        int n = x[0].length;
        int p = y.length;

        w = new double[n];

        Random r = new Random();
        //initialize weights
        for(int i=0;i<n;i++){
            w[i] = r.nextDouble();
        }

        for(int i = 0; i < epocas; i++){
            int totalError = 0;
            for(int j = 0; j < p; j++){
                int aux = funcaoLimiar(x[j]);
                int error = y[j] - aux;

                totalError += error;

                for(int k = 0; k < n; k++){
                    double delta = N * x[j][k] * error;
                    w[k] += delta;
                }
            }
            if(totalError == 0)
                break;
        }
    }

    public int funcaoLimiar(int[] input) {
        double sum = 0.0;
        for (int i = 0; i < input.length; i++) {
            sum += w[i] * input[i];
        }

        if (sum > limite)
            return 1;
        else
            return 0;
    }
}