import java.util.*;
import java.lang.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

public class GaussJordanMatrix {
    private final int N;
    public static int col;
    private double[][] a;
    private static double[] Result;
    private static Scanner in;
    private static int optMenu,optInput; 

    public static void chosenMenu(int opt){
        //I.S opt terdefinisi dalam rentang 1-7
        //F.S Mencetak pesan sesuai pilihan menu
        switch(opt){
            case 1: System.out.println(" > You chose: Simple Linear Equation");break;
            case 2: System.out.println(" > You chose: Hilbert Matrices");break;
            case 3: System.out.println(" > You chose: Company Finance Problem");break;
            case 4: System.out.println(" > You chose: Electric Circuits");break;
            case 5: System.out.println(" > You chose: Interpolation");break;
            case 6: System.out.println(" > You chose: Function");break;
            case 7: System.out.println(" > Good bye!");break;
        }
    }

    public static void chooseInput(int optMenu){
        //I.S. optMenu terdefinisi
        //F.S. mengarahkan pengguna ke menu yang dipilihnya
        in= new Scanner(System.in);
        if(optMenu != 7){
            System.out.println(" > Choose input from: ");
            System.out.println(" > 1. Keyboard Input ");
            System.out.println(" > 2. File input");
            System.out.printf(" > Choose: ");
            optInput= in.nextInt();
        }
        switch(optMenu){
            case 1: switch(optInput){
                        case 1: System.out.println(" > You chose: Keyboard Input");
                                matrixKeyboardInput();
                                break;
                        case 2: System.out.println(" > You chose: File input");
                                matrixFileInput();
                                break;
                    };break;
            case 2:switch(optInput){
                        case 1: System.out.println(" > You chose: Keyboard Input");
                                hilbertMatrixKeyboardInput();
                                break;
                        case 2: System.out.println(" > You chose: File input");
                                hilbertMatrixFileInput();
                                break;
                    };break;
             case 3:switch(optInput){
                        case 1: System.out.println(" > You chose: Keyboard Input");
                                companiesTaxes(optInput);
                                break;
                        case 2: System.out.println(" > You chose: File input");
                                companiesTaxes(optInput);
                                break;
                    };break;
            case 4:switch(optInput){
                        case 1: System.out.println(" > You chose: Keyboard Input");
                                electricalCircuit(optInput);
                                break;
                        case 2: System.out.println(" > You chose: File input");
                                electricalCircuit(optInput);
                                break;
                    };break;
             case 5:switch(optInput){
                        case 1: System.out.println(" > You chose: Keyboard Input");
                                interpolationKeyboardInput();
                                break;
                        case 2: System.out.println(" > You chose: File input");
                                interpolationFileInput();
                                break;
                    };break;
            case 6:switch(optInput){
                        case 1: System.out.println(" > You chose: Keyboard Input");
                                interpolationFunction();
                                break;
                        case 2: System.out.println(" > You chose: File input");
                                interpolationFileFunction();
                                break;
                    };break;
            case 7:break;
        }
    }

