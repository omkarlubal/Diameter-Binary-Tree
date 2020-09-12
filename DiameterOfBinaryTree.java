import java.util.*;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int value) {
		this.value = value;
		left = null;
		right = null;
	}
	
	public static TreeNode makeTreeFromArray(int idx , int tree[] , TreeNode root) {
		if(idx >= tree.length)
			return null;

		if(tree[idx] == -1)
			return null;

		int x = tree[idx];
		root = new TreeNode(x);
		root.left = makeTreeFromArray(2*idx + 1  , tree , root.left);
		root.right = makeTreeFromArray(2*idx + 2 , tree , root.right);
		return root;
    }
}

class DiameterOfBinaryTree {
	
	static int getDiameterOfTree(TreeNode node) {
		if (node == null) return 0;
		int diameter = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		
		// start at root node
		queue.add(node);
		
		while ( !queue.isEmpty() ) {
			// number of nodes at the current level
			int currentSize = queue.size();
			
			// keep track of maximum number of nodes encountered while traversing
			diameter = Math.max(diameter, currentSize);
			
			// Only remove nodes present at the current level
			// and add children of the removed nodes
			while ( currentSize > 0 ) {
				TreeNode currentNode = queue.poll();
				if ( currentNode.left != null ) {
					queue.add(currentNode.left);
				}
				if ( currentNode.right != null ) { 
					queue.add(currentNode.right);
				}
				currentSize--;
			}		
		}
		
		return diameter;
		
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int numOfNodes = sc.nextInt();
		int nodes[] = new int[numOfNodes];
		
		for (int idx = 0; idx < numOfNodes; idx++) {
			nodes[idx] = sc.nextInt();
		}
		
		TreeNode root = new TreeNode(-1);
		root = TreeNode.makeTreeFromArray(0, nodes, root);
		
		System.out.println(getDiameterOfTree(root));

	}
}

/*
Input 1
7
1 2 3 4 5 6 7

Creates a tree like

     1
   /   \
  2     3
 / \   / \
4   5 6   7

Input 2
7
5 2 3 4 -1 6 -1

Creates a tree like

     5
   /   \
  2     3
 /     / 
4     6   

Input 3
4
8 9 11 4

Creates a tree like

     8
   /   \
  9     11
 /      
4     

*/