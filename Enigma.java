
public class Enigma{

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};


    private Rotor rotors[];
        
    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
        
    }


    public String decrypt(String message){        
        //TODO
        char[] letters = message.toCharArray();         //changes message to a character array
        String decrypted = "";                          //new string to hold decrypted message

        for(int i=0; i < letters.length; i++) {
            char c = letters[i];
            int index = rotors[2].indexOf(c);           //finds index of the current character
            char letter = rotors[1].charAt(index);          //finds the corresponding charater at the index of the middle rotor

            int secIndex = rotors[2].indexOf(letter);       //finds the index of the same letter on the outter rottor
            char found = rotors[0].charAt(secIndex);        //finds the corresponding letter on the inner rotor

            decrypted += found;     //append encrpted letter to string
            rotate();               //rotates the rotors as needed

        }

        return decrypted;
        }

    
    public String encrypt(String message){
        //TODO
        char[] letters = message.toCharArray();         //changes message to a character array
        String encrypted = "";                          //new string to hold encypted message
        
        for(int i=0; i < letters.length; i++) {
            char c = letters[i];
            int index = rotors[0].indexOf(c);           //finds index of the current character
            char letter = rotors[2].charAt(index);          //finds the corresponding charater at the index of the outter rotor

            int secIndex = rotors[1].indexOf(letter);       //finds the index of the same letter in ther middle rottor
            char found = rotors[2].charAt(secIndex);        //finds the corresponding letter on the outter rotor

            encrypted += found;     //append encrpted letter to string
            rotate();               //rotates the rotors as needed

        }
        
        return encrypted;
    }
    
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
