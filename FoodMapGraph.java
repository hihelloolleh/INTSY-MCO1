import java.util.*;
import java.util.Scanner;

public class FoodMapGraph {
    ArrayList<Node> locations;
    Map<String, Integer> locationMap;
    ArrayList<ArrayList<Integer>> distanceMatrix;

    Scanner scanner;

    FoodMapGraph(int size) {
        locations = new ArrayList<>();
        distanceMatrix = new ArrayList<>();
        locationMap = new HashMap<>(); // uses a hash map to link/map locations to each index
        scanner = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            distanceMatrix.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                distanceMatrix.get(i).add(0);
            }
        }
    }

    // location = node
    public void addLocation(String name, int x_loc, int y_loc) {
        Node location = new Node(name.toLowerCase(), x_loc, y_loc);
        locations.add(location);
        locationMap.put(name.toLowerCase(), locations.size() - 1);
    }

    // adding cost
    public void addPath(String src, String dst, int distance) {
        int srcIndex = locationMap.get(src.toLowerCase());
        int dstIndex = locationMap.get(dst.toLowerCase());

        if (srcIndex >= distanceMatrix.size() || dstIndex >= distanceMatrix.size()) {
            // Create new column in the matrix
            for (int i = 0; i < distanceMatrix.size(); i++) {
                distanceMatrix.get(i).add(0);
            }

            // Create new row in the matrix
            distanceMatrix.add(new ArrayList<>());
            for (int i = 0; i < distanceMatrix.size(); i++) {
                distanceMatrix.get(distanceMatrix.size() - 1).add(0);
            }

            distanceMatrix.get(srcIndex).set(dstIndex, distance);
            distanceMatrix.get(dstIndex).set(srcIndex, distance);
        } else {
            distanceMatrix.get(srcIndex).set(dstIndex, distance);
            distanceMatrix.get(dstIndex).set(srcIndex, distance);
        }
    }

    // removeLocation
    public void removeLocation(String name) {
        int index = locationMap.get(name);

        // Remove row from matrix
        distanceMatrix.remove(index);

        // Remove column from matrix
        for (ArrayList<Integer> Row : distanceMatrix) {
            Row.remove(index);
        }

        // Update key values
        for (String key : locationMap.keySet()) {
            int val = locationMap.get(key);
            if (val > index) {
                locationMap.put(key, val - 1);
            }
        }

        // Remove from map and arraylist
        locationMap.remove(name);
        locations.remove(index);
    }

    private int heuristic(int src, int goal) {
        Node srcNode = locations.get(src);
        Node destNode = locations.get(goal);

        int xdelta = Math.abs(destNode.x_loc - srcNode.x_loc);
        int ydelta = Math.abs(destNode.y_loc - srcNode.y_loc);

        int manhattan_distance = xdelta + ydelta;

        return manhattan_distance;
    }

    public void a_star(String startLocation, String goalLocation) {
        int src = locationMap.get(startLocation);
        int goal = locationMap.get(goalLocation);
        PriorityQueue<NodeCost> pq = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.cost + heuristic(n.node, goal)));
        pq.offer(new NodeCost(src, 0)); // init node 1 with 0 cost

        int[] costs = new int[locations.size()];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;

        boolean[] visited = new boolean[locations.size()];
        Map<Integer, Integer> parentMap = new HashMap<>(); // store parent nodes

        while (!pq.isEmpty()) {
            NodeCost current = pq.poll();
            int currentNode = current.node;
            int currentCost = current.cost;

            if (visited[currentNode])
                continue;
            visited[currentNode] = true;

            // goal node reached
            if (currentNode == goal) {
                System.out.println("Optimal path to " + goalLocation + " using A* with cost: " + currentCost);
                printPath(parentMap, src, goal);
                return;
            }

            // else expand current node and traverse

            for (int i = 0; i < distanceMatrix.get(currentNode).size(); i++) {
                if (distanceMatrix.get(currentNode).get(i) > 0 && !visited[i]) {
                    int newCost = currentCost + distanceMatrix.get(currentNode).get(i) + heuristic(i, goal);
                    if (newCost < costs[i]) {
                        costs[i] = newCost;
                        pq.offer(new NodeCost(i, newCost - heuristic(i, goal)));
                        parentMap.put(i, currentNode); // Set the parent of i as currentNode
                    }
                }
            }
        }

        System.out.println("Goal " + goalLocation + " is unreachable.");
    }

    public void uniformCostSearch(String startLocation, String goalLocation) {
        int src = locationMap.get(startLocation);
        int goal = locationMap.get(goalLocation);
        PriorityQueue<NodeCost> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new NodeCost(src, 0)); // init node 1 with 0 cost

        int[] costs = new int[locations.size()];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;

        boolean[] visited = new boolean[locations.size()];
        Map<Integer, Integer> parentMap = new HashMap<>(); // store parent nodes

        while (!pq.isEmpty()) {
            NodeCost current = pq.poll();
            int currentNode = current.node;
            int currentCost = current.cost;

            if (visited[currentNode])
                continue;
            visited[currentNode] = true;

            // goal node reached
            if (currentNode == goal) {
                System.out.println("Optimal path to " + goalLocation + " with cost: " + currentCost);
                printPath(parentMap, src, goal);
                return;
            }

            // else expand current node and traverse
            for (int i = 0; i < distanceMatrix.get(currentNode).size(); i++) {
                if (distanceMatrix.get(currentNode).get(i) > 0 && !visited[i]) {
                    int newCost = currentCost + distanceMatrix.get(currentNode).get(i);
                    if (newCost < costs[i]) {
                        costs[i] = newCost;
                        pq.offer(new NodeCost(i, newCost));
                        parentMap.put(i, currentNode);
                    }
                }
            }
        }

        System.out.println("Goal " + goalLocation + " is unreachable.");
    }

    private void printPath(Map<Integer, Integer> parentMap, int src, int goal) {
        List<String> path = new ArrayList<>();
        int currentNode = goal;
        while (currentNode != src) {
            path.add(locations.get(currentNode).name);
            currentNode = parentMap.get(currentNode);
        }
        path.add(locations.get(src).name);

        Collections.reverse(path);
        System.out.println("Path: " + String.join(" -> ", path));
    }

    public boolean hasLocation(String location) {
        for (Node node : locations) {
            if (node.name.equals(location)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLocation(String location, int x, int y) {
        for (Node node : locations) {
            if (node.name.equals(location) || (node.x_loc == x && node.y_loc == y)) {
                return true;
            }
        }
        return false;
    }

    public void displayMenu() {
        System.out.println("State Based Model - UCS & A* Algorithms");
        System.out.println("1. Simulate UCS (Uniform Cost Search)");
        System.out.println("2. Simulate A*");
        System.out.println("3. Modify state space (Add or Delete Nodes)");
        System.out.println("4. Exit");
    }

    public void displayModifyMenu() {
        System.out.println("Modify the Food Map");
        System.out.println("1. Add a New Location (Node)");
        System.out.println("2. Add a New Path (Edge)");
        System.out.println("3. Remove a Location (Node)");
        System.out.println("4. Exit");
    }
}