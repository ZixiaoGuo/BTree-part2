public interface SortingStrategy {
    //TODO: need insertStudent, insertNode, search functions
    public Boolean insertStudent(Student student, BTree.BTreeNode node, BTree bTree);

    public void insertNode(BTree.BTreeNode node, Student student, BTree.BTreeNode extraChildNode,BTree bTree, int ORDER);

    public BTree.BTreeNode search(BTree.BTreeNode rootNode, Student target);
}
