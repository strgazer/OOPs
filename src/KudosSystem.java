import java.util.*;

class KudosSystem {
    private final Map<Integer, String> kudosDescriptions; // Maps kudos ID to description
    private final Map<Integer, Set<Integer>> kudosEndorsers; // Maps kudos ID to a set of endorsers
    private int kudosCounter; // Counter for generating unique kudos IDs

    // Constructor to initialize data structures
    public KudosSystem() {
        this.kudosCounter = 0;
        this.kudosDescriptions = new HashMap<>();
        this.kudosEndorsers = new HashMap<>();
    }

    public static void main(String[] args) {
        KudosSystem ks = new KudosSystem();

        // Adding some kudos
        ks.addKudos("Great teamwork on the project!");
        ks.addKudos("Excellent problem-solving skills!");
        ks.addKudos("Outstanding leadership!");

        // Adding endorsements
        ks.endorseKudos(1, 101);
        ks.endorseKudos(1, 102);
        ks.endorseKudos(2, 101);
        ks.endorseKudos(2, 103);
        ks.endorseKudos(2, 104);
        ks.endorseKudos(3, 101);

        // Printing kudos
        ks.printKudos();
    }

    // Adds a new kudos with a description
    public void addKudos(String kudosDescription) {
        ++kudosCounter;
        kudosDescriptions.put(kudosCounter, kudosDescription);
    }

    // Adds an endorser to a given kudos ID
    public void endorseKudos(int kudosId, int endorserId) {
        if (kudosDescriptions.containsKey(kudosId)) {
            kudosEndorsers.putIfAbsent(kudosId, new HashSet<>());
            kudosEndorsers.get(kudosId).add(endorserId);
        } else {
            System.out.println("Kudos ID " + kudosId + " does not exist.");
        }
    }

    // Prints the kudos descriptions with unique endorser counts in descending order
    public void printKudos() {
        // List to store kudos ID and their endorser count
        List<Map.Entry<Integer, Integer>> kudosWithCounts = new ArrayList<>();

        for (Map.Entry<Integer, Set<Integer>> entry : kudosEndorsers.entrySet()) {
            kudosWithCounts.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().size()));
        }

        // Sort by endorser count (descending), then by kudos ID (ascending) for ties
        kudosWithCounts.sort((a, b) -> {
            if (!b.getValue().equals(a.getValue())) {
                return b.getValue() - a.getValue();
            }
            return a.getKey() - b.getKey();
        });

        // Print sorted kudos
        for (Map.Entry<Integer, Integer> entry : kudosWithCounts) {
            int kudosId = entry.getKey();
            int count = entry.getValue();
            System.out.println("Kudos ID: " + kudosId + ", Description: " + kudosDescriptions.get(kudosId) + ", Unique Endorsers: " + count);
        }
    }
}