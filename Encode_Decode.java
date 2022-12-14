import java.util.*;

public class Encode_Decode {
    static String encodedString = "";
    static char[] ar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+',
            ',', '-', '.', '/' };
    static char[] ori = ar.clone();
    static char offChar;
    static int newOFF = 0; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Offset: ");
        char in = sc.next().charAt(0);
        Scanner sx = new Scanner(System.in);
        System.out.println("Words to encode: ");
        String str = sx.nextLine().toUpperCase();
        int offset = 0;
        for (int i = 0; i < 44; i++) {
            if (in == ar[i]) {
                offset = i;
            }
        }
        offChar = ar[offset];
        newOFF = offset;
        Encode_Decode ob = new Encode_Decode();
        System.out.println("Encoded String:= " + in + ob.encode(str));
        System.out.println("Decoded String:= "+ob.decode(encodedString));
    }

    public String decode(String encodedString) {
        char[] encodedArray = encodedString.toCharArray();
        String c = "";
        int f = 0;

        for (int i = 0; i < encodedArray.length; i++) {
            for (int k = 0; k < ar.length; k++) {
                if (encodedArray[i] == ar[k]) {
                    f = 1;
                    c = c + ar[(k + newOFF)%ar.length];
                    break;
                }
                if (encodedArray[i] == ' ') {
                    c = c + " ";
                    break;
                }
            }
            if (f == 0) {
                c = c + encodedArray[i];
            }
            f = 0;
        }
        return c;
    }

    public String encode(String plaintext) {
        char[] res = plaintext.toCharArray(); 
        for (int j = 0; j < newOFF; j++) {
            char tem = ar[ar.length - 1];
            int l = ar.length;
            int last = ar.length - 1;
            for (int i = l - 2; i >= 0; i--) { 
                ar[last] = ar[i];
                last--; 
            }
            ar[0] = tem;
        }
        int[] position = new int[res.length]; 
        int pos = 0; 
        int f = 0; 
        for (int k = 0; k < res.length; k++) {
            for (int i = 0; i < ori.length; i++) {
                if (res[k] == ori[i]) { 
                    f = 1;
                    position[pos] = i; 
                    pos++; 
                    break;
                }
            }
            if (f == 0 && res[k] != ' ') {
                position[pos] = (int) res[k];
                pos++;
            }
            f = 0;
            if (res[k] == ' ') {
                position[pos] = 44;
                pos++;
            }
        }

        String tr = "";
        for (int i = 0; i < position.length; i++) {
            if (position[i] == 44) {
                tr = tr + " ";
            }
            else if (position[i] >= 0 && position[i] <= 43) {
                tr = tr + ar[position[i]];
            }
        }
        encodedString = encodedString + tr; 
        return tr;
    }
}