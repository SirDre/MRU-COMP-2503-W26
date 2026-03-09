public class Tut7

/* Driver code for tutorial 7
   COMP 2503 */

{
	private String[] victors = { "Mags", "Woof", "Morphling", "Seeder", "Porter", "Beetee", "Chaff", "Brutus", "Wiress",
			"Haymitch", "Lyme", "Blight", "Cecelia", "Enobaria", "Finnick", "Augustus", "Annie", "Katniss", "Peeta" };

	public static void main(String[] args) {
		Tut7 l = new Tut7();
		l.run();
	}

	private void run() {
		BST<String> bst = new BST<String>();
		for (int i = 0; i < victors.length; i++)
			bst.add(victors[i]);

		System.out.println("\n=========\nInorder\n=========");
		bst.printInOrder();
		System.out.println("\n=========\npreorder\n=========");
		bst.printPreOrder();
		System.out.println("\n===========\nPostorder\n===========");
		bst.printPostOrder();
		System.out.println("\n===========\nLevelorder\n===========");
		bst.printLevelOrder();
		System.out.println("The tree has " + bst.size() + " nodes and height " + bst.height());
	}
}