    public static void mainMenu(){
        //Menu utama dari program
        in = new Scanner(System.in);
        optMenu=8;
        while(optMenu != 7){
            System.out.println(" ==========GAUSS-JORDAN CALCULATOR==========");
            System.out.println("|            ALGEBRAIC GEOMETRY             |");
            System.out.println("|                 -IF2123-                  |");
            System.out.println("|          Ricky Kennedy- 13516105          |");
            System.out.println("|       Daniel Ryan Levyson - 13516132      |");
            System.out.println("|        Christian Wibisono - 13516147      |");
            System.out.println(" ===========================================");
            System.out.println(" > Main Menu");
            System.out.println(" > 1. Simple Linear Equation");
            System.out.println(" > 2. Hilbert Matrices");
            System.out.println(" > 3. Company Finance Problem");
            System.out.println(" > 4. Electric Circuits");
            System.out.println(" > 5. Interpolation");
            System.out.println(" > 6. Function");
            System.out.println(" > 7. Exit");
            System.out.printf(" > Choose : ");
            optMenu = in.nextInt();
            chosenMenu(optMenu);
            chooseInput(optMenu);
        }
    }

  
    public static void interpolationFileFunction(){
        double A[][];
        double b[];
        String fileName;
        double x,h;
        fileName = new String();
        in = new Scanner(System.in);
        System.out.printf(" > Insert file name : ");
        fileName = in.nextLine();
        BufferedReader fileReader=null;
        try{
            fileReader = new BufferedReader(new FileReader(fileName));
            String line = fileReader.readLine();
            String[] L = line.split(" ");
            col = (int) Double.parseDouble(L[0]) + 2;
            h=(Double.parseDouble(L[2])-Double.parseDouble(L[1]))/Double.parseDouble(L[0]);
            int row =(int) (Double.parseDouble(L[2])/h);
            row++;
            x=Double.parseDouble(L[1]);
            A = new double[row][col];
            b = new double[row];
            for(int i=0;i<row;i++)
            {
                for(int j=0;j<col;j++)
                {
                    A[i][j]=Math.pow(x,col-j-2);
                }
                b[i]=Math.exp(-x)/(1+Math.sqrt(x)+Math.pow(x,2));
                x+=h;
            }
            printAugmentedMatrix(A, b);
            solveInterpolation(A, b);
        } catch (IOException e) {
             System.out.println(" > File not found! ");
        } finally {
            try {
                 if (fileReader != null)
                 fileReader.close();
            } 
            catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
    }

    public static void interpolationFunction(){
        Scanner in = new Scanner (System.in);
        double x,y;
        int n;
        System.out.printf(" > Input degree of interpolation : ");n=in.nextInt();
        System.out.printf(" > Input interval : \n");
        System.out.printf(" > a : ");x= in.nextDouble(); 
        System.out.printf(" > b : ");y=in.nextDouble();
        double h=(y-x)/n; 
        int row= (int)(y/h);
        row++;
        col=n+2;
        double A[][]= new double[row][col];
        double b[]= new double[row];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                A[i][j]=Math.pow(x,col-j-2);
            }
            b[i]=Math.exp(-x)/(1+Math.sqrt(x)+Math.pow(x,2));
            x+=h;
        }
        printAugmentedMatrix(A, b);
        solveInterpolation(A, b);
    }

    public static void interpolationKeyboardInput(){
        double x;
        System.out.printf(" > Insert the degree of the curve : ");
        int row=in.nextInt();
        row++;
        col=row;
        col++;
        double A[][]= new double[row][col];
        double b[]= new double[row];
        for(int i=0;i<row;i++)
        {
            System.out.printf(" > Data #%d : %n",i+1);
            System.out.printf(" > X = "); 
            x=in.nextDouble();
            for(int j=0;j<col-1;j++)
            {
                A[i][j]=Math.pow(x,col-j-2);
            }
            System.out.printf(" > f(x) = "); 
            x=in.nextDouble();
            b[i]=x;
        }
        printAugmentedMatrix(A, b);
        solveInterpolation(A, b);
    }

    private void exportMatrixToTxt(){
        System.out.printf(" > Do you want to save it to file? (y/n) :");
        String c="y";
        in = new Scanner(System.in);
        c = in.nextLine();
        if(c.equalsIgnoreCase("y")){
            System.out.printf(" > Insert file name : ");
            String fileName = in.nextLine();
            PrintWriter printWriter=null;
            try{
                printWriter = new PrintWriter(new File(fileName));
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < col; j++) {
                        printWriter.printf("%.2f ",a[i][j+1]);
                    }
                    printWriter.printf("%.2f\n", a[i][0]);
                }
            } catch(IOException e) {
                    System.out.println(" > File not found! ");
            } finally{
                    printWriter.close ();
                }
            }
    }

    public static void interpolationFileInput(){
        double A[][];
        double b[];
        String fileName;
        fileName = new String();
        in = new Scanner(System.in);
        System.out.printf(" > Insert file name : ");
        fileName = in.nextLine();
        BufferedReader fileReader=null;
        try{
            fileReader = new BufferedReader(new FileReader(fileName));
            String line = fileReader.readLine();
            String[] x = line.split(" ");
            int row = x.length;
            col = row;
            A = new double[row][col];
            b = new double[row];
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++)
                {
                    A[i][j]=Math.pow(Double.parseDouble(x[i]),col-j-1);
                }
                System.out.println();
            }
            line = fileReader.readLine();
            x = line.split(" ");
            for(int j=0;j<col;j++)
            {
                b[j]=Double.parseDouble(x[j]);
            }
            col++;
            printAugmentedMatrix(A, b);
            solveInterpolation(A, b);
        } catch (IOException e) {
             System.out.println(" > File not found! ");
        } finally {
            try {
                 if (fileReader != null)
                 fileReader.close();
            } 
            catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
    }

    public static void solveInterpolation(double[][] A, double [] b){
        GaussJordanMatrix gaussian = new GaussJordanMatrix(A,b);
        gaussian.show();
        gaussian.exportMatrixToTxt();
        System.out.println();
        if (gaussian.isFeasible()) {
            System.out.println(" > So the polinomial found is:");
            double[] x = gaussian.primal();
            System.out.printf(" > f(x) = ");
            for (int i = 0; i < x.length; i++) {
                if(i != 0 && x[i]>=0){
                    System.out.printf("+");
                }
                System.out.printf("%.3f",x[i]);
                if(col-i-2 !=0){
                    if(col-i-2 ==1){
                        System.out.printf("x");
                    } else {
                        System.out.printf("x^%d",col-i-2);
                    }
                }
            }
            System.out.println();
            in = new Scanner(System.in);
            double find,fx;
            System.out.printf(" > Input X to find the f(x) (enter -999 to stop): ");
            find=in.nextDouble();
            while(find !=-999)
            {
                fx=0;
                for(int j=0;j<x.length;j++)
                {
                    fx=fx+x[j]*(Math.pow(find,x.length-j-1));
                }
                System.out.printf(" > f(%.2f) = %.3f%n",find,fx);
                System.out.printf(" > Input X to find the f(x) (enter -999 to stop): ");
                find=in.nextDouble();
            }
        }
    }

    public static void matrixKeyboardInput(){
        double A[][];
        double b[];
        int row;
        in = new Scanner(System.in);
        System.out.printf(" > Insert number of equation : ");
        row = in.nextInt();
        System.out.printf(" > Insert number of variables : ");
        col = in.nextInt();
        A = new double[row][col];
        b = new double[row]; 
        col+=1;   
        System.out.println(" > Insert element of matrix : ");      
        for(int i=0;i<row;i++){
            System.out.printf(" > ");  
            for(int j=0;j<col;j++){
                if(j==col-1){
                    b[i] = in.nextDouble();
                } else {
                    A[i][j] = in.nextDouble();
                }
            }
        }
        printAugmentedMatrix(A,b);
        test(A,b);
        GaussJordanMatrix file = new GaussJordanMatrix(A,b);
        file.exportMatrixToTxt();
    }

    public static void matrixFileInput(){
        double A[][];
        int row=0;
        int i=0;
        double b[];
        String fileName;
        fileName = new String();
        in = new Scanner(System.in);
        System.out.printf(" > Insert file name : ");
        fileName = in.nextLine();
        System.out.println(" > Your file name: "+ fileName);
        BufferedReader fileReader=null;
        LineNumberReader lnr=null;
        try{
            fileReader = new BufferedReader(new FileReader(fileName));
            lnr = new LineNumberReader(new FileReader(fileName));
            lnr.skip(Long.MAX_VALUE);
            row = lnr.getLineNumber() + 1;
            String line = fileReader.readLine();
            String[] x = line.split(" ");
            col = x.length;
            A = new double[row][col];
            b = new double[row];
            while(line != null){
                String[] element = line.split(" ");
                for(int j=0;j<col;j++){
                    if(j==col-1){
                        b[i]= Double.parseDouble(element[j]);
                    } else {
                        A[i][j] = Double.parseDouble(element[j]);
                    }
                }
                line = fileReader.readLine();
                i++;
            }
            printAugmentedMatrix(A,b);
            test(A,b);
            GaussJordanMatrix file = new GaussJordanMatrix(A,b);
            file.exportMatrixToTxt();
        } catch (IOException e) {
             System.out.println(" > File not found! ");
        } finally {
            try {
                 if (fileReader != null)
                 fileReader.close();
            } 
            catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
    }

    public static void companiesTaxes(int opt){
        int row=3;
        Scanner in = new Scanner(System.in);
        col=row+1;
        double[][] A = new double[row][col];
        double[] b = new double[row];
        double revenue=0;
        if(opt ==1){
            System.out.printf(" > Input company's revenue : $ ");
            revenue= in.nextDouble();
            while (revenue < 0){
                System.out.printf("\n Wrong input, revenue must be > $0 \n");
                System.out.printf(" > Input company's revenue : $ ");
                revenue= in.nextDouble();
            }
        } else if(opt==2){
            String fileName;
            fileName = new String();
            System.out.printf(" > Insert file name : ");
            fileName = in.nextLine();
            BufferedReader fileReader=null;
            try{
                fileReader = new BufferedReader(new FileReader(fileName));
                String line = fileReader.readLine();
                String[] x = line.split(" ");
                revenue = Double.parseDouble(x[0]);
            } catch (IOException e) {
                 System.out.println(" > File not found! ");
            } finally {}
        }

        for(int i=0; i<row ; i++){
            for(int j=0; j<col-1 ;j++){
                if(i==j){
                   switch(i){
                       case 0:A[i][j] = 10/4;
                              break;
                       case 1:A[i][j] = 20;
                              break;
                       case 2:A[i][j] = 10; 
                              break;
                   }
                } else if(i<j){
                    A[i][j] = 1.00;
                }
                else A[i][j] = 0.00; 
            }
            b[i]=revenue;
        }
        printAugmentedMatrix(A,b);
        test(A,b);
        System.out.printf(" > Federal Taxes = $%.2f\n",  Result[0]);
        System.out.printf(" > Regional Taxes = $%.2f\n",  Result[1]);
        System.out.printf(" > Corporate Social Responsibility = $%.2f\n",  Result[2]);
    }

    public static void hilbertMatrixFileInput(){
        String fileName;
        fileName = new String();
        in = new Scanner(System.in);
        System.out.printf(" > Insert file name : ");
        fileName = in.nextLine();
        System.out.println(" > Your file name: "+ fileName);
        BufferedReader fileReader=null;
        try{
            fileReader = new BufferedReader(new FileReader(fileName));
            String line = fileReader.readLine();
            String[] x = line.split(" ");
            int n = Integer.parseInt(x[0]);
            constructHilbertMatrix(n);
        } catch (IOException e) {
             System.out.println(" > File not found! ");
        } finally {
            try {
                 if (fileReader != null)
                 fileReader.close();
            } 
            catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
    }

    public static void constructHilbertMatrix(int n){
        double[][] A;
        double[] b;
        int row=n;
        col=row+1;
        A = new double[row][col];
        b = new double[row];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                A[i][j]=1/((double) (i+j+1));
            }
            b[i]=1.00;
        }
        printAugmentedMatrix(A, b);
        test(A,b);
        GaussJordanMatrix file = new GaussJordanMatrix(A,b);
        file.exportMatrixToTxt();
    }

    public static void hilbertMatrixKeyboardInput(){
        in = new Scanner(System.in);
        System.out.printf(" > Hx = B, H is a Hilbert matrix%n");
        System.out.printf(" > Input n : ");
        int row=in.nextInt();
        constructHilbertMatrix(row);
    }

    public static void printAugmentedMatrix(double[][] A, double[] b){
        int N = b.length;
        System.out.println(" > Augmented Matrix :");
        for(int i=0;i<N;i++){
            System.out.printf("    ");  
            for(int j=0;j<col-1;j++){
                System.out.printf("%.2f ", A[i][j]);
            }
            System.out.printf("| %.2f%n",b[i]);
        }         
    }

    // Gauss-Jordan elimination with partial pivoting
    //Constructor
    public GaussJordanMatrix(double[][] A, double[] b) {
        N = b.length;

        a = new double[N][col+1];
        for(int i=0;i<N;i++){
            a[i][0]=b[i];
        }
        for (int i = 0; i < N; i++){
            for (int j = 1; j < col; j++){
                a[i][j] = A[i][j-1];
            }
        }
        solve();
        check();
    }

    public void solveManySolutions(){
        int[] solution = new int[21];
        boolean[] failed = new boolean[21];
        char[] v = new char[21];
        char variabel;
        boolean isEmpty;
        for(int i=N-1;i>=0;i--){
            isEmpty = false;
            failed[i]=false;
            for(int j=1;j<=col;j++){
                if(isEmpty==true && a[i][j]!=0){
                    solution[j]=-9999;
                    failed[i]=true; 
                } 
                else if(isEmpty==false && a[i][j]==1){
                    isEmpty=true;
                    solution[j]=1;
                }
            }
        }
        variabel='t';
        System.out.println(" > Let : ");
        for(int j=1;j<=col;j++){
            if(solution[j]==-9999){
                v[j] = variabel;
                System.out.println(" > x"+j+" = "+v[j]);
                variabel++;
            }
        }
        System.out.println();
        for(int j=1;j<col;j++){
        	if(solution[j]==1){
        		int i=0;
        		while(a[i][j]!=1) i++;
        		if(failed[i]){
        			System.out.printf(" > x%d = ",j);
                    if(a[i][0]!=0) System.out.printf("%.2f",a[i][0]);
                        
                    for(int k=j+1;k<col;k++){
                        if(a[i][k]!=0){
                            System.out.printf("%+.2f%c",-a[i][k],v[k]);
                        } 
                    } 
        		} else {
        			System.out.printf(" > x%d = %.2f",j,a[i][0]);
        		}		
        	}
        	else if(solution[j]==-9999){
        		System.out.printf(" > x%d = %c",j,v[j]);
        	}
        	System.out.printf("%n");
        }
    }

    public static void electricalCircuit(int opt){
        double[][] temp = new double[4][7];
        double[][] A = new double[4][6];
        double[] b = new double[4];
        int row=4;col=5;
        double r12=0,r52=0,r32=0,r65=0,r54=0,r43=0,v1=0,v6=0;
        if(opt==1){
            System.out.printf(" > Input the value of the resistor : %n");
            System.out.printf(" > 1. r12 : "); r12 = in.nextDouble();
            System.out.printf(" > 2. r52 : "); r52 = in.nextDouble();
            System.out.printf(" > 3. r32 : "); r32 = in.nextDouble();
            System.out.printf(" > 4. r65 : "); r65 = in.nextDouble();
            System.out.printf(" > 5. r54 : "); r54 = in.nextDouble();
            System.out.printf(" > 6. r43 : "); r43 = in.nextDouble();
            System.out.printf(" > Masukan nilai tegangan : %n");
            System.out.printf(" > 1. v1 : "); v1 = in.nextDouble();
            System.out.printf(" > 2. v6 : "); v6 = in.nextDouble();
        } else {
            String fileName;
            fileName = new String();
            in = new Scanner(System.in);
            System.out.printf(" > Insert file name : ");
            fileName = in.nextLine();
            BufferedReader fileReader=null;
            try{
                fileReader = new BufferedReader(new FileReader(fileName));
                String line = fileReader.readLine();
                String[] L = line.split(" ");
                r12= Double.parseDouble(L[0]);
                r52= Double.parseDouble(L[1]);
                r32= Double.parseDouble(L[2]);
                r65= Double.parseDouble(L[3]);
                r54= Double.parseDouble(L[4]);
                r43= Double.parseDouble(L[5]);
                line = fileReader.readLine();
                L = line.split(" ");
                v1= Double.parseDouble(L[0]);
                v6= Double.parseDouble(L[1]);
            }catch(IOException e){}finally{}
        }
        for(int i=1;i<=3;i++)
        {
            for(int j=1;j<=6;j++)
            {
                temp[i][j] = 0;
            }
        }
        temp[1][0]=1/r12; 
        temp[1][1]= 1; 
        temp[1][2]= -1; 
        for(int j=1;j<=6;j++){
            temp[1][j]*=temp[1][0];
        }
        temp[2][0]=1/r52;
        temp[2][2]= -1; 
        temp[2][5]= 1; 
        for(int j=1;j<=6;j++){
            temp[2][j]*=temp[2][0];
        }
        temp[3][0]=1/r32;
        temp[3][2]= -1;
        temp[3][3]= 1; 
        for(int j=1;j<=6;j++){
            temp[3][j]*=temp[3][0];
        }

        b[0] = 0;
        for(int i=1;i<=3;i++)
        {
            b[0] = b[0] - (temp[i][1]*v1) - (temp[i][6]*v6);
        }
        for(int k=0;k<4;k++)
        {
            A[0][k]=0;
            for(int j=1;j<=3;j++)
            {
                A[0][k] = A[0][k] + temp[j][k+2];
            }
        }

        for(int i=1;i<=3;i++)
        {
            for(int j=1;j<=6;j++)
            {
                temp[i][j] = 0;
            }
        }
        temp[1][0]=1/r65;
        temp[1][5]= -1;
        temp[1][6]= 1;
        for(int j=1;j<=6;j++){
            temp[1][j]*=temp[1][0];
        }
        temp[2][0]=1/r52;
        temp[2][2]= 1;
        temp[2][5]= -1;
        for(int j=1;j<=6;j++){
            temp[2][j]*=temp[2][0];
        }
        temp[3][0]=1/r54;
        temp[3][4]= 1; 
        temp[3][5]= -1; 
        for(int j=1;j<=6;j++){
            temp[3][j]*=temp[3][0];
        }

        b[1] = 0;
        for(int i=1;i<=3;i++){
            b[1] = b[1] - (temp[i][1]*v1) - (temp[i][6]*v6);
        }
        for(int k=0;k<4;k++){
            A[1][k]=0;
            for(int j=1;j<=3;j++){
                A[1][k] = A[1][k] + temp[j][k+2];
            }
        }
        for(int i=1;i<=3;i++){
            for(int j=1;j<=6;j++){
                temp[i][j] = 0;
            }
        }
        temp[1][0]=1/r43;
        temp[1][3]= -1;
        temp[1][4]= 1; 
        for(int j=1;j<=6;j++){
            temp[1][j]*=temp[1][0];
        }
        temp[2][0]=1/r32;
        temp[2][2]= 1; 
        temp[2][3]= -1; 
        for(int j=1;j<=6;j++){
            temp[2][j]*=temp[2][0];
        }

        b[2] = 0;
        for(int i=1;i<=3;i++){
            b[2] = b[2] - (temp[i][1]*v1) - (temp[i][6]*v6);
        }
        for(int k=0;k<4;k++){
            A[2][k]=0;
            for(int j=1;j<=3;j++){
                A[2][k] = A[2][k] + temp[j][k+2];
            }
        }
        for(int i=1;i<=3;i++){
            for(int j=1;j<=6;j++){
                temp[i][j] = 0;
            }
        }
        temp[1][0]=1/r54;
        temp[1][4]= -1;
        temp[1][5]= 1; 
        for(int j=1;j<=6;j++){
            temp[1][j]*=temp[1][0];
        }
        temp[2][0]=1/r43;
        temp[2][3]= 1;
        temp[2][4]= -1;
        for(int j=1;j<=6;j++){
            temp[2][j]*=temp[2][0];
        }
        b[3] = 0;
        for(int i=1;i<=3;i++){
            b[3]= b[3] - (temp[i][1]*v1) - (temp[i][6]*v6);           
        }
        for(int k=0;k<4;k++){
            A[3][k]=0;
            for(int j=1;j<=3;j++){
                A[3][k] = A[3][k] + temp[j][k+2];
            }
       }
       printAugmentedMatrix(A,b);
       test(A,b);
       double i12=(v1-Result[0])/r12;
       double i52=(Result[3]-Result[0])/r52;
       double i32=(Result[1]-Result[0])/r32; 
       double i65=(v6-Result[3])/r65;
       double i54=(Result[3]-Result[2])/r54;
       double i43=(Result[2]-Result[1])/r43;
       System.out.printf("\n > List of Voltages \n");
       System.out.printf("   v1 = %.2f\n",v1);
       for(int i=0;i<row;i++){
            System.out.printf("   v%d = %.2f \n",i+2,Result[i]);
       }
       System.out.printf("   v6 = %.2f\n\n",v6);

       System.out.printf(" > List of Current \n");
       System.out.printf("   i12 = %.2f\n",i12);
       System.out.printf("   i32 = %.2f\n",i32);
       System.out.printf("   i52 = %.2f\n",i52);
       System.out.printf("   i43 = %.2f\n",i43);
       System.out.printf("   i54 = %.2f\n",i54);
       System.out.printf("   i65 = %.2f\n",i65);
    }	


    public void solve(){
        int idx=0,i,j,k;
        double pivot,temp,pembagi;
        double pengurang;
	    for(j=1;j<col&&j<=N;j++){
	        pivot=Math.abs(a[idx][j]);
	        int idxpivot=idx;
	        for(i=idx;i<N;i++){
	            if(pivot<Math.abs(a[i][j])){
	                idxpivot=i;
	                pivot=Math.abs(a[i][j]);
	            }
	        }
	        if(pivot>0){
	       		if(idxpivot!=idx){
	                for(k=0;k<col;k++){
	                    temp=a[idxpivot][k];
	                    a[idxpivot][k]=a[idx][k];
	                    a[idx][k]=temp;
	                }
	            }
	            pembagi=a[idx][j];
	            for(int z=0;z<col;z++){
	                a[idx][z]/=pembagi;
                } 
	            for(i=0;i<N;i++){
	            	if(i!=idx){
	                 	pengurang=a[i][j];
	                    for(k=0;k<col;k++){
	                        a[i][k]=a[i][k]-(pengurang*a[idx][k]);
	                    }
	                }
	            }
	       	    idx=idx+1;
	        }        
	    } 
    }

    //Check the type of the solutions
    public int check(){
        int kol;
        kol=col-1;
		if(N >= kol){
			if(a[kol-1][kol] != 0){
                return 1;
			}else if((a[kol-1][kol]== 0)&&(a[kol-1][0])!=0){
                   return 2;
				  }else{
                    return 3;
				  }
		}
		else{
			boolean bNotSol=true;
			int j=0;
			while((j<kol)&&(bNotSol)){
				if(a[N-1][j]!=0){
					bNotSol=false;
				}
				j++;
			}if(!bNotSol){
                return 3;
			}else{
				if(a[N-1][0]==0){
                    return 3;

				}else{
                    return 2;
				}
			}
		}
	}
       
   
    // Solution extractor
    public double[] primal() {
        double[] x = new double[N];
        for (int i = 0; i < N; i++) {
                x[i] = a[i][0];
        }
        return x;
    }

    // Check if the system has a unique solutions
    public boolean isFeasible() {
        return primal() != null;
    }

    private void show() {
        System.out.println(" > Reduced Row Echelon Matrix :");
        for (int i = 0; i < N; i++) {
            System.out.printf("    "); 
            for (int j = 1; j < col; j++) {
                System.out.printf("%.2f ", a[i][j]);
            }
            System.out.printf("| %.2f\n", a[i][0]);
        }
    }


    //Solve and print to screen
    public static void test(double[][] A, double[] b) {
        GaussJordanMatrix gaussian = new GaussJordanMatrix(A, b);
        gaussian.show();
        if (gaussian.check()==1) {
            System.out.println(" > Solution to Ax = b");
            double[] x = gaussian.primal();
            Result = gaussian.primal();
            for (int i = 0; i < x.length; i++) {
                System.out.printf("    ");
                System.out.printf("x%d = %.2f\n",i+1,x[i]);
            }
        }
        else if(gaussian.check()==3){
            System.out.println(" > System have many solutions");
            gaussian.solveManySolutions();
        } else {
            System.out.println(" > System doesn't have any solution");
        }
    }

    public static void main(String[] args) {
        try                { mainMenu();          }
        catch(Exception e) { e.printStackTrace(); }
        System.out.println("---------------------------------------");
    }
}