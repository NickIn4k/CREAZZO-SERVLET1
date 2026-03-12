import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
        int index;
        do{
            System.out.println("""
            \nCosa vuoi fare?
              1. Ottieni tutti i pokemon dell'api
              2. Ottieni un pokemon tramite nome
              3. Ottieni un pokemon tramite id
              4. Esci
            """);

            index = sc.nextInt();
            sc.nextLine();
            try {
                switch (index) {
                    case 1:
                        System.out.println(client.getAllPokemon());
                        break;
                    case 2:
                        System.out.println("Inserisci il nome del pokemon");
                        String name = sc.nextLine();
                        System.out.println(client.getPokemonByName(name));
                        break;
                    case 3:
                        System.out.println("Inserisci l'id del pokemon");
                        int id = sc.nextInt();
                        System.out.println(client.getPokemonById(id));
                        break;
                    default:
                        System.out.println("Scelta non valida");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while(index != 4);
    }
}