import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int tamanoTablero = 3;
        int mejorDe = 1;
        int victoriasJugador = 0;
        int victoriasRival = 0;

        // LISTA DE RIVALES
        String[] rivales = {"Alex", "Marta", "Sergio", "Diego", "Rosa"};
        String rival = rivales[rand.nextInt(rivales.length)];

        boolean salir = false;
        while (!salir) {

            // MENU PRINCIPAL
            System.out.print("Indica el número de la opcion que deseas esocger (1. Jugar | 2. Configuración | 3. Salir): ");
            int opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.print("Escribe tu nombre: ");
                String jugador = sc.next();
                System.out.println("Tu rival será: " + rival);

                // INSTRUCCION INICIAL
                System.out.println("INSTRUCCIÓN INICIAL: Saldrá un numero aleatorio entre el 1 y el 2. " +
                        "Si sale 1 empezará " + jugador + " y si sale 2 empezará " + rival + ".");

                int turnoInicial = rand.nextInt(2) + 1;
                boolean tuTurno = (turnoInicial == 1);

                // DECIDIR QUIEN COMIENZA
                String nombreInicio;
                if (tuTurno) {
                    nombreInicio = jugador;
                } else {
                    nombreInicio = rival;
                }

                System.out.println("Ha salido el " + turnoInicial + " - Comienza " + nombreInicio);
                System.out.println("Marcador: " + jugador + " " + victoriasJugador + " - " + victoriasRival + " " + rival);

                boolean finDelMejorDe = false;
                while (!finDelMejorDe) {

                    // CREAR TABLERO VACIO
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

                        // DECIDIR TURNO Y SIMBOLO
                        String nombreTurno;
                        if (tuTurno) {
                            nombreTurno = jugador;
                        } else {
                            nombreTurno = rival;
                        }

                        char simbolo;
                        if (tuTurno) {
                            simbolo = 'X';
                        } else {
                            simbolo = 'O';
                        }

                        System.out.println("Turno de " + nombreTurno + " (" + simbolo + ")");

                        // LEER POSICION
                        System.out.print("Fila (0-" + (tamanoTablero - 1) + "): ");
                        int fila = sc.nextInt();

                        System.out.print("Columna (0-" + (tamanoTablero - 1) + "): ");
                        int col = sc.nextInt();

                        // VALIDAR POSICION
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

                        // COMPROBAR FILAS
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

                        // COMPROBAR COLUMNAS
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

                        // COMPROBAR DIAGONAL PRINCIPAL
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

                        // COMPROBAR DIAGONAL SECUNDARIA
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

                        // MOSTRAR RESULTADO PARTIDA
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
                    }

                    // COMPROBAR MEJOR DE X
                    if (victoriasJugador == mejorDe) {
                        System.out.println("🏆 " + jugador + " gana el mejor de " + mejorDe);
                        finDelMejorDe = true;
                    } else if (victoriasRival == mejorDe) {
                        System.out.println("🏆 " + rival + " gana el mejor de " + mejorDe);
                        finDelMejorDe = true;
                    }
                }

            } else if (opcion == 2) {
                System.out.print("CONFIGURACION: 1.Tamaño del tablero / 2.Mejor de X: ");
                int opcionConfig = sc.nextInt();

                // CAMBIAR CONFIGURACION
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
                // MOSTRAR MARCADOR FINAL ANTES DE SALIR
                System.out.println("Marcador final: Has ganado " + victoriasJugador + " partida(s), tu rival ha ganado " + victoriasRival + " partida(s).");
                salir = true;
            }
        }
    }
}
