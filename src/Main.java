import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int tamanoTablero = 3;
        int mejorDe = 1; // por defecto 1 partida
        int victoriasJugador = 0;
        int victoriasRival = 0;

        // Lista de rivales
        String[] rivales = {"Alex", "Marta", "Sergio", "Diego", "Rosa"};
        String rival = rivales[rand.nextInt(rivales.length)];

        boolean salir = false;

        while (!salir) {

            System.out.print("Indica el número de la opcion que deseas esocger (1. Jugar | 2. Configuración | 3. Salir): ");
            int opcion = sc.nextInt();

            if (opcion == 1) {

                System.out.print("Escribe tu nombre: ");
                String jugador = sc.next();

                System.out.println("Tu rival será: " + rival);

                // INSTRUCCIÓN INICIAL
                System.out.println(
                        "INSTRUCCIÓN INICIAL: Saldrá un numero aleatorio entre el 1 y el 2. " +
                                "Si sale 1 empezará " + jugador + " y si sale 2 empezará " + rival + "."
                );

                int turnoInicial = rand.nextInt(2) + 1; // 1 o 2
                boolean tuTurno = (turnoInicial == 1);

                System.out.println("Ha salido el " + turnoInicial + " - Comienza " +
                        (tuTurno ? jugador : rival));

                // Mostrar marcador antes de empezar
                System.out.println("Marcador: " + jugador + " " + victoriasJugador +
                        " - " + victoriasRival + " " + rival);

                boolean finDelMejorDe = false;

                // CICLO DE PARTIDAS HASTA MEJOR DE X
                while (!finDelMejorDe) {

                    // TABLERO
                    char[][] tablero = new char[tamanoTablero][tamanoTablero];
                    for (int i = 0; i < tamanoTablero; i++) {
                        for (int j = 0; j < tamanoTablero; j++) {
                            tablero[i][j] = ' ';
                        }
                    }

                    boolean fin = false;
                    int jugadas = 0;

                    while (!fin) {

                        // MOSTRAR TABLERO
                        for (int i = 0; i < tamanoTablero; i++) {
                            for (int j = 0; j < tamanoTablero; j++) {
                                System.out.print(" " + tablero[i][j]);
                                if (j < tamanoTablero - 1) System.out.print(" |");
                            }
                            System.out.println();
                            if (i < tamanoTablero - 1) {
                                for (int k = 0; k < tamanoTablero; k++) {
                                    System.out.print("----");
                                }
                                System.out.println();
                            }
                        }

                        String nombreTurno = tuTurno ? jugador : rival;
                        char simbolo = tuTurno ? 'X' : 'O';

                        System.out.println("Turno de " + nombreTurno + " (" + simbolo + ")");

                        System.out.print("Fila (0-" + (tamanoTablero - 1) + "): ");
                        int fila = sc.nextInt();

                        System.out.print("Columna (0-" + (tamanoTablero - 1) + "): ");
                        int col = sc.nextInt();

                        if (fila < 0 || fila >= tamanoTablero || col < 0 || col >= tamanoTablero) {
                            System.out.println("Posición fuera del tablero");
                            continue;
                        }

                        if (tablero[fila][col] != ' ') {
                            System.out.println("Casilla ocupada");
                            continue;
                        }

                        tablero[fila][col] = simbolo;
                        jugadas++;

                        boolean ganador = false;

                        // FILAS
                        for (int i = 0; i < tamanoTablero && !ganador; i++) {
                            boolean completa = true;
                            for (int j = 0; j < tamanoTablero; j++) {
                                if (tablero[i][j] != simbolo) {
                                    completa = false;
                                    break;
                                }
                            }
                            if (completa) ganador = true;
                        }

                        // COLUMNAS
                        for (int j = 0; j < tamanoTablero && !ganador; j++) {
                            boolean completa = true;
                            for (int i = 0; i < tamanoTablero; i++) {
                                if (tablero[i][j] != simbolo) {
                                    completa = false;
                                    break;
                                }
                            }
                            if (completa) ganador = true;
                        }

                        // DIAGONAL PRINCIPAL
                        if (!ganador) {
                            boolean completa = true;
                            for (int i = 0; i < tamanoTablero; i++) {
                                if (tablero[i][i] != simbolo) {
                                    completa = false;
                                    break;
                                }
                            }
                            if (completa) ganador = true;
                        }

                        // DIAGONAL SECUNDARIA
                        if (!ganador) {
                            boolean completa = true;
                            for (int i = 0; i < tamanoTablero; i++) {
                                if (tablero[i][tamanoTablero - 1 - i] != simbolo) {
                                    completa = false;
                                    break;
                                }
                            }
                            if (completa) ganador = true;
                        }

                        // MOSTRAR TABLERO FINAL
                        if (ganador) {

                            for (int i = 0; i < tamanoTablero; i++) {
                                for (int j = 0; j < tamanoTablero; j++) {
                                    System.out.print(" " + tablero[i][j]);
                                    if (j < tamanoTablero - 1) System.out.print(" |");
                                }
                                System.out.println();
                                if (i < tamanoTablero - 1) {
                                    for (int k = 0; k < tamanoTablero; k++) {
                                        System.out.print("----");
                                    }
                                    System.out.println();
                                }
                            }

                            // CONTAR VICTORIA
                            if (nombreTurno.equals(jugador)) {
                                victoriasJugador++;
                            } else {
                                victoriasRival++;
                            }

                            System.out.println("¡Ha ganado " + nombreTurno + "!");
                            System.out.println("Marcador: " + jugador + " " + victoriasJugador +
                                    " - " + victoriasRival + " " + rival);
                            fin = true;

                        } else if (jugadas == tamanoTablero * tamanoTablero) {

                            for (int i = 0; i < tamanoTablero; i++) {
                                for (int j = 0; j < tamanoTablero; j++) {
                                    System.out.print(" " + tablero[i][j]);
                                    if (j < tamanoTablero - 1) System.out.print(" |");
                                }
                                System.out.println();
                                if (i < tamanoTablero - 1) {
                                    for (int k = 0; k < tamanoTablero; k++) {
                                        System.out.print("----");
                                    }
                                    System.out.println();
                                }
                            }

                            System.out.println("Empate");
                            fin = true;
                        }

                        tuTurno = !tuTurno;
                    } // FIN PARTIDA

                    // COMPROBAR SI SE HA LLEGADO A MEJOR DE X
                    if (victoriasJugador == mejorDe) {
                        System.out.println("🏆 " + jugador + " gana el mejor de " + mejorDe);
                        finDelMejorDe = true;
                    } else if (victoriasRival == mejorDe) {
                        System.out.println("🏆 " + rival + " gana el mejor de " + mejorDe);
                        finDelMejorDe = true;
                    }
                } // FIN CICLO MEJOR DE X

            } else if (opcion == 2) {

                System.out.print("CONFIGURACION: 1.Tamaño del tablero / 2.Mejor de X: ");

                int opcionConfig = sc.nextInt();
                if (opcionConfig == 1) {

                    System.out.print("Nuevo tamaño del tablero (mínimo 3): ");
                    tamanoTablero = sc.nextInt();
                    System.out.print("Tamaño del tablero establecido en " + tamanoTablero);
                    System.out.println(" ");

                } else if (opcionConfig == 2) {

                    System.out.print("Mejor de cuántas partidas: ");
                    mejorDe = sc.nextInt();

                    victoriasJugador = 0;
                    victoriasRival = 0;

                    System.out.println("Modo configurado: Ganador al mejor de " + mejorDe + " partidas.");
                }

            } else if (opcion == 3) {
                salir = true;
            }
        }
    }
}
