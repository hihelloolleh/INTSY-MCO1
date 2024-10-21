import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FoodMapGraph foodMap = new FoodMapGraph(22);
        Scanner scanner = new Scanner(System.in);

        // NODES
        foodMap.addLocation("University Mall", 23, 5); // A 0
        foodMap.addLocation("Mcdonalds'", 22, 5); // B 1
        foodMap.addLocation("Perico's", 21, 3); // C 2
        foodMap.addLocation("Bloemen", 13, 4); // D 3
        foodMap.addLocation("WH Taft", 13, 5); // E 4
        foodMap.addLocation("EGI Taft", 12, 5); // F 5
        foodMap.addLocation("Castro St.", 10, 5); // G 6
        foodMap.addLocation("Agno Food Court", 9, 3); // H 7
        foodMap.addLocation("One Archers'", 7, 7); // I 8
        foodMap.addLocation("Br Andrew Gonzales", 6, 6); // J 9
        foodMap.addLocation("Green Mall", 5, 7); // K 10
        foodMap.addLocation("Green Court", 8, 5); // L 11
        foodMap.addLocation("Sherwood", 9, 8); // M 12
        foodMap.addLocation("Jollibee", 10, 8); // N 13
        foodMap.addLocation("Dagonoy St.", 15, 10); // O 14
        foodMap.addLocation("Burgundy", 17, 9); // P 15
        foodMap.addLocation("Estrada St.", 18, 9); // Q 16
        foodMap.addLocation("D Student's Place", 20, 10); // R 17
        foodMap.addLocation("Leon Guinto St.", 17, 12); // S 18
        foodMap.addLocation("Pablo Ocampo St.", 24, 8); // T 19
        foodMap.addLocation("Fidel Reyes", 5, 5); // U 20
        foodMap.addLocation("Razon", 6, 4); // V 21

        // EDGES
        // A
        foodMap.addPath("University Mall", "Mcdonalds'", 1); // A -> B
        foodMap.addPath("University Mall", "Pablo Ocampo St.", 4); // A -> T

        // B
        foodMap.addPath("Mcdonalds'", "Perico's", 3); // B -> C
        foodMap.addPath("Mcdonalds'", "D Student's Place", 7); // B -> R

        // C
        foodMap.addPath("Perico's", "Bloemen", 9); // C -> D
        foodMap.addPath("Perico's", "WH Taft", 10); // C -> E
        foodMap.addPath("Perico's", "D Student's Place", 6); // C -> R

        // D
        foodMap.addPath("Bloemen", "WH Taft", 5); // D -> E
        foodMap.addPath("Bloemen", "Agno Food Court", 5); // D -> H

        // E
        foodMap.addPath("WH Taft", "EGI Taft", 1); // E -> F
        foodMap.addPath("WH Taft", "Dagonoy St.", 4); // E -> O

        // F
        foodMap.addPath("EGI Taft", "Castro St.", 2); // F -> G
        foodMap.addPath("EGI Taft", "Agno Food Court", 4); // F -> H
        foodMap.addPath("EGI Taft", "Dagonoy St.", 5); // F -> O

        // G
        foodMap.addPath("Castro St.", "Agno Food Court", 5); // G -> H
        foodMap.addPath("Castro St.", "One Archers'", 2); // G -> I
        foodMap.addPath("Castro St.", "Dagonoy St.", 6); // G -> O

        // H
        foodMap.addPath("Agno Food Court", "One Archers'", 6); // H -> I
        foodMap.addPath("Agno Food Court", "Green Court", 2); // H -> L

        // I
        foodMap.addPath("One Archers'", "Br Andrew Gonzales", 1); // I -> J
        foodMap.addPath("One Archers'", "Green Court", 4); // I -> L
        foodMap.addPath("One Archers'", "Sherwood", 6); // I -> M
        foodMap.addPath("One Archers'", "Jollibee", 5); // I -> N
        foodMap.addPath("One Archers'", "Dagonoy St.", 7); // I -> O

        // J
        foodMap.addPath("Br Andrew Gonzales", "Green Mall", 1); // J -> K
        foodMap.addPath("Br Andrew Gonzales", "Green Court", 3); // J -> L
        foodMap.addPath("Br Andrew Gonzales", "Fidel Reyes", 3); // J -> U
        foodMap.addPath("Br Andrew Gonzales", "Razon", 2); // J -> V

        // K
        foodMap.addPath("Green Mall", "Sherwood", 5); // K -> M
        foodMap.addPath("Green Mall", "Jollibee", 6); // K -> N
        foodMap.addPath("Green Mall", "Fidel Reyes", 2); // K -> U

        // L
        foodMap.addPath("Green Court", "Razon", 1); // L -> V

        // M
        foodMap.addPath("Sherwood", "Jollibee", 1); // M -> N

        // N
        foodMap.addPath("Jollibee", "Dagonoy St.", 7); // N -> O

        // O
        foodMap.addPath("Dagonoy St.", "Burgundy", 3); // O -> P
        foodMap.addPath("Dagonoy St.", "Leon Guinto St.", 4); // O -> S

        // P
        foodMap.addPath("Burgundy", "Estrada St.", 1); // P -> Q

        // Q
        foodMap.addPath("Estrada St.", "D Student's Place", 3); // Q -> R

        // R
        foodMap.addPath("D Student's Place", "Leon Guinto St.", 5); // R -> S
        foodMap.addPath("D Student's Place", "Pablo Ocampo St.", 5); // R -> T

        // S (none)

        // T (none)

        // U
        foodMap.addPath("Fidel Reyes", "Razon", 2); // U -> V

        String start;
        String goal;
        Boolean exit = false;
        String name;
        int sc = 0;
        int input = 0;

        while (!exit) {
            foodMap.displayMenu();
            System.out.print("Enter Option: ");
            input = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            System.out.println();
            switch (input) {
                case 1:
                    // Simulate UCS
                    System.out.print("Enter Start Destination: ");
                    start = scanner.nextLine().trim(); // trim to remove extra spaces
                    System.out.print("Enter Goal Destination: ");
                    goal = scanner.nextLine().trim(); // trim to remove extra spaces

                    System.out.println("Start: " + start);
                    System.out.println("Goal: " + goal);

                    start = start.toLowerCase();
                    goal = goal.toLowerCase();

                    // Ensure user input matches nodes in the graph
                    if (foodMap.hasLocation(start) && foodMap.hasLocation(goal)) {
                        foodMap.uniformCostSearch(start, goal); // Handle user input for start and goal
                        System.out.println();
                    } else {
                        System.out.println("Error: One or both of the locations do not exist.");
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.print("Enter Start Destination: ");
                    start = scanner.nextLine().trim(); // trim to remove extra spaces
                    System.out.print("Enter Goal Destination: ");
                    goal = scanner.nextLine().trim(); // trim to remove extra spaces

                    System.out.println("Start: " + start);
                    System.out.println("Goal: " + goal);
                    start = start.toLowerCase();
                    goal = goal.toLowerCase();

                    // Ensure user input matches nodes in the graph
                    if (foodMap.hasLocation(start) && foodMap.hasLocation(goal)) {
                        foodMap.a_star(start, goal); // Handle user input for start and goal
                        System.out.println();
                    } else {
                        System.out.println("Error: One or both of the locations do not exist.");
                        System.out.println();
                    }
                    break;
                case 3:
                    foodMap.displayModifyMenu();
                    System.out.print("Enter Option: ");
                    sc = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();

                    switch (sc) {
                        case 1:
                            System.out.print("Enter name of new location (node): ");
                            name = scanner.nextLine().trim();
                            name = name.toLowerCase();

                            System.out.print("Enter x-coordinate: ");
                            int xCoord = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter y-coordinate: ");
                            int yCoord = scanner.nextInt();
                            scanner.nextLine();

                            if (foodMap.hasLocation(name, xCoord, yCoord)) {
                                System.out.println("Error: Location already exists.");
                                System.out.println();
                            } else {
                                foodMap.addLocation(name, xCoord, yCoord);
                                System.out.println(name + " has been added!");
                                System.out.println();
                            }
                            break;
                        case 2:
                            System.out.print("Enter Start Destination: ");
                            String startDest = scanner.nextLine().trim();

                            System.out.print("Enter Goal Destination: ");
                            String goalDest = scanner.nextLine().trim();

                            System.out.print("Enter Cost of Edge: ");
                            int cost = scanner.nextInt();
                            scanner.nextLine();

                            foodMap.addPath(startDest, goalDest, cost);
                            System.out.println();
                            break;
                        case 3:
                            System.out.print("Enter a location to be removed: ");
                            name = scanner.nextLine().trim();
                            name = name.toLowerCase();
                            if (foodMap.hasLocation(name)) {
                                foodMap.removeLocation(name);
                                System.out.println(name + " has been removed. ");
                                System.out.println();
                            } else {
                                System.out.println("Error: Location does not exists");
                                System.out.println();
                            }
                            break;
                        case 4:
                            // exit
                            break;
                        default:
                            System.out.println("Error: Invalid choice. Please try again.\n");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Error: Invalid choice. Please try again.\n");
            }
        }

        scanner.close();
    }
}
